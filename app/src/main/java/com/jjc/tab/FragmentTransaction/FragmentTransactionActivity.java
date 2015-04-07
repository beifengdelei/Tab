package com.jjc.tab.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.RadioGroup;

import com.jjc.tab.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/7.
 */
public class FragmentTransactionActivity extends FragmentActivity {

    private Context context;
    private RadioGroup radioGroup;
    private List<Fragment> fragmentList = new ArrayList<>();

    private int currentTab; // 当前Tab页面索引

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmenttransaction);

        context = FragmentTransactionActivity.this;
        initData();
        initView();
    }

    private void initData() {
        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThree());
    }

    private void initView() {
        radioGroup = (RadioGroup)findViewById(R.id.tabs_container);

        if(!fragmentList.isEmpty()){
            // 默认显示第一页
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.layout_container, fragmentList.get(0));
            fragmentTransaction.commit();

            radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        }
    }

    private class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            for(int i = 0; i < radioGroup.getChildCount(); i++){
                if(radioGroup.getChildAt(i).getId() == checkedId){
                    Fragment fragment = fragmentList.get(i);
                    FragmentTransaction ft = obtainFragmentTransaction(i);

                    getCurrentFragment().onPause(); // 暂停当前tab
//                getCurrentFragment().onStop(); // 暂停当前tab

                    if(fragment.isAdded()){
//                    fragment.onStart(); // 启动目标tab的onStart()
                        fragment.onResume(); // 启动目标tab的onResume()
                    }else{
                        ft.add(R.id.layout_container, fragment);
                    }
                    showTab(i); // 显示目标tab
                    ft.commit();

                    // 如果设置了切换tab额外功能功能接口
//                    if(null != onRgsExtraCheckedChangedListener){
//                        onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
//                    }
                }
            }
        }
    }

    /**
     * 切换tab
     * @param idx
     */
    private void showTab(int idx){
        for(int i = 0; i < fragmentList.size(); i++){
            Fragment fragment = fragmentList.get(i);
            FragmentTransaction ft = obtainFragmentTransaction(idx);
            if(idx == i){
                ft.show(fragment);
            }else{
                ft.hide(fragment);
            }
            ft.commit();
        }
        currentTab = idx; // 更新目标tab为当前tab
    }

    /**
     * 获取一个带动画的FragmentTransaction
     * @param index
     * @return
     */
    private FragmentTransaction obtainFragmentTransaction(int index){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // 设置切换动画
        if(index > currentTab){
            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
        }else{
            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
        }
        return ft;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public Fragment getCurrentFragment(){
        return fragmentList.get(currentTab);
    }

    static class OnRgsExtraCheckedChangedListener{
        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index){

        }
    }
}
