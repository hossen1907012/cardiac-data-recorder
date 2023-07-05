package com.example.cardiac_data_recoder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertReportActivity extends AppCompatActivity {

    TextView date_picker, time_picker;
    EditText systolic_pressure, diastolic_pressure, heart_rate, comment;
    Button add_new_record;
    int year, month, day;
    int hour, min, sec;

    String date_string, time_string;

    DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_report);

        mAuth = FirebaseAuth.getInstance();

        final Calendar calendar = Calendar.getInstance();

        date_picker = findViewById(R.id.record_date);
        time_picker = findViewById(R.id.record_time);
        systolic_pressure = findViewById(R.id.systolic_pressure);
        diastolic_pressure = findViewById(R.id.diastolic_pressure);
        heart_rate = findViewById(R.id.heart_rate);
        comment = findViewById(R.id.comment);
        add_new_record = findViewById(R.id.add_new_record);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("MedicalReport");

        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date_string = SimpleDateFormat.getDateInstance().format(calendar.getTime());
                        date_picker.setText(date_string);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(InsertReportActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                        time_string = hours + " : " + minutes;
                        time_picker.setText(time_string);
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });

        add_new_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertRecord();
            }
        });
    }


    private void insertRecord(){
        String date_txt = date_picker.getText().toString();
        String time_txt = time_picker.getText().toString();
        String systolic_txt =systolic_pressure.getText().toString();
        String diastolic_txt =diastolic_pressure.getText().toString();
        String heart_rate_text = heart_rate.getText().toString();
        String comment_txt =comment.getText().toString();

        MedicalReportModel medicalReportModel = new MedicalReportModel(

                date_string,
                time_string,
                systolic_txt,
                diastolic_txt,
                heart_rate_text,
                comment_txt
        );
        try {
            databaseReference.child(mAuth.getCurrentUser().getUid()).push().setValue(medicalReportModel);
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();
            Thread.sleep(100);
            startActivity(new Intent(InsertReportActivity.this, DataViewerActivity.class));
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}