package com.example.cardiac_data_recoder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity1 extends AppCompatActivity implements View.OnClickListener  {


    private FirebaseAuth mAuth;

    EditText email,password;

    TextView signup,forgetpass;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity1);

        email=findViewById(R.id.signupemail1);
        password=findViewById(R.id.signuppassword2);
        signup=findViewById(R.id.signup11);
        forgetpass=findViewById(R.id.forgotpassword1);
        signin=findViewById(R.id.login1);

        mAuth=FirebaseAuth.getInstance();


        signup.setOnClickListener(this);
        forgetpass.setOnClickListener(this);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String saveEmail=email.getText().toString().trim();
                String savePassword=password.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(saveEmail).matches()) {
                    email.setError("Please Enter Your Email Address Completely");
                    email.requestFocus();
                } else if (TextUtils.isEmpty(savePassword)) {
                    password.setError("Please Enter Your Password");
                    password.requestFocus();
                } else if (savePassword.length() < 6) {
                    password.setError("Please Enter a Password of 6 Characters or More");
                    password.requestFocus();
                } else {
                    userlogin();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            /*case R.id.login1:
               userlogin();
               break;*/
            case R.id.signup11:
                signuppage();

                break;
            case R.id.forgotpassword1:
                forgotpass();
                break;



        }
    }

    private void signuppage() {

        Toast.makeText(loginactivity1.this, "Redirecting to Sign Up Page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }


    private void forgotpass()
    {
        Toast.makeText(this, "Redirecting to Reset Password Page", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(),forgotpassword.class));

    }

    private void userlogin()
    {
        String email1=email.getText().toString().trim();
        String pass=password.getText().toString().trim();

        if(email1.isEmpty())
        {
            email.setError("Please Enter Email Address");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
        {
            email.setError("Enter a Valid Email Address");
            email.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            password.setError("Enter a Password");
            password.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            password.setError("Enter Valid Password");
            password.requestFocus();

        }

        mAuth.signInWithEmailAndPassword(email1,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(loginactivity1.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(getApplicationContext(),DetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(loginactivity1.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();

                }


            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(loginactivity1.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity(); // Finish all activities in the task, including the current one
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}