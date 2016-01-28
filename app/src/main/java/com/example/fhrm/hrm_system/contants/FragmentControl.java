package com.example.fhrm.hrm_system.contants;

import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;


/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class FragmentControl {
    public static void replace (FragmentManager fragmentManager, int layout_id, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(layout_id, fragment)
                .commit();
    }

    public static void mySetText (Dialog dialog, int idInput, TextView textInput, String text){
        textInput = (TextView)dialog.findViewById(idInput);
        textInput.setText(text);
    }

    public static final String [] status ={"Trainee","Intern Ship","Official Staff"};
    public static final String [] position ={"Manager", "Group Leader","Leader","Quality Assurance","Staff"
            ,"Tester","Comtor","Bridge System Engineer"};
}
