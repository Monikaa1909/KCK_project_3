package com.example.shoppinglistapplication.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(primaryKeys = {"idShoppingList", "idProduct"},
        foreignKeys = {
                @ForeignKey(
                        entity = ShoppingList.class,
                        parentColumns = "idShoppingList",
                        childColumns = "idShoppingList",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = Product.class,
                        parentColumns = "idProduct",
                        childColumns = "idProduct",
                        onDelete = CASCADE)})

public class CompositionOfTheShoppingList {

    @NonNull
    private int idShoppingList;
    @NonNull
    private int idProduct;

    @NonNull
    private Integer quantity;

//    private String suggestedFormOfAccessibility;

    public int getIdShoppingList() {
        return idShoppingList;
    }

    public int getIdProduct(){
        return idProduct;
    }

    public void setIdShoppingList(int idShoppingList) {
        this.idShoppingList = idShoppingList;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @NonNull
    public Integer getQuantity() { return quantity; }

    public void setQuantity(@NonNull Integer quantity) { this.quantity = quantity; }

//    @NonNull
//    public String getSuggestedFormOfAccessibility() { return suggestedFormOfAccessibility; }
//
//    public void setSuggestedFormOfAccessibility(@NonNull String suggestedFormOfAccessibility) { this.suggestedFormOfAccessibility = suggestedFormOfAccessibility; }

//    public CompositionOfTheShoppingList(@NonNull Integer quantity, @NonNull String suggestedFormOfAccessibility, @NonNull int idShoppingList, @NonNull int idProduct) {
//        this.quantity = quantity;
//        this.suggestedFormOfAccessibility = suggestedFormOfAccessibility;
//        this.idProduct = idProduct;
//        this.idShoppingList = idShoppingList;
//    }

    public CompositionOfTheShoppingList(@NonNull Integer quantity, @NonNull int idShoppingList, @NonNull int idProduct) {
        this.quantity = quantity;
        this.idProduct = idProduct;
        this.idShoppingList = idShoppingList;
//        this.suggestedFormOfAccessibility = "null";
    }
}

