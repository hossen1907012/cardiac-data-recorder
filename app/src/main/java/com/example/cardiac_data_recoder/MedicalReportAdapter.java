//package com.example.cardiac_data_recoder;
//
//import androidx.annotation.NonNull;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//import java.util.ArrayList;
//
//
//
//public class MedicalReportAdapter extends RecyclerView.Adapter<MedicalReportAdapter.MyViewHolder> {
//    Context context;
//    ArrayList<MedicalReportModel> allReports;
//    //My edit
//    ArrayList<String> reportKeys;
//    //* 88
//    private EditButtonClickListener editButtonClickListener;
//
//    public void setEditButtonClickListener(EditButtonClickListener listener) {
//        this.editButtonClickListener = listener;
//    }
//    //My edit;*/
//
//
//    public MedicalReportAdapter(Context context, ArrayList<MedicalReportModel>list){
//        this.context = context;
//        this.allReports = list;
//
//    }
//
//
//    //My delete
//
//    private DeleteButtonClickListener deleteButtonClickListener;
//    //My delete
//
//    public interface DeleteButtonClickListener {
//        void onDeleteButtonClick(int position);
//    }
//
//
//    //My delete
//
//    public void setDeleteButtonClickListener(DeleteButtonClickListener listener) {
//        this.deleteButtonClickListener = listener;
//    }
//
//
//
//    //My delete
//
//
//
//    //My edit
//
//    public void setReportKeys(ArrayList<String> reportKeys) {
//        this.reportKeys = reportKeys;
//    }
//
//    //My edit*/
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        TextView s, g, n,glu,so,u;
//        Button editButton;
//        Button deleteButton;
//        public MyViewHolder(@NonNull View itemView, DeleteButtonClickListener deleteButtonClickListener) {
//            super(itemView);
//            s = itemView.findViewById(R.id.sugarView);
//            g = itemView.findViewById(R.id.gasView);
//            n = itemView.findViewById(R.id.nillView);
//            so = itemView.findViewById(R.id.sodiumView);
//            u = itemView.findViewById(R.id.acidView);
//            glu = itemView.findViewById(R.id.glucoseView);
//
//            //*My edit
//            editButton=itemView.findViewById(R.id.buttone);
//            //My edit*/
//
//            //My delete
//            deleteButton = itemView.findViewById(R.id.buttond);
//            deleteButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (deleteButtonClickListener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            deleteButtonClickListener.onDeleteButtonClick(position);
//                        }
//                    }
//                }
//            });
//
//
//            //My delete
//
//
//        }
//    }
//
//    @NonNull
//    @Override
//    public MedicalReportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v= LayoutInflater.from(context).inflate(R.layout.p_medical_report_card,parent,false);
//        return new MyViewHolder(v,deleteButtonClickListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MedicalReportAdapter.MyViewHolder holder, int position) {
//        MedicalReportModel medicalReportModel = allReports.get(position);
//        holder.s.setText(medicalReportModel.getDate());
//        holder.g.setText(medicalReportModel.getTime());
//        holder.n.setText(medicalReportModel.getSystolic());
//        holder.so.setText(medicalReportModel.getDiastolic());
//        holder.u.setText(medicalReportModel.getHeartRate());
//        holder.glu.setText(medicalReportModel.getComment());
//
//
//
//        //*My edit 88
//        holder.editButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    // Get the key of the clicked database record
//                    String key = reportKeys.get(position);
//
//                    // Invoke the listener callback
//                    if (editButtonClickListener != null) {
//                        editButtonClickListener.onEditButtonClick(key);
//                    }
//                } catch (IndexOutOfBoundsException e) {
//                    // Handle index out of bounds exception
//                    e.printStackTrace();
//                } catch (Exception e) {
//                    // Handle any other exceptions
//                    e.printStackTrace();
//                }
//            }
//        });
//        //My edit*/
//
//        //My delete
//
//        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (deleteButtonClickListener != null) {
//                    deleteButtonClickListener.onDeleteButtonClick(position);
//                }
//            }
//        });
//        //my delete
//
//
//    }
//    //*My edit 88
//
//    public interface EditButtonClickListener {
//        void onEditButtonClick(String key);
//    }
//
//
//    //My edit*/
//
//
//
//    @Override
//    public int getItemCount() {
//        return allReports.size();
//    }
//
//
//}