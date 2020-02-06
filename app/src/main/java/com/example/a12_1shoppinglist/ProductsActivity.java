package com.example.a12_1shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

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

public class ProductsActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    EditText editText;
    int choiceItemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        arrayList = new ArrayList<>();
        listView =findViewById(R.id.list_view_products);
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,arrayList);
        listView.setAdapter(arrayAdapter);
        editText = findViewById(R.id.edit_text_products);

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
            Toast toast = Toast.makeText(this, "Добавьте покупку !",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        } else {
            arrayList.add(item);
            arrayAdapter.notifyDataSetChanged();
            editText.setText("");
        }
    }

    public void onClickButtonRemove(View view) {
        if (arrayList.isEmpty()){
            Toast toast = Toast.makeText(this, "Список уже пуст !",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        } else {
            arrayList.remove(choiceItemPosition);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
