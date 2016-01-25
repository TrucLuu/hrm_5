package com.example.fhrm.hrm_system.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.fhrm.hrm_system.R;
import com.example.fhrm.hrm_system.models.Staff;

import java.util.List;

/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class ArrayAdapterStaff extends ArrayAdapter<Staff> {
    Context context;
    List<Staff> objects;

    public ArrayAdapterStaff(Context context, int resource, List<Staff> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }

    public View getView(int pos, View convertView, ViewGroup viewParent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_department_layout, viewParent, false);
        TextView textView = (TextView)rowView.findViewById(R.id.text_item_department);
        textView.setText(objects.get(pos).getName());
        return rowView;
    }
}
