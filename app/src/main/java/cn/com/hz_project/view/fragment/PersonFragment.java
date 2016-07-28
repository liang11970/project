package cn.com.hz_project.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.com.projectdemos.R;

public class PersonFragment extends Fragment {
    @InjectView(R.id.rg_interact)
    RadioGroup rg_interact;

    @InjectView(R.id.rb_vote)
    RadioButton rb_vote;

    @InjectView(R.id.rb_quiz)
    RadioButton rb_quiz;


    private FragmentManager manager;
    private FragmentTransaction transaction;
    private QuizFragment quizFragment;
    private VoteFragment voteFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_person,null);
        ButterKnife.inject(this, view);

        initView();
        initData();

        return view;
    }

    private void initView() {
        rg_interact.check(R.id.rb_vote);

        quizFragment = new QuizFragment();
        voteFragment = new VoteFragment();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        transaction.add(R.id.fm_interact, voteFragment);
        transaction.commit();
    }

    private void initData() {

        rg_interact.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_vote:
                        if (!voteFragment.isAdded()){
                            manager.beginTransaction().add(R.id.fm_interact, voteFragment).commit();
                        }else{
                            manager.beginTransaction().hide(quizFragment).show(voteFragment).commit();
                        }
                        break;

                    case R.id.rb_quiz:
                        if (!quizFragment.isAdded()){
                            manager.beginTransaction().add(R.id.fm_interact, quizFragment).commit();
                        }else{
                            manager.beginTransaction().hide(voteFragment).show(quizFragment).commit();
                        }
                        break;
                }
            }
        });
    }

}
