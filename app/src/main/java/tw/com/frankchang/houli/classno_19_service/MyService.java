package tw.com.frankchang.houli.classno_19_service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by 張景翔 on 2017/5/10.
 */

public class MyService extends Service {

    private NotificationManager ntfiManager;
    private final int NTFI_ID = 79979;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();

        return new MyBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "onUnbind()", Toast.LENGTH_SHORT).show();

        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ntfiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //跳出一個 Notification 跑進度條
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i+=2){
                    try {
                        Thread.sleep(1500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendNotification(i);
                }
            }
        }).start();

        //Service自我終結
        //stopSelf();

        //生命週期展示
        Toast.makeText(this, "onStartCommand()", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification(int propress) {
        Notification ntfi  = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.service_ContentTitle))
                .setContentText(getString(R.string.service_ContentText))
                .setProgress(100, propress, false)
                .build();

        if (propress != 100){
            ntfiManager.notify(NTFI_ID, ntfi);
        }
        else{
            ntfiManager.cancel(NTFI_ID);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show();
    }

    class MyBinder extends Binder{

        public void download_File(){
            Toast.makeText(MyService.this, "MyService : download_File()", Toast.LENGTH_SHORT).show();
        }

        public void play_Music(){
            Toast.makeText(MyService.this, "MyService : play_Music()", Toast.LENGTH_SHORT).show();
        }
    }
}
