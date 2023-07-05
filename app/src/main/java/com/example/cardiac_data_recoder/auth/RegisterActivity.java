package com.example.cardiac_data_recoder.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardiac_data_recoder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText email1, password12, confirmpassword1;
    Button register;
    TextView redirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email1=findViewById(R.id.emailField);
        password12=findViewById(R.id.passwordField);
        confirmpassword1=findViewById(R.id.confirmPasswordField);
        register=findViewById(R.id.register);
        redirect=findViewById(R.id.redirectRegister);

        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();
            }
        });


        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),loginactivity1.class));
            }
        });

    }

    private void createuser() {

        String email=email1.getText().toString().trim();
        String password1=password12.getText().toString().trim();
        String password2=confirmpassword1.getText().toString().trim();


        if(email.isEmpty())
        {
            email1.setError("Please Enter Email Address");
            email1.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            email1.setError("Enter a Valid Email Address");
            email1.requestFocus();
            return;
        }
        if(password1.isEmpty())
        {
            password12.setError("Enter a Password");
            password12.requestFocus();
            return;
        }
        if(password2.isEmpty())
        {
            password12.setError("Enter Confirm Password");
            password12.requestFocus();
            return;
        }
        if(!password1.equals(password2))
        {
            confirmpassword1.setError("Password not Matched!");
            confirmpassword1.requestFocus();
            return;
        }
        if(password1.length()<6)
        {
            password12.setError("Enter Valid Password");
            password12.requestFocus();

        }
        if(password2.length()<6)
        {
            confirmpassword1.setError("Enter Valid Password");
            confirmpassword1.requestFocus();

        }

        mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Registration is Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),loginactivity1.class);
                    startActivity(intent);
                }
                else
                {
                    if(task.getException()instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(RegisterActivity.this, "User is already Registered", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),loginactivity1.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });



    }
}