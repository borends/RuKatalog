package com.example.rukatalog;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    ArrayList<Product> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produt_list);
        loadProductList();
        mRecyclerView = findViewById(R.id.recyclerview);


    }

    private void loadProductList() {
        RequestQueue volleyQueue = Volley.newRequestQueue(ProductActivity.this);
        String url = getIntent().getExtras().getString("url");;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        JSONArray productList = response.getJSONArray("products");

                        for (int i = 0; i < productList.length(); i++) {
                            JSONObject object = productList.getJSONObject(i);
                            String productImageUrl = object.getString("thumbnail");
                            String title = object.getString("title");
                            String description = object.getString("description");
                            String price = ("$ " + object.getString("price"));
                            data.add(new Product(title, price, description, productImageUrl));
                        }

                        AdapterClass adapter = new AdapterClass(data, getApplicationContext());
                        mRecyclerView.setAdapter(adapter);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(ProductActivity.this, "Some error occurred! Cannot fetch data", Toast.LENGTH_LONG).show();
                    Log.e("MainActivity", "loadDogImage error: ${error.localizedMessage}");
                }
        );

        volleyQueue.add(jsonObjectRequest);
    }
}