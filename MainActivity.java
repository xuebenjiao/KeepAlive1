package demo.keepalive;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,LocalService.class));
        startService(new Intent(this,RemoteService.class));
        Log.e("OnNewIntent","onCreate");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("OnNewIntent","onStart");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("OnNewIntent","onNewIntent()");
        super.onNewIntent(intent);
    }
    public void myClick(View view){
        startActivity(new Intent(this,MainActivity.class));

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("OnNewIntent","onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("OnNewIntent","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("OnNewIntent","onPause");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("OnNewIntent","onConfigurationChanged");
    }
}
