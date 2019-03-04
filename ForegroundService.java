package demo.keepalive;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

public class ForegroundService extends Service {
    private static final int SERVICE_ID = 1;
private class LocalBind extends Binder{

}
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBind();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(Build.VERSION.SDK_INT<18){
            //开启前台Service,并且不显示通知栏消息
            startForeground(SERVICE_ID,new Notification());
        }
        else if(Build.VERSION.SDK_INT < 26){
            //开启前台Services
            startForeground(SERVICE_ID,new Notification());
            //移除通知栏消息
            startService(new Intent(this,InnerService.class));
        }
        else{//version >= android 8.0 channelId 渠道
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if(manager != null){
                NotificationChannel channel = new NotificationChannel("channel","xxx",NotificationManager.IMPORTANCE_MIN);
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channel");
                startForeground(SERVICE_ID,builder.build());
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }
    public static class InnerService extends  Service{

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(SERVICE_ID,new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
