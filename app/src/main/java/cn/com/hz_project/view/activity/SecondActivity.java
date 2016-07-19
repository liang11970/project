package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.com.hz_project.model.bean.Title;
import cn.com.hz_project.view.adapter.LingdaoAdapter;
import cn.com.projectdemos.R;

public class SecondActivity extends Activity {
    private List<Title> fruitList = new ArrayList<Title>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second);
        initFruits(); //  初始化水果数据
        LingdaoAdapter adapter = new LingdaoAdapter(SecondActivity.this,
                R.layout.item1, fruitList);
        ListView listView = (ListView) findViewById(R.id.lingdao);
        listView.setAdapter(adapter);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initFruits() {
        Title apple = new Title(R.mipmap.man1,"郑善和","1959年3月出生，汉族，籍贯福建福州，中共党员，1977年6月参加工作，大学学历，现任上海市司法局党委书记。","上海市司法局党委书记");
        fruitList.add(apple);
        Title banana = new Title(R.mipmap.man2,"朱久伟","1957年2月出生，汉族，籍贯江苏邗江，中共党员，1975年3月参加工作，研究生学历，法律硕士，现任上海市司法局副局长。","上海市司法局巡视员");
        fruitList.add(banana);
        Title orange = new Title(R.mipmap.man3,"王 协","1962年5月出生，汉族，籍贯山东济阳，中共党员，1983年8月参加工作，大学学历，现任上海市司法局副局长。","上海市司法局副局长");
        fruitList.add(orange);
        Title watermelon = new Title(R.mipmap.man4,"陈春兰","1971年3月出生，汉族，籍贯四川泸县，无党派，1993年7月参加工作，大学学历，法学硕士，现任上海市司法局副局长。","上海市司法局副局长");
        fruitList.add(watermelon);
        Title pear = new Title(R.mipmap.man5,"刘建华","1957年5月出生，汉族，籍贯安徽黄山，中共党员，1974年4月参加工作，大学学历，现任上海市司法局巡视员。","上海市司法局巡视员");
        fruitList.add(pear);
    }
}
