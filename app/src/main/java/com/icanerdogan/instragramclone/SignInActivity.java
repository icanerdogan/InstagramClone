package com.icanerdogan.instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import es.dmoral.toasty.Toasty;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtUsername, txtPassword;
    Button SignIn, btnSignInToSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        setContentView(R.layout.activity_sign_in);

        // Remember User
        ParseUser pUser = ParseUser.getCurrentUser();
        if (pUser !=null){
            Intent intent = new Intent(SignInActivity.this, AppActivity.class);
            startActivity(intent);
            finish();
        }

        // TextView Components
        txtUsername = findViewById(R.id.txtSignInUsername);
        txtPassword = findViewById(R.id.txtSignInPassword);

        // Button Components
        SignIn = findViewById(R.id.btnSignIn);
        btnSignInToSignUp = findViewById(R.id.btnSignInSignUp);

        // Button Events
        SignIn.setOnClickListener(this);
        btnSignInToSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                Login();
                break;
            case R.id.btnSignInSignUp:
                SignInToSignUp();
                break;
        }
    }

    private void Login() {
        ParseUser.logInInBackground(txtUsername.getText().toString(), txtPassword.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Toasty.error(getApplicationContext(), "Username or password is wrong", Toasty.LENGTH_SHORT).show();
                }
                else {
                    // Sign In to App Activity
                    Intent intent = new Intent(SignInActivity.this, AppActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void SignInToSignUp() {
        Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(signUpIntent);
        finish();
    }

}