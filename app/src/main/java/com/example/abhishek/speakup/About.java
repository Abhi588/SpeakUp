package com.example.abhishek.speakup;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
/**
 * Created by Abhishek on 04-07-2017.
 */
public class About extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        WebView wv = (WebView)findViewById(R.id.wv);
        String text = "<html><body>";
        text += "<h3 style=\"text-align:center;\">Utility App</h3><br><p>App to be useful for conversion of text to speech for who want to learn different language"+
                " and for who wants correct pronunciation of words. Build to provide you better interface </p><br><ul style=\"list-style-type:square\">\n" +
                "  <li>version ='1.0'</li>\n" +
                "  <li>miniimum 4.1 jelly bean required</li>\n" +
                "  <li>No external Interface Required</li>\n" +
                "</ul>" +
                "<br><div style=\"background-color:black; color:white;\";><p>Copyright © Somdatta dubey</p></div>";
        text += "</body></html>";
        wv.loadData(text,"text/html","utf-8");
    }
}
