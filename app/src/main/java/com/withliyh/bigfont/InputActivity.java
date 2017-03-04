package com.withliyh.bigfont;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText)findViewById(R.id.textView)).getText().toString();
                if (text.trim().isEmpty()) {
                    return;
                }
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                intent.putExtra("text", text);
                startActivity(intent);
            }
        });
    }
}
