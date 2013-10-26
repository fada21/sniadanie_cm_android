package com.cm.sniadanie_cm;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView title;
    EditText name;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        title = (TextView) findViewById(R.id.title);
        title.setText("Nasz napis Otwarte zabytki");
        
        name = (EditText) findViewById(R.id.name);
        
        Button searchBtn = (Button) findViewById(R.id.search_btn);
        
        searchBtn.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Kliknęliśmy", Toast.LENGTH_SHORT).show();
                title.setText(name.getText().toString());
            }
        } );
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
