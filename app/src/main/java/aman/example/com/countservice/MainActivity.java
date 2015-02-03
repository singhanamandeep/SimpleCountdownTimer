package aman.example.com.countservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {

    static TextView countTv;
    static TextView startedTv, stoppedTv;
    Button startB, stopB;
    Intent myService;
    boolean serviceStatus = false;
    static int counter = 0;
    EditText duratioIp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countTv = (TextView) findViewById(R.id.mainCountTextView);
        startedTv = (TextView) findViewById(R.id.mainStartedAtTextView);
        stoppedTv = (TextView) findViewById(R.id.mainStoppedAtTextView);
        startB = (Button) findViewById(R.id.mainStartButton);
        stopB = (Button) findViewById(R.id.mainStopButton);
        duratioIp = (EditText)findViewById(R.id.mainDurationEditText);

        countTv.setText("Count Down");
        startedTv.setText(" ");
        myService = new Intent(MainActivity.this, MyService.class);

        startB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = Integer.parseInt(duratioIp.getText().toString());
                if(serviceStatus == true)
                    Toast.makeText(MainActivity.this,
                            "Service already running", Toast.LENGTH_SHORT).show();
                else if(counter <= 0 || (duratioIp.getText().toString() == null)){
                    Toast.makeText(getApplicationContext(),
                            "Duration is not valid", Toast.LENGTH_SHORT).show();
                }
                else {
                    Calendar c = Calendar.getInstance();
                    //for getting date also add dd:MMMM:yyyy in the simpleDateFormat agr
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ");
                    String strDate = sdf.format(c.getTime());
                    countTv.setText("Count Down");
                    startedTv.setText("Started on:\n" + strDate);
                    serviceStatus = true;
                    startService(myService);
                }
            }
        });
        stopB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(serviceStatus == true){
                    stopService(myService);
                    serviceStatus = false;
                }
                else
                {
                    stoppedTv.setText("Clock is already stoped");
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
