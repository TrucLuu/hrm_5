package com.example.fhrm.hrm_system.fragments;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fhrm.hrm_system.R;

/**
 * Created by luuhoangtruc on 21/01/2016.
 */
public class AboutFragment extends Fragment {
    private ImageView imageLogo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initialize(view);
        setRetainInstance(true);
        return view;
    }

    private void initialize(View view) {
        imageLogo = (ImageView)view.findViewById(R.id.imageView);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.clockwise);
        imageLogo.setImageResource(R.mipmap.logo);
        imageLogo.startAnimation(animation);
    }

}
