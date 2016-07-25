package cn.com.hz_project.view.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
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
        initFruits();
        LingdaoAdapter adapter = new LingdaoAdapter(SecondActivity.this,
                R.layout.item_p, fruitList);
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
        Title apple = new Title(R.mipmap.man1,"郑善和","1959年3月出生，汉族，籍贯福建福州，中共党员，1977年6月参加工作，大学学历，现任上海市司法局党委书记、局长，市监狱管理局第一政委。曾任市公安局经济保卫总队副总队长，市公安局政治部副主任、主任，市公安局副局长，市监狱管理局党委书记，市...","上海市司法局党委书记");
        fruitList.add(apple);
        Title banana = new Title(R.mipmap.man2,"朱久伟","1957年2月出生，汉族，籍贯江苏邗江，中共党员，1975年3月参加工作，研究生学历，法律硕士，现任上海市司法局副局长。曾任华东政法学院人事处处长、成人教育院院长，市社区矫正工作办公室主任等职。","上海市司法局巡视员");
        fruitList.add(banana);
        Title orange = new Title(R.mipmap.man3,"王 协","1962年5月出生，汉族，籍贯山东济阳，中共党员，1983年8月参加工作，大学学历，现任上海市司法局副局长。曾任市第一中级人民法院办公室副主任，上海海事法院办公室主任，上海海事法院审委会专职委员，市司法局纪委书记等职。","上海市司法局副局长");
        fruitList.add(orange);
        Title watermelon = new Title(R.mipmap.man4,"陈春兰","1971年3月出生，汉族，籍贯四川泸县，无党派，1993年7月参加工作，大学学历，法学硕士，现任上海市司法局副局长。曾任浦东新区司法局局长助理、副局长，浦东新区政协副主席等职。","上海市司法局副局长");
        fruitList.add(watermelon);
        Title pear = new Title(R.mipmap.man5,"刘建华","1957年5月出生，汉族，籍贯安徽黄山，中共党员，1974年4月参加工作，大学学历，现任上海市司法局巡视员。曾任市白茅岭监狱党委书记、监狱长，市监狱管理局办公室主任，市监狱管理局副局长，市劳教局党委书记、局长，市戒毒管理局党委书记、局长等职...","上海市司法局巡视员");
        fruitList.add(pear);
        Title pear1 = new Title(R.mipmap.man6,"刘卫萍","1964年3月出生，汉族，籍贯湖北天门，中共党员，1985年7月参加工作，研究生学历，经济学博士，现任上海市司法局政治部主任。曾任市司法局监狱劳教政策研究室主任，市社区矫正工作办公室副主任，市司法局政治部副主任、组织干部处处长等职。","上海市司法局政治部主任");
        fruitList.add(pear1);
        Title pear2 = new Title(R.mipmap.man7,"李 鸣","1963年10月出生，汉族，籍贯湖北黄陂，中共党员，1985年7月参加工作，大学学历，工学学士，现任上海市司法局纪委书记。曾任市总工会办公室主任、组织部部长，市总工会经费审查委员会主任等职。","上海市司法局纪委书记");
        fruitList.add(pear2);
        Title pear3 = new Title(R.mipmap.man8,"戴卫东","1966年6月出生，汉族，籍贯江苏泰兴，中共党员，1986年7月参加工作，大学学历，现任上海市戒毒管理局党委书记。曾任市南汇监狱党委书记、监狱长，市提篮桥监狱党委书记、监狱长，市监狱管理局副局长等职。","上海市戒毒管理局党委书记");
        fruitList.add(pear3);
        Title pear4 = new Title(R.mipmap.man9,"陈耀鑫","1962年9月出生，汉族，籍贯上海，中共党员，1980年3月参加工作，大学学历，现任上海市社区矫正管理局局长。曾任市宝山监狱党委书记、监狱长，市监狱管理局政治部副主任、教育培训处处长，市司法警官学校党委书记、校长，市社区矫正工作办公室主任等...","上海市社区矫正管理局局长");
        fruitList.add(pear4);
        Title pear5 = new Title(R.mipmap.man10,"张祎","1965年8月出生，汉族，籍贯安徽怀宁，中共党员，1987年3月参加工作，中央党校研究生学历，现任上海市戒毒管理局党委副书记、局长。曾任市第一劳教所党委书记、所长，市劳教局办公室主任，市劳教局副局长，市戒毒管理局副局长等职。","上海市戒毒管理局党委副书记、局长");
        fruitList.add(pear5);
        Title pear6 = new Title(R.mipmap.man11,"朱剑华","1956年9月出生，汉族，籍贯上海，中共党员，1975年1月参加工作，大学学历，现任上海市司法局副巡视员、法制宣传处（依法治市工作处）处长。曾任市司法局宣传资料中心主任、办公室（外事办公室）副主任、信访办公室主任等职。","上海市司法局副巡视员、法制宣传处处长");
        fruitList.add(pear6);
    }
}
