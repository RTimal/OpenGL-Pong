package com.games.battlepong;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener(){
        public void onClick(View argo){
        Intent i = new Intent(MainActivity.this,gameController.class);
        startActivity(i);
        }
        });
    }
}