package com.example.abhishek.speakup;

import java.util.Locale;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Sound extends Activity implements
        TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSpeak;
    private Button btnStop, btn_clear;
    private EditText txtText;
    private SeekBar seekPitch;
    private SeekBar seekSpeed;

    private double pitch = 1.0;
    private double speed = 1.0;
    int result;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        tts = new TextToSpeech(this, this);

        btnSpeak = (Button) findViewById(R.id.btnSpeak);
        btnStop = (Button) findViewById(R.id.btnStop);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        txtText = (EditText) findViewById(R.id.txtText);

        seekPitch = (SeekBar) findViewById(R.id.seekPitch);
        seekPitch.setThumbOffset(5);
        seekPitch.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Log.d("Speech", "Progress ["+progress+"]");
                pitch = (float) progress / (seekBar.getMax() / 2);
                //Log.d("Speech", "Pitch ["+pitch+"]");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekSpeed = (SeekBar) findViewById(R.id.seekSpeed);
        seekSpeed.setThumbOffset(5);
        seekSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed = (float) progress / (seekBar.getMax() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speakOut();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                stopspeaking();
            }

        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtText.setText("");
            }
        });

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);

        String[] items = new String[] { "--Select Language---","UK_English", "US_English", "FRENCH" ,"GERMAN"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "please select any language",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        result = tts.setLanguage(Locale.UK);
                        break;
                    case 2:
                        result = tts.setLanguage(Locale.US);
                        break;
                    case 3:
                        result = tts.setLanguage(Locale.FRENCH);
                        break;
                    case 4:
                        result = tts.setLanguage(Locale.GERMAN);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            //int result = tts.setLanguage(Locale.UK);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(getApplicationContext(), "Language not supported",Toast.LENGTH_SHORT).show();
            } else {
                btnSpeak.setEnabled(true);
                speakOut();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Initialisation failed",Toast.LENGTH_SHORT).show();
        }

    }

    private void speakOut() {
        String text = txtText.getText().toString();
        tts.setPitch((float) pitch);
        tts.setSpeechRate((float) speed);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void stopspeaking() {
        if (tts != null)
            tts.stop();
    }
}
