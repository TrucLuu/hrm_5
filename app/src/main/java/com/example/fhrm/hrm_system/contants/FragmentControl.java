package com.example.fhrm.hrm_system.contants;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;


/**
 * Created by luuhoangtruc on 25/01/2016.
 */
public class FragmentControl {
    public static void replace (FragmentManager fragmentManager, int layout_id, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(layout_id, fragment)
                .commit();
    }
}
