package com.geektech.film.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.film.R;
import com.geektech.film.data.internet.RetrofitBuilder;
import com.geektech.film.ui.main.recyclerview.Adapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Adapter adapter;
    private  Object[] keys;
    private ArrayList<String> values;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_search);
        setRecyclerview();
        fetchfilm();
    }

    private void setRecyclerview(){
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }


    private void fetchfilm(){
        RetrofitBuilder.getService().fetchFilm("joker","31b20f2f")
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            parseJson(response.body());

                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void parseJson(JsonObject body){
        JsonObject rates = body.getAsJsonObject("rates");
        keys = rates.keySet().toArray();
        values = new ArrayList<>();
        for (Object item : keys ) {
            values.add(rates.getAsJsonPrimitive(item.toString()).toString());
        }
        setUpAdapter();
    }

    private void setUpAdapter(){
        adapter.update(values);
    }
}
