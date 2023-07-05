package com.example.cardiac_data_recoder.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cardiac_data_recoder.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        mAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.forgetmail1);
        reset=findViewById(R.id.forgetpassbutton);





        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkmail=email.getText().toString().trim();
                if(checkmail.isEmpty())
                {
                    email.setError("Enter Email Address");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(checkmail).matches())
                {
                    email.setError("Enter a Valid Email Address");
                    email.requestFocus();
                    return;
                }

                mAuth.sendPasswordResetEmail(checkmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(forgotpassword.this, "Check Mail for Resetting Password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),loginactivity1.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(forgotpassword.this, "Error: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });



            }
        });


    }
}