package com.example.a12_1shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HouseholdGoodsActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    EditText editText;
    int choiceItemPosition;
    private static final String PREFERENCES = "PREFERENCES_HOUSE_GOODS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_goods);

        arrayList = new ArrayList<>();

        SharedPreferences preferencesRestore = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        for (int i = 0; i < preferencesRestore.getInt("length",0); i++){
            arrayList.add(preferencesRestore.getString(String.valueOf(i),""));
        }

        listView = findViewById(R.id.list_view_household_goods);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,arrayList);
        listView.setAdapter(arrayAdapter);
        editText = findViewById(R.id.edit_text_household_goods);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choiceItemPosition = position;
            }
        });
    }

    public void onClickButtonAdd(View view) {
        String item = editText.getText().toString();
        if (item.equals("")){
            Toast toast = Toast.makeText(this, "Добавьте покупку !", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }else {
            arrayList.add(item);
            arrayAdapter.notifyDataSetChanged();
            editText.setText("");
        }
    }

    public void onClickButtonRemove(View view) {
        if (arrayList.isEmpty()){
            Toast toast = Toast.makeText(this,"Список уже пуст !", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        } else {
            arrayList.remove(choiceItemPosition);
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onSaveData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveData();
    }

    void onSaveData(){
        String[] items = arrayList.toArray(new String[0]);
        SharedPreferences preferencesSave = getSharedPreferences(PREFERENCES,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesSave.edit();
        for (int i = 0; i < items.length; i++){
            editor.putString(String.valueOf(i),items[i]);
        }
        editor.putInt("length",items.length);
        editor.apply();
    }
}
