package com.shresthagaurav.esoftwarica.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shresthagaurav.esoftwarica.R;
import com.shresthagaurav.esoftwarica.adpter.AdapterRecycleView;
import com.shresthagaurav.esoftwarica.apiclass.ApiCall;
import com.shresthagaurav.esoftwarica.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
       recyclerView= root.findViewById(R.id.Rvlist);
        ApiCall apiCall = new ApiCall();
        final Call<List<Employee>> listCall = apiCall.calls().getallEmployee();
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "error"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.d("error","error" +response.code() );
                    return;
                }
                List<Employee>listemp=response.body();
                AdapterRecycleView adapterRecycleView = new AdapterRecycleView(getContext(),listemp);
                recyclerView.setAdapter(adapterRecycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(getContext(), "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("error","error   "+t.getLocalizedMessage() );

            }
        });
        return root;
    }

}