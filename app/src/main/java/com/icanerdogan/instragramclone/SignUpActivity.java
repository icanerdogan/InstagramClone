package com.icanerdogan.instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtEmail, txtFullName, txtUsername, txtPassword;
    Button btnSignUp, btnSignUpToSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Text Components
        txtEmail = findViewById(R.id.txtSignUpEmail);
        txtFullName = findViewById(R.id.txtSignUpFullName);
        txtUsername = findViewById(R.id.txtSignUpUsername);
        txtPassword = findViewById(R.id.txtSignUpPassword);

        // Button Components
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUpToSignIn = findViewById(R.id.btnSignUpSignIn);

        // Button Click Events
        btnSignUp.setOnClickListener(this);
        btnSignUpToSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                SignUp();
                break;
            case R.id.btnSignUpSignIn:
                SignUpToSignIn();
                break;
        }
    }
    private void SignUp() {
        // SIGN UP WITH PARSE
    }

    private void SignUpToSignIn() {
        Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(signInIntent);
        finish();
    }

}