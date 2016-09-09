package com.example.rakesh.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText meditText;

    public final static String EXTRA_MESSAGE ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meditText = (EditText) findViewById(R.id.EditText_Edit_Message);
    }

    // Method for send message
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = meditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

   public void showAppBarScreen(View view) {
       Intent intent = new Intent(this, AppBarTest.class);
       startActivity(intent);
   }
}
