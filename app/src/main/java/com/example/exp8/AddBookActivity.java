package com.example.exp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {
    private MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        dbhelper =new MyDatabaseHelper(this,"Category.db",null,2);
        Button adding = (Button) findViewById(R.id.adding);
        adding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db =dbhelper.getWritableDatabase();
                ContentValues values =new ContentValues();


                values.put("author","吴承恩");
                values.put("name","西游记");
                values.put("price",28.9);
                values.put("pages",304);
                db.insert("Book",null,values);
                values.clear();
                Toast.makeText(AddBookActivity.this,"添加成功", Toast.LENGTH_SHORT).show();


            }
        });

    }
}