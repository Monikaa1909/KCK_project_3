package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapter.FormOfAccessibilityListAdapter;
import com.example.shoppinglistapplication.entity.FormOfAccessibility;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductFormsActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_with_add_delete_button);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Formy dostępności produktu:");

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final FormOfAccessibilityListAdapter adapter = new FormOfAccessibilityListAdapter(new FormOfAccessibilityListAdapter.FormOfAccessibilityDiff(), 0);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int idProduct = (int) getIntent().getSerializableExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID);
        new Thread(() -> {
            productViewModel = new ProductViewModel(this.getApplication());

            FloatingActionButton addFab = findViewById(R.id.fab_add);
            addFab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductFormsActivity.this, NewProductFormActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                startActivity(intent);
                this.finish();
            });

            FloatingActionButton deleteFab = findViewById(R.id.fab_delete);
            deleteFab.setOnClickListener(view -> {
                Intent intent = new Intent(ProductFormsActivity.this, ProductFormDeleteActivity.class);
                intent.putExtra(ProductDetailsActivity.KEY_PRODUCT_DETAIL_ID, idProduct);
                startActivity(intent);
                this.finish();
            });

            List<FormOfAccessibility> unitsList = productViewModel.getProductForm(idProduct);
            adapter.submitList(unitsList);
        }).start();
    }
}