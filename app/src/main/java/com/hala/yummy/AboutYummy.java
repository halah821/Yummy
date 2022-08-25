package com.hala.yummy;

import android.widget.TextView;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;

/**
 * Created by Hala on 5/15/2016.
 */
public class AboutYummy extends Activity{
    TextView txtAbout;

    /**
     * the value of this attribute is about application description.
     * you can change it with your own. it also accept html tag.
     */
    String aboutContent = "Yummy is an Android app" +
            " that collects good food recipes of my choice. " +
            "&copy; 2016 Yummy. All rights reserved.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_app);

        txtAbout = (TextView) findViewById(R.id.txtAbout);
        txtAbout.setText(Html.fromHtml(aboutContent));
    }
}