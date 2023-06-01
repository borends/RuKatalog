package com.example.rukatalog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSmartphone = findViewById(R.id.buttonSmartphone);
        Button buttonLaptop = findViewById(R.id.buttonLaptop);
        Button buttonGadgets = findViewById(R.id.buttonGadgets);

        buttonSmartphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(MainActivity.this, ProductActivity.class);
                switcher.putExtra("url", "https://dummyjson.com/products/category/smartphones");
                startActivity(switcher);
            }
        });

        buttonLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(MainActivity.this, ProductActivity.class);
                switcher.putExtra("url", "https://dummyjson.com/products/category/laptops");
                startActivity(switcher);
            }
        });

        buttonGadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switcher = new Intent(MainActivity.this, ProductActivity.class);
                switcher.putExtra("url", "https://dummyjson.com/products/category/automotive");
                startActivity(switcher);
            }
        });
    }
}
