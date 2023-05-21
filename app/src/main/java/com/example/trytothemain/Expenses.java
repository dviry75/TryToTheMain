package com.example.trytothemain;

public class Expenses {

    private int NumExpense;
    private String Category;
    private String Descrabtion;

    public Expenses(int NumExpense , String Category , String Descrabtion){
        this.Category = Category;
        this.Descrabtion = Descrabtion;
        this.NumExpense = NumExpense;
    }











    public void setNumExpense(int numExpense) {
        NumExpense = numExpense;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescrabtion(String descrabtion) {
        Descrabtion = descrabtion;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescrabtion() {
        return Descrabtion;
    }

    public int getNumExpense() {
        return NumExpense;
    }






}
