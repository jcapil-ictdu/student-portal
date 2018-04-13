package com.example.ictdu.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;


public class SignupActivity extends AppCompatActivity {

    TextView fname;
    TextView lname;
    TextView user;
    TextView pwd;
    public Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = findViewById(R.id.tvFirstName);
        lname = findViewById(R.id.tvLastName);
        user = findViewById(R.id.tvUsername);
        pwd = findViewById(R.id.tvPassword);
        btn = findViewById(R.id.btnSignUp);
    }

    public void submitData(View view) {
        btn.setEnabled(false);

        new Ajax(this, fname.getText().toString(), lname.getText().toString(), user.getText().toString(), pwd.getText().toString()).execute();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
