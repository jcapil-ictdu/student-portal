package com.example.ictdu.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class LoginActivty extends AppCompatActivity {

    Button btnCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        goCreateAccount();
    }

    public void goCreateAccount() {
        btnCreateAccount = (Button) findViewById(R.id.btnCreateanAccount);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent act = new Intent(LoginActivty.this, SignupActivity.class);
                startActivity(act);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
