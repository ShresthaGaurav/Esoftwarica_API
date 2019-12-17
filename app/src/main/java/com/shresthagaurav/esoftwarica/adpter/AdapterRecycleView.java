package com.shresthagaurav.esoftwarica.adpter;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shresthagaurav.esoftwarica.DeleteApiData;
import com.shresthagaurav.esoftwarica.EditEmployees;
import com.shresthagaurav.esoftwarica.R;
import com.shresthagaurav.esoftwarica.model.Employee;

import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ContactsViewHolder> {
    Context context;
    List<Employee> show_employees;

    public AdapterRecycleView(Context context, List<Employee> show_employees) {
        this.context = context;
        this.show_employees = show_employees;
    }

    @NonNull
    @Override
    public AdapterRecycleView.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recycle_v, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleView.ContactsViewHolder holder, int position) {

        final Employee se = show_employees.get(position);
        holder.txtid.append(String.valueOf(se.getId()));
        holder.txtname.append(se.getEmployee_name());
        holder.txtsalary.append(String.valueOf(se.getEmployee_salary()));
        holder.txtage.append(String.valueOf(se.getEmployee_age()));
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditEmployees.class);
                intent.putExtra("id", se.getId());
                intent.putExtra("name", se.getEmployee_name());
                intent.putExtra("salary", String.valueOf(se.getEmployee_salary()));
                intent.putExtra("age", String.valueOf(se.getEmployee_age()));
                context.startActivity(intent);
            }
        });
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = se.getId();
                DeleteApiData deleteApiData = new DeleteApiData();
               deleteApiData.Deletedata(id,context);
               notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return show_employees.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView txtname, txtage, txtid, txtsalary;
        ImageButton btn_edit, btn_del;

        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.rvname);
            txtid = itemView.findViewById(R.id.rvid);
            txtsalary = itemView.findViewById(R.id.rvsalary);
            txtage = itemView.findViewById(R.id.rvage);
            btn_del = itemView.findViewById(R.id.rvdelete);
            btn_edit = itemView.findViewById(R.id.rvedit);
        }
    }

}