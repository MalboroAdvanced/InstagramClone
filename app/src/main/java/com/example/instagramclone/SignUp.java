package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //UI Components
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEnterEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignup);
                }
                return false;
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){

            transitionToSocialMediaActivity();

        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSignup:
                if(edtEmail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){

                    FancyToast.makeText(this,"please fill all attributes",
                            Toast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }else{

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up " + edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {


                        if(e == null){
                            FancyToast.makeText(SignUp.this,appUser.getUsername()
                                    + " is signed up",Toast.LENGTH_SHORT,FancyToast.SUCCESS,
                                    true).show();

                            transitionToSocialMediaActivity();

                        }else{
                            FancyToast.makeText(SignUp.this,"there was an error" + e.getMessage(),
                                    Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();
                        }

                        progressDialog.dismiss();
                    }
                });}

                break;

            case R.id.btnLogin:

                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                startActivity(intent);

                break;


        }

    }

    public void rootLayoutTapped(View view){


        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    private void transitionToSocialMediaActivity(){

        Intent intent = new Intent(this, SocialMediaActivity.class);
        startActivity(intent);

    }
}
