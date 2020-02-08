package com.geektech.film.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Example;
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
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_search);
        button = findViewById(R.id.next);
        setRecyclerview();
        fetchfilm();
    }

    private void setRecyclerview() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }


    private void fetchfilm() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitBuilder.getService().fetchFilm(editText.getText().toString().trim(), "31b20f2f")
                        .enqueue(new Callback<Example>() {
                            @Override
                            public void onResponse(Call<Example> call, Response<Example> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    setUpAdapter(response.body().getWebsite());
                                }
                            }

                            @Override
                            public void onFailure(Call<Example> call, Throwable t) {
                                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }


    private void setUpAdapter(String title) {
        adapter.update("ddddddd");

    }
}
