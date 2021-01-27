package com.example.restaurantapk;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Register extends AppCompatActivity {
    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    EditText etName,etSur,etPassword,etReEnter,etMail,etTel,etBday;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);


        etName=findViewById(R.id.etName);
        etSur=findViewById(R.id.etSur);
        etPassword=findViewById(R.id.etPassword);
        etReEnter=findViewById(R.id.etReEnter);
        etMail=findViewById(R.id.etMail);
        etTel=findViewById(R.id.etTel);
        etBday=findViewById(R.id.etBday);
        btnRegister=findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty() ||etSur.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()
                || etReEnter.getText().toString().isEmpty() || etTel.getText().toString().isEmpty()  || etBday.getText().toString().isEmpty() )
                    Toast.makeText(Register.this, "Please enter all details!", Toast.LENGTH_SHORT).show();
                else
                {
                    if(etPassword.getText().toString().equals(etReEnter.getText().toString()))
                    {
                        String name=etName.getText().toString().trim();
                        String surname=etSur.getText().toString().trim();
                        String mail=etMail.getText().toString().trim();
                        String passwword=etPassword.getText().toString().trim();
                        String bday=etBday.getText().toString().trim();
                        String tel=etTel.getText().toString().trim();

                        BackendlessUser user=new BackendlessUser();
                        user.setEmail(mail);
                        user.setPassword(passwword);
                        user.setProperty("name",name);
                        user.setProperty("bday",bday);
                        user.setProperty("telephone",tel);
                        user.setProperty("surname",surname);

                        showProgress(true);

                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {

                                showProgress(false);
                                Toast.makeText(Register.this, "User succesfully registered", Toast.LENGTH_SHORT).show();
                                Register.this.finish();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(Register.this, "Error:"+fault.getMessage(), Toast.LENGTH_SHORT).show();
                              showProgress(false);
                            }
                        });

                    }
                    else
                    {
                        Toast.makeText(Register.this, "Please make sure that password and re-type password is the same!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}