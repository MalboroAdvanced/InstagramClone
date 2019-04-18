package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity, btnSignupLoginActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);

        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnLoginActivity);
                }
                return false;
            }
        });

        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignupLoginActivity = findViewById(R.id.btnSignupLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignupLoginActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnLoginActivity:


                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Logging In");
                progressDialog.show();

                ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                        edtLoginPassword.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if(user != null && e == null){
                                    FancyToast.makeText(LoginActivity.this,
                                                    "Logged in successfully", Toast.LENGTH_SHORT,FancyToast.SUCCESS,
                                            true).show();

                                    progressDialog.dismiss();
                                    transitionToSocialMediaActivity();


                                }else{

                                    FancyToast.makeText(LoginActivity.this,"there was an error"
                                                    + e.getMessage(),
                                            Toast.LENGTH_SHORT,FancyToast.ERROR,true).show();

                                }
                            }

                        });


                break;
            case R.id.btnSignupLoginActivity:

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);

                break;

        }

    }

    public  void rootLayoutLoginTapped(View view){

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
