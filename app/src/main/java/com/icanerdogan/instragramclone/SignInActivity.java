package com.icanerdogan.instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtUsername, txtPassword;
    Button btnSignIn, btnSignInToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setContentView(R.layout.activity_sign_in);

        txtUsername = findViewById(R.id.txtSignInUsername);
        txtPassword = findViewById(R.id.txtSignInPassword);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignInToSignUp = findViewById(R.id.btnSignInSignUp);

        btnSignIn.setOnClickListener(this);
        btnSignInToSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                SignIn();
                break;
            case R.id.btnSignInSignUp:
                SignInToSignUp();
                break;
        }
    }

    private void SignIn() {
        // LOGIN TO THE HOME SCREEN
    }

    private void SignInToSignUp() {
        Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
        finish();
    }

}