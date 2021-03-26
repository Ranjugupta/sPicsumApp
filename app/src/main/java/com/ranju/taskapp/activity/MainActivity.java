package com.ranju.taskapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.ranju.taskapp.R;
import com.ranju.taskapp.api.AllApiClass;
import com.ranju.taskapp.api.Holders;
import com.ranju.taskapp.model.PicsModel;
import com.ranju.taskapp.views.ImgAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private RecyclerView recImg;
    private ArrayList<PicsModel> picsModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recImg = findViewById(R.id.recImg);
        recImg.setLayoutManager(new GridLayoutManager(this,2));
        getAllData();
    }

    private void getAllData() {
        Holders holders = AllApiClass.getInstance().getApi();
        Call<List<PicsModel>> sumResCall=holders.images();
        sumResCall.enqueue(new Callback<List<PicsModel>>() {
            @Override
            public void onResponse(Call<List<PicsModel>> call, Response<List<PicsModel>> response) {
                if (response.isSuccessful())
                    Log.d(TAG, "onResponse: "+response.body().size());
                picsModels = new ArrayList<>();
                for (int i=0;i<response.body().size();i++){
                    PicsModel model = response.body().get(i);
                    picsModels.add(model);
                }
                ImgAdapter adapter = new ImgAdapter(picsModels,MainActivity.this);
                recImg.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<PicsModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}