package com.shresthagaurav.esoftwarica.apiclass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {
    public EmployeeAPI calls(){
         String base_url = "http://dummy.restapiexample.com/api/v1/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
return employeeAPI;
    }
    }
