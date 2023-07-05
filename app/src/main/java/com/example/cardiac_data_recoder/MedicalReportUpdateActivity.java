package com.example.cardiac_data_recoder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class MedicalReportUpdateActivity extends AppCompatActivity {
    TextView date_picker, time_picker;
    EditText systolic_pressure, diastolic_pressure, heart_rate, comment;

    String[] data = new String[6];
    Button update_record;

    int year, month, day;
    int hour, min, sec;
    DatabaseReference databaseReference;
    FirebaseAuth mAuth;

    String recordKey;

    MedicalReportModel currMedReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_report_update);

        mAuth = FirebaseAuth.getInstance();
        final Calendar calendar = Calendar.getInstance();
        date_picker = findViewById(R.id.record_date_update);
        time_picker = findViewById(R.id.record_time_update);
        systolic_pressure = findViewById(R.id.systolic_pressure_update);
        diastolic_pressure = findViewById(R.id.diastolic_pressure_update);
        heart_rate = findViewById(R.id.heart_rate_update);
        comment = findViewById(R.id.comment_update);
        update_record = findViewById(R.id.update_the_record);





        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MedicalReportUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(MedicalReportUpdateActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                        time_picker.setText(hours + " : " + minutes);
                    }
                }, hour, min, false);
                timePickerDialog.show();
            }
        });



        databaseReference = FirebaseDatabase.getInstance().getReference().child("MedicalReport");

        update_record.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                updateReport();
                Intent intent = new Intent(MedicalReportUpdateActivity.this, DataViewerActivity.class);
                startActivity(intent);
            }

        });


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            recordKey = extras.getString("recordKey");
            data[0] = extras.getString("date");
            data[1] = extras.getString("time");
            data[2] = extras.getString("sys");
            data[3] = extras.getString("dys");
            data[4] = extras.getString("hr");
            data[5] = extras.getString("cmn");

            try {
                date_picker.setText(data[0]);
                time_picker.setText(data[1]);
                systolic_pressure.setText(data[2]);
                diastolic_pressure.setText(data[3]);
                heart_rate.setText(data[4]);
                comment.setText(data[5]);
            }catch (Exception e){
                Log.e("_ERROR_", e.getMessage());
            }
        }

    }
    public void updateReport(){
        String updatedDate = date_picker.getText().toString();
        String updatedTime = time_picker.getText().toString();
        String updatedSystolic = systolic_pressure.getText().toString();
        String updatedDiastolic = diastolic_pressure.getText().toString();
        String updatedHeartRate = heart_rate.getText().toString();
        String updatedComment = comment.getText().toString();

        Map<String, Object> updatedData = new HashMap<>();
        updatedData.put("date", updatedDate);
        updatedData.put("time", updatedTime);
        updatedData.put("systolic", updatedSystolic);
        updatedData.put("diastolic", updatedDiastolic);
        updatedData.put("heartRate", updatedHeartRate);
        updatedData.put("comment", updatedComment);

        try {
            DatabaseReference childRef = databaseReference.child(mAuth.getCurrentUser().getUid()).child(recordKey);
            childRef.updateChildren(updatedData);
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
