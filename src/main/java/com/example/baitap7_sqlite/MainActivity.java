package com.example.baitap7_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.baitap7_sqlite.adapter.ToDoAdapter;
import com.example.baitap7_sqlite.dao.ToDoDAO;
import com.example.baitap7_sqlite.model.ToDo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView toDoList;
    EditText edtTitle, edtContent, edtDate, edtType;
    Button btnAdd, btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToDoDAO toDoDAO = new ToDoDAO(this);
        ArrayList<ToDo> listToDo = toDoDAO.getListToDo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, listToDo);
        toDoList = findViewById(R.id.toDoList);
        toDoList.setAdapter(toDoAdapter);
        edtTitle = findViewById(R.id.edtTitle);
        edtContent = findViewById(R.id.edtContent);
        edtDate = findViewById(R.id.edtDate);
        edtType = findViewById(R.id.edtType);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                String date = edtDate.getText().toString();
                String type = edtType.getText().toString();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content) || TextUtils.isEmpty(date) || TextUtils.isEmpty(type)) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}