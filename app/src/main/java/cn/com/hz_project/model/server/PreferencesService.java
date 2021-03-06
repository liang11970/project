package cn.com.hz_project.model.server;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.com.hz_project.model.bean.Login;

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
     * 登录界面保存参数（账号，密码，记住密码，自动登录）
     */
    public void save(String name,String pawd,String remPwd,String autoLogin) {
        //获得SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences("loginpwd", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("name", name);
        editor.putString("pwd", pawd);
        editor.putString("remPwd", remPwd);
        editor.putString("autoLogin", autoLogin);

        editor.commit();
    }



    /**
     * 登录界面获取参数
     */
    public HashMap getLoginInfo(){
        SharedPreferences preferences = context.getSharedPreferences("loginpwd", Context.MODE_PRIVATE);

        HashMap<String,String> map = new HashMap<>();
        map.put("name",preferences.getString("name",""));
        map.put("pwd",preferences.getString("pwd",""));
        map.put("remPwd",preferences.getString("remPwd","false"));
        map.put("autoLogin",preferences.getString("autoLogin","false"));

        return map;
    }

    /**
     * 保存参数
     */
    public void save(Login login) {
        //获得SharedPreferences对象
        SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("cusNumber", login.getObj().getCusNumber());
        editor.putString("departmentName", login.getObj().getDepartmentName());
        editor.putString("dprtmntId", login.getObj().getDprtmntId());
        editor.putString("loginIp", login.getObj().getLoginIp());
        editor.putString("loginName", login.getObj().getLoginName());
        editor.putString("orgClassKey", login.getObj().getOrgClassKey());
        editor.putString("orgCode", login.getObj().getOrgCode());
        editor.putString("role", login.getObj().getRole());
        editor.putString("roleId", login.getObj().getRoleId());
        editor.putInt("userId", login.getObj().getUserId());
        editor.putInt("userLevel", login.getObj().getUserLevel());
        editor.putString("ubdExpert",login.getObj().getUbdExpert());
        editor.putString("telPhone",login.getObj().getTelPhone());
        editor.putString("imgUrl", login.getObj().getImgUrl());
        editor.commit();
    }




    /**
     * 获取各项参数
     * @return
     */
    public Map<String, String> getPerferences() {
        Map<String, String> params = new HashMap<String, String>();
        SharedPreferences preferences = context.getSharedPreferences("login", Context.MODE_PRIVATE);
        params.put("cusNumber", preferences.getString("cusNumber",""));
        params.put("departmentName", preferences.getString("departmentName", ""));
        params.put("dprtmntId", preferences.getString("dprtmntId",""));
        params.put("loginIp", preferences.getString("loginIp",""));
        params.put("loginName", preferences.getString("loginName",""));
        params.put("orgClassKey", preferences.getString("orgClassKey",""));
        params.put("orgCode", preferences.getString("orgCode",""));
        params.put("role", preferences.getString("role",""));
        params.put("ubdExpert",preferences.getString("ubdExpert",""));
        params.put("roleId", preferences.getString("roleId",""));
        params.put("userId", preferences.getInt("userId",0)+"");
        params.put("userLevel", preferences.getInt("userLevel",0)+"");
        params.put("telPhone", preferences.getString("telPhone","000000"));
        params.put("imgUrl", preferences.getString("imgUrl","http://pic14.nipic.com/20110427/2944718_000916112196_2.jpg"));
        return params;
    }

}
