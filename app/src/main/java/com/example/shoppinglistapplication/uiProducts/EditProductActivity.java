package com.example.shoppinglistapplication.uiProducts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppinglistapplication.R;
import com.example.shoppinglistapplication.entity.Category;
import com.example.shoppinglistapplication.helpfulModel.DataValidator;
import com.example.shoppinglistapplication.uiCategories.CategoriesActivity;
import com.example.shoppinglistapplication.uiCategories.EditCategoryActivity;
import com.example.shoppinglistapplication.viewmodel.CategoryViewModel;
import com.example.shoppinglistapplication.viewmodel.ProductViewModel;

public class EditProductActivity extends AppCompatActivity {

    public static final String KEY_EDIT_PRODUCT_NAME = "editProductName";
    public static final String KEY_EDIT_PRODUCT_ID = "editProductID";
    private EditText editProductName;
    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_name);

        int idProduct = (int) getIntent().getSerializableExtra(KEY_EDIT_PRODUCT_ID);

        editProductName = findViewById(R.id.new_element_name);
        editProductName.setHint(R.string.hint_edit_name_product);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            if (TextUtils.isEmpty(editProductName.getText())) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setView(this.getLayoutInflater().inflate(R.layout.dialog_wrong_data, null))
                        .setTitle("Niepoprawne dane")
                        .setMessage(R.string.empty_not_saved)
                        .setPositiveButton("Podaj poprawn?? nazw??", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                        .setNegativeButton("Anuluj dodawanie produktu", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"Anulowano dodawanie nowego produktu",Toast.LENGTH_LONG).show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                new Thread(() -> {
                    productViewModel = new ProductViewModel(this.getApplication());
                    DataValidator validator = new DataValidator();
                    String newProductName = validator.validateName(editProductName.getText().toString());

                    Intent intent = new Intent(EditProductActivity.this, ProductsActivity.class);
                    if (!productViewModel.productExists(newProductName)) {
                        productViewModel.updateProductName(idProduct, newProductName, emptyFunction -> {});
                    } else {
                        intent.putExtra(ProductsActivity.KEY_PRODUCT_INFO, "editingNameExists");
                        intent.putExtra(EditProductActivity.KEY_EDIT_PRODUCT_ID, idProduct);
                    }
                    startActivity(intent);
                    finish();
                }).start();
            }
        });
    }
}