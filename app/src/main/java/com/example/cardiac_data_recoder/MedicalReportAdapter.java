package com.example.cardiac_data_recoder;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;




public class MedicalReportAdapter extends RecyclerView.Adapter<MedicalReportAdapter.MyViewHolder> {
    Context context;
    ArrayList<MedicalReportModel> allReports;

    String[] data = new String[6];

    ArrayList<String> reportKeys;

    private EditButtonClickListener editButtonClickListener;
    private DeleteButtonClickListener deleteButtonClickListener;

    public void setEditButtonClickListener(EditButtonClickListener listener) {
        this.editButtonClickListener = listener;
    }


    public MedicalReportAdapter(Context context, ArrayList<MedicalReportModel>list){
        this.context = context;
        this.allReports = list;

    }

    public interface DeleteButtonClickListener {
        void onDeleteButtonClick(int position);
    }


    public void setDeleteButtonClickListener(DeleteButtonClickListener listener) {
        this.deleteButtonClickListener = listener;
    }

    public void setReportKeys(ArrayList<String> reportKeys) {
        this.reportKeys = reportKeys;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bp_viewer, datetime_viewer, hr_viewer;
        Button editButton, deleteButton;
        public MyViewHolder(@NonNull View itemView, DeleteButtonClickListener deleteButtonClickListener) {
            super(itemView);
            bp_viewer = itemView.findViewById(R.id.bp_viewer);
            hr_viewer = itemView.findViewById(R.id.hr_viewer);
            datetime_viewer = itemView.findViewById(R.id.date_time_view);


            editButton=itemView.findViewById(R.id.edit_rec_button);

            deleteButton = itemView.findViewById(R.id.delete_rec_button);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (deleteButtonClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            deleteButtonClickListener.onDeleteButtonClick(position);
                        }
                    }
                }
            });

        }
    }

    @NonNull
    @Override
    public MedicalReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_data_card,parent,false);
        return new MyViewHolder(v,deleteButtonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MedicalReportAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MedicalReportModel medicalReportModel = allReports.get(position);
        holder.bp_viewer.setText(medicalReportModel.getSystolic() + "/" + medicalReportModel.getDiastolic());
        holder.hr_viewer.setText(medicalReportModel.getHeartRate());
        holder.datetime_viewer.setText(medicalReportModel.getDate() + "  -  "+ medicalReportModel.getTime());

        if(Integer.parseInt(medicalReportModel.getSystolic()) > 140 || Integer.parseInt(medicalReportModel.getSystolic()) < 90 || Integer.parseInt(medicalReportModel.getDiastolic()) < 60 || Integer.parseInt(medicalReportModel.getDiastolic()) > 90){
            holder.bp_viewer.setTextColor(Color.rgb(255, 20,20));
        }

        data[0] = medicalReportModel.getDate();
        data[1] = medicalReportModel.getTime();
        data[2] = medicalReportModel.getSystolic();
        data[3] = medicalReportModel.getDiastolic();
        data[4] = medicalReportModel.getHeartRate();
        data[5] = medicalReportModel.getComment();


        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String key = reportKeys.get(position);
                    if (editButtonClickListener != null) {
                        editButtonClickListener.onEditButtonClick(key, data);
                    }
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("__ERROR__", e.getMessage());
                }
            }
        });


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteButtonClickListener != null) {
                    deleteButtonClickListener.onDeleteButtonClick(position);
                }
            }
        });


    }

    public interface EditButtonClickListener {
        void onEditButtonClick(String key, String[] data);
    }


    @Override
    public int getItemCount() {
        return allReports.size();
    }
}