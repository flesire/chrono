package net.openmind.chrono;

import android.app.Activity;
import android.content.Loader;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class Main extends Activity implements View.OnClickListener {

    private Chronometer chronometer;

    private SoundPool soundPool;
    private int soundId;
    private boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        ((Button) findViewById(R.id.start_button)).setOnClickListener(this);
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_button:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stop_button:
                chronometer.stop();
                break;
        }
    }

}
