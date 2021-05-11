package com.icanerdogan.instragramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import es.dmoral.toasty.Toasty;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    EditText txtEmail, txtFullName, txtUsername, txtPassword;
    Button SignUp, btnSignUpToSignIn;

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
        SignUp = findViewById(R.id.btnSignUp);
        btnSignUpToSignIn = findViewById(R.id.btnSignUpSignIn);

        // Button Click Events
        SignUp.setOnClickListener(this);
        btnSignUpToSignIn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                Register();
                break;
            case R.id.btnSignUpSignIn:
                SignUpToSignIn();
                break;
        }
    }
    private void Register() {
        ParseUser parseUser2 = new ParseUser();
        parseUser2.setUsername(txtUsername.getText().toString());
        parseUser2.setPassword(txtPassword.getText().toString());
        parseUser2.put("email",txtEmail.getText().toString());
        parseUser2.put("fullName", txtFullName.getText().toString());

        parseUser2.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Toasty.error(getApplicationContext(), "User Could Not Be Created",Toasty.LENGTH_SHORT, true).show();

                }
                else{
                    Toasty.success(getApplicationContext(), "User Created", Toasty.LENGTH_SHORT, true).show();

                }
            }
        });
    }

    private void SignUpToSignIn() {
        Intent signInIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(signInIntent);
        finish();
    }

}