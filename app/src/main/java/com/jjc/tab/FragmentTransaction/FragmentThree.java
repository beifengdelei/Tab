package com.jjc.tab.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjc.tab.R;

/**
 * Created by Administrator on 2015/4/7.
 */
public class FragmentThree extends Fragment{
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("THREE____onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("THREE____onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("THREE____onCreateView");
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("THREE____onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("THREE____onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("THREE____onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        System.out.println("THREE____onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("THREE____onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("THREE____onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("THREE____onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("THREE____onDetach");
    }
}
