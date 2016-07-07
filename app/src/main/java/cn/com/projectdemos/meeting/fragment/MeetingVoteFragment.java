package cn.com.projectdemos.meeting.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.com.projectdemos.R;
import cn.com.projectdemos.meeting.widget.FrameFragment;

/**
 * Created by wee on 16/6/20.
 */
public class MeetingVoteFragment extends FrameFragment {


    View mView;

    @Override
    protected View getFragmentResource(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_meeting_vote, null);


        return mView;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    protected void doAfterCreate() {
//        gridview = (GridView) mView.findViewById(R.id.dish_list);
//
//        inItView();
//
//
//        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        dishNu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), AddNewDishActivity.class);
//                startActivityForResult(intent, 10);
//
//            }
//        });
//
//
//        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(), "ddd", Toast.LENGTH_LONG).show();
//                return false;
//
//
//            }
//        });


    }

//    private void inItView(){
//        HttpUtils.get(Constants.URL_SHOP_DISH + "?shop_id=" + PrefAppStore.getShopId(getActivity()), new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                Debug.e("hck", new String(responseBody));
//                list = JsonTools.getDishList("result", new String(responseBody));
//                adapter = new DishAdapter(getActivity(), list);
//
//                gridview.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Debug.e("hck", "请检查网络后再做尝试");
//            }
//        });
//
//    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
////
////        if(requestCode == 10){
////            String isNeedRefresh= PrefAppStore.getIsRefresh(getActivity());
////            if(isNeedRefresh.equals("1")){
////                inItView();
////                PrefAppStore.setIsRefresh(getActivity(),"0");
////            }else {
////
////            }
////
////        }else {
////
////        }
//    }

    @Override
    public void onResumeView() {

    }


}
