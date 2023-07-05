package com.example.cardiac_data_recoder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertReportActivity extends AppCompatActivity {

    TextView date_picker, time_picker;
    EditText systolic_pressure, diastolic_pressure, heart_rate, comment;
    Button add_new_record;

    int year, month, day;
    int hour, min, sec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_report);

        final Calendar calendar = Calendar.getInstance();

        date_picker = findViewById(R.id.record_date);
        time_picker = findViewById(R.id.record_time);
        systolic_pressure = findViewById(R.id.systolic_pressure);
        diastolic_pressure = findViewById(R.id.diastolic_pressure);
        heart_rate = findViewById(R.id.heart_rate);
        comment = findViewById(R.id.comment);
        add_new_record = findViewById(R.id.add_new_record);


        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertReportActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date_picker.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));
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
                        time_picker.setText(hours + " : " + minutes);
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });
    }
}