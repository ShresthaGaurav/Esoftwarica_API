package com.shresthagaurav.esoftwarica.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.shresthagaurav.esoftwarica.EditEmployees;
import com.shresthagaurav.esoftwarica.MainActivity;
import com.shresthagaurav.esoftwarica.R;
import com.shresthagaurav.esoftwarica.apiclass.ApiCall;
import com.shresthagaurav.esoftwarica.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {
    EditText etName, etSalary, etage;
    Button btnRegister;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        etName = root.findViewById(R.id.et_name);
        etage = root.findViewById(R.id.et_age);
        etSalary = root.findViewById(R.id.et_salary);
        btnRegister = root.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditEmployees ed = new EditEmployees();

                ed.CheckEmpty(etName,etSalary,etage);
                EmployeeCUD employeeCUD = new EmployeeCUD(etName.getText().toString(), Float.parseFloat(etSalary.getText().toString()), Integer.parseInt(etage.getText().toString()), "");

                ApiCall apiCall = new ApiCall();
                final Call<Void> listCall = apiCall.calls().register(employeeCUD);
                listCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(), "user created", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "user not created", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
        return root;
    }

}