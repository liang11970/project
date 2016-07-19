package cn.com.hz_project.model.server;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * ==================================
 * Created by wangl on 2016/7/19.时间10:49
 * <p>
 * 版本1.0
 * <p>
 * 描述  登录偏好设置
 * <p>
 * ================================
 */
public class PreferencesService {


    private Context context;

    public PreferencesService(Context context) {
        this.context = context;
    }

    /**
     * 保存参数
     */
    public void save(String name, String pawd,Boolean tag) {
        //获得SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences("loginpwd", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("checkbox", tag);
        editor.putString("name", name);
        editor.putString("md5password", pawd);
        editor.commit();
    }

    /**
     * 获取各项参数
     * @return
     */
    public Map<String, String> getPerferences() {
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("loginpwd", Context.MODE_PRIVATE);
        params.put("checkbox", preferences.getBoolean("checkbox",false)+"");
        params.put("name", preferences.getString("name", ""));
        params.put("md5password", preferences.getString("md5password",""));
        return params;
    }

}
