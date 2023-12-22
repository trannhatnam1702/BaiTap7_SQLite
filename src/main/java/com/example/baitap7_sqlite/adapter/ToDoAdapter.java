package com.example.baitap7_sqlite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.baitap7_sqlite.R;
import com.example.baitap7_sqlite.model.ToDo;
import java.util.List;

public class ToDoAdapter extends ArrayAdapter<ToDo> {
    private Context context;
    private List<ToDo> listData;
    public ToDoAdapter (Context context, List<ToDo> listToDo) {
        super(context, 0, listToDo);
        this.context = context;
        this.listData = listToDo;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public ToDo getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,null);
        }
        TextView toDoTitle = convertView.findViewById(R.id.txtTitle);
        TextView toDoContent = convertView.findViewById(R.id.txtContent);
        TextView toDoDate = convertView.findViewById(R.id.txtDate);

        ToDo temp = this.listData.get(position);
        toDoTitle.setText(temp.getTitle());
        toDoContent.setText(temp.getContent());
        toDoDate.setText(temp.getDate());
        return convertView;
    }
//    static class ViewHolder {
//        TextView toDoTitle, toDoContent, toDoDate;
//    }
}
