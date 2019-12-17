package com.shresthagaurav.esoftwarica;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.shresthagaurav.esoftwarica.apiclass.ApiCall;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteApiData {

    public  void Deletedata(int id, final Context context) {

        Log.d("error", "error" +id);
        ApiCall apiCall = new ApiCall();
        Call<Void> voidCall = apiCall.calls().deleteEmployee(id);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.d("error", "error" + response.code());
                    Toast.makeText(context, "error"+response.code(), Toast.LENGTH_SHORT).show();

                    return;
                }
                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,MainActivity.class);
               context.startActivity(intent);

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText( context, "error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                Log.d("error", "error   " + t.getLocalizedMessage());

            }
        });

       
    }
}
