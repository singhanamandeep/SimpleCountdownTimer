package aman.example.com.countservice;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by aman on 9/Nov/14.
 */
public class MyService extends Service {

    static CountDownTimer ct;
    @Override
    public void onCreate() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "This is user Service", Toast.LENGTH_SHORT).show();

        ct = new CountDownTimer(MainActivity.counter*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                MainActivity.countTv.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                MainActivity.countTv.setText("done!");
                Log.v("ONFINISH", "the finish is called");
                MainActivity.countTv.setText("Time's up!");
                Calendar c = Calendar.getInstance();
                //for getting date also add dd:MMMM:yyyy in the simpleDateFormat agr
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String strDate = sdf.format(c.getTime());
                MainActivity.stoppedTv.setText("Stopped on:\n"+ strDate);
            }
        };
        ct.start();
//        IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
//        registerReceiver(recivedBroadcast,filter);

        return 1;
    }


    @Override
    public void onDestroy() {
        ct.cancel();
        Toast.makeText(this, "The user Service is terminated", Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        //for getting date also add dd:MMMM:yyyy in the simpleDateFormat agr
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String strDate = sdf.format(c.getTime());
        MainActivity.stoppedTv.setText("Stopped on:\n"+ strDate);
//        unregisterReceiver(recivedBroadcast);
    }
}

