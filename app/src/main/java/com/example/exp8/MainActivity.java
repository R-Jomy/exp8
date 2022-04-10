package com.example.exp8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbhelper;
    private String[] data = { "书名                       类别              作者              价格",
            "《时间简史》        科学               霍金           39.9  ",
            "《红楼梦》             文学            曹雪芹         49.9  ",
            "《共产党宣言》     思政            马克思         39.9  "};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        dbhelper = new MyDatabaseHelper(this,"library.db",null,2);
        Button createDatabase = (Button) findViewById(R.id.create_database);
        Button addBook = (Button) findViewById(R.id.add_book);

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhelper.getWritableDatabase();
            }
        });

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);
                SQLiteDatabase db =dbhelper.getWritableDatabase();
                ContentValues values =new ContentValues();

                values.put("category_name","science");
                values.put("category_code",1);
                db.insert("Category",null,values);
                values.clear();
                values.put("category_name","military");
                values.put("category_code",2);
                db.insert("Category",null,values);
            }
        });
    }
}