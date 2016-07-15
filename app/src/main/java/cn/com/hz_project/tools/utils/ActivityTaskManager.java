package cn.com.hz_project.tools.utils;

import android.app.Activity;

import java.util.HashMap;
import java.util.Set;

/**
 * ==================================
 * Created by wangl on 2016/6/17.时间11:14
 * <p>
 * 版本1.0
 * <p>
 * 描述一个Activity管理器管理活动的Activity。
 * <p>
 * ================================
 */
public class ActivityTaskManager {



    private static ActivityTaskManager activityTaskManager = null;

    private HashMap<String, Activity> activityMap = null;

    private ActivityTaskManager() {

        activityMap = new HashMap<String, Activity>();

    }

            /**
             016
             * 返回activity管理器的唯一实例对象。
             017
             * @return ActivityTaskManager
            018
             */

    public static synchronized ActivityTaskManager getInstance() {

        if (activityTaskManager == null) {

            activityTaskManager = new ActivityTaskManager();

        }

        return activityTaskManager;

    }

            /**
             026
             * 将一个activity添加到管理器。
             027
             * @param activity
            028
             */

    public Activity putActivity(String name, Activity activity) {

        return activityMap.put(name, activity);

    }

            /**
             033
             * 得到保存在管理器中的Activity对象。
             034
             * @param name
            035
             * @return Activity
            036
             */

    public Activity getActivity(String name) {

        return activityMap.get(name);

    }

            /**
             041
             * 返回管理器的Activity是否为空。
             042
             * @return 当且当管理器中的Activity对象为空时返回true，否则返回false。
            043
             */

    public boolean isEmpty() {

        return activityMap.isEmpty();

    }

            /**
             048
             * 返回管理器中Activity对象的个数。
             049
             * @return 管理器中Activity对象的个数。
            050
             */

    public int size() {

        return activityMap.size();

    }

            /**
             055
             * 返回管理器中是否包含指定的名字。
             056
             *
             057
             * @param name
            058
             *            要查找的名字。
            059
             * @return 当且仅当包含指定的名字时返回true, 否则返回false。
            060
             */

    public boolean containsName(String name) {

        return activityMap.containsKey(name);

    }

            /**
             065
             * 返回管理器中是否包含指定的Activity。
             066
             * @param activity
            067
             *            要查找的Activity。
            068
             * @return 当且仅当包含指定的Activity对象时返回true, 否则返回false。
            069
             */

    public boolean containsActivity(Activity activity) {

        return activityMap.containsValue(activity);

    }

            /**
             074
             * 关闭所有活动的Activity。
             075
             */

    public void closeAllActivity() {

        Set<String> activityNames = activityMap.keySet();

        for (String string : activityNames) {

            finisActivity(activityMap.get(string));

        }

        activityMap.clear();

    }

            /**
             084
             * 关闭所有活动的Activity除了指定的一个之外。
             085
             * @param nameSpecified
            086
             *            指定的不关闭的Activity对象的名字。
            087
             */

    public void closeAllActivityExceptOne(String nameSpecified) {

        Set<String> activityNames = activityMap.keySet();

        Activity activitySpecified = activityMap.get(nameSpecified);

        for (String name : activityNames) {

            if (!name.equals(nameSpecified)) {

                finisActivity(activityMap.get(name));

            }
        }

        activityMap.clear();

        activityMap.put(nameSpecified, activitySpecified);

    }

            /**
             100
             * 移除Activity对象,如果它未结束则结束它。
             101
             * @param name
            102
             *            Activity对象的名字。
            103
             */

    public void removeActivity(String name) {

        Activity activity = activityMap.remove(name);

        finisActivity(activity);

    }

            /**
             109
             * 结束指定的Activity
             110
             * @param activity
            111
             *            指定的Activity。
            112
             */

    private final void finisActivity(Activity activity) {

        if (activity != null && !activity.isFinishing()) {

            activity.finish();

        }

    }
}
