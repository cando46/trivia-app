package com.mentalsegment.triviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SendMessageActivity extends AppCompatActivity {

    EditText to;
    EditText subject;
    EditText message;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        init();
    }

    private void init() {
        to=findViewById(R.id.et_main_to);
        subject=findViewById(R.id.et_main_subject);
        message=findViewById(R.id.et_main_message);
        fab=findViewById(R.id.fb_main_send);

    }

    public void send(View view) {
        String TO=to.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{TO});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());

        startActivity(Intent.createChooser(intent, "Send Email"));
    }
}
