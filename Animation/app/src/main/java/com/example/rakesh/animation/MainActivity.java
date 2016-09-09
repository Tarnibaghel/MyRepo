package com.example.rakesh.animation;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    String URL = "http://static.boredpanda.com/blog/wp-content/uploads/2016/02/smiling-cat-shelter-adopted-coen-ava-rey-thumb.jpg";
    ImageView mImage;
    Button mButton;
    ProgressDialog mProgressDialog;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.ImageView_Image);
        mButton = (Button) findViewById(R.id.Button_Download);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                checkInternetConenction();
                new DownloadImage().execute(URL);
            }
        });
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Downloading Image ");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... URL) {

            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImage.setImageBitmap(result);
            mProgressDialog.dismiss();
        }
    }

    private boolean checkInternetConenction() {
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    public void onClick(View v){

        switch(v.getId()) {
            case R.id.Button_zoomin:
               animation = AnimationUtils.loadAnimation(getApplicationContext(),
                   R.anim.zoom_in);
                break;
            case R.id.Button_zoomout:
               animation = AnimationUtils.loadAnimation(getApplicationContext(),
                       R.anim.zoom_out);
                break;
            case R.id.Button_blink:
                animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.blink);
                break;
            case R.id.Button_rotate:
                animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.rotate);
                break;
            case R.id.Button_move:
                animation = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.move);
                break;
        }

        mImage.startAnimation(animation);
    }
}
