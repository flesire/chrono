package net.openmind.chrono;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class Main extends Activity implements View.OnClickListener {

    private Chronometer chronometer1;
    private Chronometer chronometer2;

    private long firstLimit = 10000;
    private long secondLimit = 20000;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        chronometer1 = (Chronometer) findViewById(R.id.chronometer1);
        chronometer2 = (Chronometer) findViewById(R.id.chronometer2);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);

        chronometer1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsedMillis > firstLimit) {
                    chronometer.stop();
                    chronometer2.setBase(SystemClock.elapsedRealtime());
                    chronometer2.start();
                }
            }
        });

        chronometer2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                if (elapsedMillis > secondLimit) {
                    chronometer.stop();
                    chronometer1.setBase(SystemClock.elapsedRealtime());
                    chronometer1.start();
                }
            }
        });

        player = MediaPlayer.create(this, R.raw.sound1);
        player.setLooping(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                chronometer1.setBase(SystemClock.elapsedRealtime());
                chronometer1.start();
                break;
            case R.id.stop_button:
                chronometer1.stop();
                chronometer2.stop();
                break;
        }
    }

}
