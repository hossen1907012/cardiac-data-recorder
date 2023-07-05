package com.example.cardiac_data_recoder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cardiac_data_recoder.auth.loginactivity1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataViewerActivity extends AppCompatActivity {


    Button add_new_rec, logout;
    RecyclerView recyclerView;
    DatabaseReference database;
    MedicalReportAdapter adapter;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ArrayList<MedicalReportModel> list = new ArrayList<>();
    ArrayList<String> reportKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viewer);


        add_new_rec = findViewById(R.id.redirect_to_add_data);
        logout = findViewById(R.id.logout_button);

        add_new_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataViewerActivity.this, InsertReportActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DataViewerActivity.this, loginactivity1.class));
            }
        });


        try {
            database = FirebaseDatabase.getInstance().getReference("MedicalReport").child(mAuth.getCurrentUser().getUid());

            recyclerView = findViewById(R.id.medical_report_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new MedicalReportAdapter(this, list);


            adapter.setReportKeys(reportKeys);

            adapter.setEditButtonClickListener(new MedicalReportAdapter.EditButtonClickListener() {
                @Override
                public void onEditButtonClick(String key, String[] data) {
                    Intent intent = new Intent(DataViewerActivity.this, MedicalReportUpdateActivity.class);
                    intent.putExtra("recordKey", key);
                    intent.putExtra("date", data[0]);
                    intent.putExtra("time", data[1]);
                    intent.putExtra("sys", data[2]);
                    intent.putExtra("dys", data[3]);
                    intent.putExtra("hr", data[4]);
                    intent.putExtra("cmn", data[5]);
                    startActivity(intent);
                }
            });



        adapter.setDeleteButtonClickListener(new MedicalReportAdapter.DeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(int position) {
                String reportKey = reportKeys.get(position);
                database.child(reportKey).removeValue();
                list.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });

        recyclerView.setAdapter(adapter);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                reportKeys.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MedicalReportModel medReport = dataSnapshot.getValue(MedicalReportModel.class);
                    list.add(medReport);

                    reportKeys.add(dataSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }catch (Exception e) {
            Log.e("__ERROR__", e.getClass().getName() + ":" + e.getMessage());
        }
}
}