package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.hz_project.model.server.PreferencesService;
import cn.com.projectdemos.R;

/**
 * Created by peng on 2016/7/13.
 */
public class SheZhiFragment extends Fragment {

    @InjectView(R.id.tvname)
    TextView tvname;
    @InjectView(R.id.tvphone)
    TextView tvphone;
    @InjectView(R.id.tvdepartmentName)
    TextView tvdepartmentName;
    @InjectView(R.id.ivtop)
    ImageView ivtop;
    private PreferencesService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_shezhi, null);
        ButterKnife.inject(this, view);
        getData();

        return view;
    }

    private void getData() {

        service = new PreferencesService(getContext());

        tvname.setText(service.getPerferences().get("cusNumber"));
        tvphone.setText(service.getPerferences().get("telPhone"));
        tvdepartmentName.setText(service.getPerferences().get("departmentName"));

        Picasso.with(getContext()).load("http://192.168.2.35:8080/WsbxMobile"+service.getPerferences().get("imgUrl")).into(ivtop);

        Logger.e(service.getPerferences().get("imgUrl"));
        Logger.e("http://192.168.2.35:8080/WsbxMobile"+service.getPerferences().get("imgUrl"));

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

