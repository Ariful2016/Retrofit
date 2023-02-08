package com.arifcit.retrofitia2101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Posts> postsList;
    RecyclerView recyclerView;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postsList = new ArrayList<>();

        recyclerView = findViewById(R.id.postRecycler);

        MyApi myApi = MyRetrofit.getRetrofit().create(MyApi.class);

        Call<List<Posts>> listCall = myApi.getAllPost();

        listCall.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                postsList = response.body();
                if (postsList.size()>0){
                    for (Posts posts : postsList){
                        Log.i("TAG", "id "+ posts.getId());
                    }
                }
                adapter = new PostAdapter(MainActivity.this,postsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {

            }
        });
    }
}