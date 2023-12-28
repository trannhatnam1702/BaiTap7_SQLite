package com.example.baitap7_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
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
    private int id;
    private int status;
    private void refreshListView() {
        ToDoDAO todoDAO = new ToDoDAO(MainActivity.this);
        ArrayList<ToDo> list = todoDAO.getListToDo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(MainActivity.this, list);
        toDoList.setAdapter(toDoAdapter);
    }
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

        toDoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToDo selectedToDo = (ToDo) parent.getItemAtPosition(position);

                edtTitle.setText(selectedToDo.getTitle());
                edtContent.setText(selectedToDo.getContent());
                edtDate.setText(selectedToDo.getDate());
                edtType.setText(selectedToDo.getType());

                MainActivity.this.id = selectedToDo.getId();
                MainActivity.this.status = selectedToDo.getStatus();
            }
        });

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
                ToDo newToDo = new ToDo(id,title, content, date, type,0);

                boolean isSuccess = toDoDAO.addToDo(newToDo);

                if (isSuccess) {
                    Toast.makeText(MainActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT).show();
                    ArrayList<ToDo> list = toDoDAO.getListToDo();
                    ToDoAdapter toDoAdapter = new ToDoAdapter(MainActivity.this, list);
                    toDoList.setAdapter(toDoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Thêm mới thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ToDoDAO todoDAO = new ToDoDAO(MainActivity.this);
                                todoDAO.deleteToDo(id);
                                refreshListView();
                                edtTitle.setText("");
                                edtContent.setText("");
                                edtDate.setText("");
                                edtType.setText("");
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                String date = edtDate.getText().toString();
                String type = edtType.getText().toString();
                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(content) || TextUtils.isEmpty(date) || TextUtils.isEmpty(type)) {
                    Toast.makeText(MainActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Bạn có chắc chắn muốn cập nhật không?")
                            .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ToDo updatedToDo = new ToDo(id, title, content, date, type, 0);
                                    ToDoDAO todoDAO = new ToDoDAO(MainActivity.this);
                                    todoDAO.Update(updatedToDo);
                                    refreshListView();

//                                    edtTitle.setText("");
//                                    edtContent.setText("");
//                                    edtDate.setText("");
//                                    edtType.setText("");
                                }
                            })
                            .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            }
        });
    }
}