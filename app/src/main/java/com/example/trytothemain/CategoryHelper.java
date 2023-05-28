package com.example.trytothemain;

import android.content.Context;

import java.util.ArrayList;

public class CategoryHelper {
    myDbCategory helper;

    public CategoryHelper(Context context) {

        helper = new myDbCategory(context);
    }

    public long insert(String cate) {
        long id = helper.insertData(cate);
        return id;
    }

    public String getData(){
        String data = helper.getData();
        return data;
    }

    public ArrayList<String> getArrayData(){
        return  helper.getArrayData();
    }

    public int delete(String category){
        return helper.delete(category);
    }
    public int updateName(String oldCategory , String newCategory){
        return helper.updateName(oldCategory , newCategory);

    }


}
