package com.shresthagaurav.esoftwarica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shresthagaurav.esoftwarica.apiclass.ApiCall;
import com.shresthagaurav.esoftwarica.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEmployees extends AppCompatActivity {
    Button btn_edit, btn_dell;
    EditText et_name, et_salary, et_age;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_employees);

        btn_edit = findViewById(R.id.btn_edit);
        btn_dell = findViewById(R.id.btn_dell);
        et_name = findViewById(R.id.up_name);
        et_age = findViewById(R.id.up_age);
        et_salary = findViewById(R.id.up_salary);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            et_name.setText(bundle.getString("name"));
            et_salary.setText(bundle.getString("salary"));
            et_age.setText(bundle.getString("age"));
            id = bundle.getInt("id");

        }
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckEmpty(et_name, et_salary, et_age)) {
                    String name = et_name.getText().toString();
                    Float salary = Float.parseFloat(et_salary.getText().toString());
                    int age = Integer.parseInt(et_age.getText().toString());

                    EmployeeCUD CUD = new EmployeeCUD(name, salary, age, "");
                    ApiCall apiCall = new ApiCall();
                    Call<Void> voidCall = apiCall.calls().updateEmployee(id, CUD);
                    voidCall.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(EditEmployees.this, "error" + response.code(), Toast.LENGTH_SHORT).show();
                                Log.d("error", "error" + response.code());
                                return;
                            }
                            Toast.makeText(EditEmployees.this, "Update", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditEmployees.this, MainActivity.class);
                            startActivity(intent);

                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Toast.makeText(EditEmployees.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("error", "error   " + t.getLocalizedMessage());

                        }
                    });

                }
            }
        });
        btn_dell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteApiData deleteApiData = new DeleteApiData();
                deleteApiData.Deletedata(id, EditEmployees.this);

            }
        });
    }

  public  boolean CheckEmpty(EditText et_name, EditText et_salary, EditText et_age) {

        if (!TextUtils.isEmpty(et_name.getText().toString())) {
            if (!TextUtils.isEmpty(et_salary.getText().toString())) {
                if (!TextUtils.isEmpty(et_age.getText().toString())) {
                    return true;

                } else {
                    et_age.setError("enter the age");
                }
            } else {
                et_salary.setError("enter the age");
            }

        } else {
            et_name.setError("enter the age");
        }
        return false;
    }
}
