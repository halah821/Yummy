package com.hala.yummy;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Hala on 5/15/2016.
 */
public class RecipeDetail extends Activity {

    TextView txtRecipeName, txtCookTime, txtServes, txtIngredients, txtDirections;
    ImageView imgPreviewDetail;
    ProgressBar prgLoading;
    ScrollView sclDetail;

    DBHelper dbhelper;
    ArrayList<Object> data;
    int id;
    String RecipeName, Preview, CookTime, Serves, Ingredients, Directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        Intent i_get = getIntent();
        id = i_get.getIntExtra("id_for_detail", 0);


        dbhelper = new DBHelper(this);

        txtRecipeName = (TextView) findViewById(R.id.txtRecipeName);
        txtCookTime = (TextView) findViewById(R.id.txtCookTime);
        txtServes = (TextView) findViewById(R.id.txtServes);
        txtIngredients = (TextView) findViewById(R.id.txtIngredients);
        txtDirections = (TextView) findViewById(R.id.txtDirections);
        imgPreviewDetail = (ImageView) findViewById(R.id.imgPreviewDetail);
        prgLoading = (ProgressBar) findViewById(R.id.prgLoading);
        sclDetail = (ScrollView) findViewById(R.id.sclDetail);

        try{
            dbhelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }

        new getDetailTask().execute();

    }

    /** this class is used to handle thread */
    public class getDetailTask extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // TODO Auto-generated method stub
            getDetailFromDatabase();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            prgLoading.setVisibility(8);
            sclDetail.setVisibility(0);
            showDetail();
            dbhelper.close();
        }
    }

    /**
     * this code is used to get data from database and store them
     * to attributes
     */
    public void getDetailFromDatabase(){
        ArrayList<Object> row = dbhelper.getDetail(id);

        RecipeName = row.get(0).toString();
        Preview = row.get(1).toString();
        CookTime = row.get(2).toString();
        Serves = row.get(3).toString();
        Ingredients = row.get(4).toString();
        Directions = row.get(5).toString();
    }

    /**
     * then set those values of attributes to the views
     */
    public void showDetail(){
        txtRecipeName.setText(RecipeName);
        int imagePreview = getResources().getIdentifier(Preview, "drawable", getPackageName());
        imgPreviewDetail.setImageResource(imagePreview);
        txtCookTime.setText("Cook time : "+CookTime);
        txtServes.setText("Serves : "+Serves);
        txtIngredients.setText(Html.fromHtml(Ingredients));
        txtDirections.setText(Html.fromHtml(Directions));
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig)
    {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }
}

