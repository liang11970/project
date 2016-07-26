package cn.com.hz_project.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.hz_project.view.activity.LoginActivity;
import cn.com.projectdemos.R;

/**
 * Created by peng on 2016/7/13.
 */
public class SheZhiFragment extends Fragment {

    @InjectView(R.id.name)
    TextView name;

    @InjectView(R.id.phoneNumb)
    TextView phone;

    @InjectView(R.id.department)
    TextView department;

    @InjectView(R.id.photo)
    ImageView photo;

    @InjectView(R.id.quit)
    Button btn_quit;

    private PreferencesService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_setting, null);
        ButterKnife.inject(this, view);
        getData();

        return view;
    }

    private void getData() {

        service = new PreferencesService(getContext());

        name.setText(service.getPerferences().get("cusNumber"));
        phone.setText(service.getPerferences().get("telPhone"));
        department.setText(service.getPerferences().get("departmentName"));

//        Log.i("-------------->","http://116.228.202.122:8080/WsbxMobile"+service.getPerferences().get("imgUrl"));

        Picasso.with(getActivity()).load("http://116.228.202.122:8080/WsbxMobile"+service.getPerferences().get("imgUrl")).into(photo);

        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences=getContext().getSharedPreferences("logintag", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor=preferences.edit();
                editor.putBoolean("tag",false);
                editor.commit();


                getActivity().finish();



            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}

