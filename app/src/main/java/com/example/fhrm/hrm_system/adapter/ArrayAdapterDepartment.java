package com.example.fhrm.hrm_system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.models.Department;

import java.util.List;

/**
 * Created by luuhoangtruc on 22/01/2016.
 */
public class ArrayAdapterDepartment extends ArrayAdapter<Department> {

    Context context;
    List<Department> objects;

    public ArrayAdapterDepartment(Context context, int resource, List<Department> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    public View getView(int pos, View convertView, ViewGroup viewParent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_department_layout, viewParent, false);
        TextView textView = (TextView)rowView.findViewById(R.id.text_item_department);
        textView.setText(objects.get(pos).getNameDepartment());
        return rowView;
    }
}
