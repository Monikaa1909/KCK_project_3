package com.example.shoppinglistapplication.uiCategories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.CategoryListAdapter;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;

public class CategoriesToEditActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        categoryViewModel = new CategoryViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final CategoryListAdapter adapter = new CategoryListAdapter(new CategoryListAdapter.CategoryDiff(), 5);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryViewModel = new CategoryViewModel(this.getApplication());
        categoryViewModel.getAllCategory().observe(this, categories -> {
            adapter.submitList(categories);
        });
    }
}