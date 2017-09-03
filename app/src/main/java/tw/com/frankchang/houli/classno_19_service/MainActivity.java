package tw.com.frankchang.houli.classno_19_service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyService.MyBinder mybinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startOnClick(View view) {
        Intent start_it = new Intent(this, MyService.class);
        startService(start_it);
    }

    public void stopOnClick(View view) {
        Intent stop_it = new Intent(this, MyService.class);
        stopService(stop_it);
    }

    public void bindOnClick(View view) {
        Intent bind_it = new Intent(this, MyService.class);
        bindService(bind_it, conn, BIND_AUTO_CREATE);
    }

    public void unBindOnClick(View view) {
        unbindService(conn);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mybinder = (MyService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //用不到
        }
    };

    public void downloadFileOnClick(View view) {
        if (mybinder != null){
            mybinder.download_File();
        }
    }

    public void playMusicOnClick(View view) {
        if (mybinder != null){
            mybinder.play_Music();
        }
    }
}
