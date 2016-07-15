package cn.com.hz_project.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.com.hz_project.view.fragment.HomeFragment;
import cn.com.hz_project.view.fragment.PersonFragment;
import cn.com.hz_project.view.fragment.SheZhiFragment;
import cn.com.hz_project.view.fragment.SortFragment;
import cn.com.projectdemos.R;

public class ViewPagerActivity extends FragmentActivity {

    public final static int num = 4 ;

    Fragment homeFragment;
    Fragment personFragment;
    Fragment sorttypeFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        fragmentManager = getSupportFragmentManager();
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        ((RadioButton)radioGroup.findViewById(R.id.radio0)).setChecked(true);

        transaction = fragmentManager.beginTransaction();
        Fragment fragment = new HomeFragment();
        transaction.replace(R.id.content, fragment);
        transaction.commit();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        transaction = fragmentManager.beginTransaction();
                        Fragment homeFragment = new HomeFragment();
                        transaction.replace(R.id.content, homeFragment);
                        transaction.commit();
                        break;
                    case R.id.radio1:
                        transaction = fragmentManager.beginTransaction();
                        Fragment sortFragment = new SortFragment();
                        transaction.replace(R.id.content, sortFragment);
                        transaction.commit();
                        break;
                    case R.id.radio2:
                        transaction = fragmentManager.beginTransaction();
                        Fragment personFragment = new PersonFragment();
                        transaction.replace(R.id.content, personFragment);
                        transaction.commit();
                        break;
                    case R.id.radio3:
                        transaction = fragmentManager.beginTransaction();
                        Fragment shezhiFragment = new SheZhiFragment();
                        transaction.replace(R.id.content, shezhiFragment);
                        transaction.commit();
                        break;
                }

            }
        });
    }



}
