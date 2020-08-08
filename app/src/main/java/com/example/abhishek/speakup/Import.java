package com.example.abhishek.speakup;

import java.io.*;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Import extends Activity implements
        TextToSpeech.OnInitListener {
    EditText txtData;
    Button btnWriteSDFile;
    Button btnReadSDFile;
    Button btnClearScreen;
    Button btnClose;
    private TextToSpeech tts;
    private Button btnSpeak;
    private Button btnStop;
    private double pitch = 1.0;
    private double speed = 1.0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
        // bind GUI elements with local controls
        tts = new TextToSpeech(this, this);
        txtData = (EditText) findViewById(R.id.txtData);
        txtData.setHint("Enter some lines of data here...");
        btnSpeak = (Button) findViewById(R.id.btnspk);
        btnStop = (Button) findViewById(R.id.btnstop);

        btnWriteSDFile = (Button) findViewById(R.id.btnWriteSDFile);
        btnWriteSDFile.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // write on SD card file data in the text box
                try {
                    File myFile = new File("/sdcard/mysdfile.txt");
                    myFile.createNewFile();
                    FileOutputStream fOut = new FileOutputStream(myFile);
                    OutputStreamWriter myOutWriter =
                            new OutputStreamWriter(fOut);
                    myOutWriter.append(txtData.getText());
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(),
                            "Done writing SD 'mysdfile.txt'",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }// onClick
        }); // btnWriteSDFile
        btnReadSDFile = (Button) findViewById(R.id.btnReadSDFile);
        btnReadSDFile.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // write on SD card file data in the text box
                try {
                    File myFile = new File("/External Storage/mysdfile.txt");
                    FileInputStream fIn = new FileInputStream(myFile);
                    BufferedReader myReader = new BufferedReader(
                            new InputStreamReader(fIn));
                    String aDataRow = "";
                    String aBuffer = "";
                    while ((aDataRow = myReader.readLine()) != null) {
                        aBuffer += aDataRow + "\n";
                    }
                    txtData.setText(aBuffer);
                    myReader.close();
                    Toast.makeText(getBaseContext(),
                            "Done reading SD 'mysdfile.txt'",
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }// onClick
        }); // btnReadSDFile

        btnClearScreen = (Button) findViewById(R.id.btnClearScreen);
        btnClearScreen.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // clear text box
                txtData.setText("");
            }
        }); // btnClearScreen

        btnClose = (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // clear text box
                finish();
            }
        }); // btnClose
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

    }// onCreate

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
            int result = tts.setLanguage(Locale.UK);

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
        String text = txtData.getText().toString();
        tts.setPitch((float) pitch);
        tts.setSpeechRate((float) speed);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void stopspeaking() {
        if (tts != null)
            tts.stop();
    }

}