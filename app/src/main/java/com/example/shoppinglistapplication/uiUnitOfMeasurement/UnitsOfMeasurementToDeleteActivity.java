package com.example.shoppinglistapplication.uiUnitOfMeasurement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.adapterholder.FormOfAccessibilityListAdapter2;
import com.example.shoppinglistapplication.adapterholder.UnitOfMeasurementListAdapter2;
import com.example.shoppinglistapplication.viewmodel.FormOfAccessibilityViewModel;
import com.example.shoppinglistapplication.viewmodel.UnitOfMeasurementViewModel;

public class UnitsOfMeasurementToDeleteActivity extends AppCompatActivity {

    private UnitOfMeasurementViewModel unitOfMeasurementViewModel;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_deleting_editing);

        subtitle = findViewById(R.id.subtitle_text_view);
        subtitle.setText("Wybierz jednostkę, którą chcesz usunąć:");

        unitOfMeasurementViewModel = new UnitOfMeasurementViewModel(this.getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UnitOfMeasurementListAdapter2 adapter = new UnitOfMeasurementListAdapter2(new UnitOfMeasurementListAdapter2.UnitOfMeasurementDiff(), 4);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        unitOfMeasurementViewModel.getAllUnits().observe(this, units -> {
            adapter.submitList(units);
        });
    }
}