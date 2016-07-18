package cn.com.hz_project.tools.db;

import android.content.Context;


import com.hz_project.entity.DaoMaster;
import com.hz_project.entity.DaoSession;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 1、创建数据库
 * 2、创建数据库的表
 * 3、包含对数据库的CRUD
 * 4、对数据库的升级
 * Created by luoliwen on 16/4/16.
 */
public class DaoManager {
    private static  final String   TAG = DaoManager.class.getSimpleName();
    private static  final String  DB_NAME="mydb.sqlite";//数据库名称
    private volatile  static DaoManager manager;//多线程访问
    private  static DaoMaster.DevOpenHelper helper;
    private static  DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;

    /**
     * 使用单例模式获得操作数据库的对象
     * @return
     */
    public  static DaoManager getInstance(){
       DaoManager instance = null;
        if (manager==null){
            synchronized (DaoManager.class){
                if (instance==null){
                    instance = new DaoManager();
                    manager = instance;
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        this.context = context;
    }
    /**
     * 判断是否存在数据库，如果没有则创建数据库
     * @return
     */
    public DaoMaster getDaoMaster(){
        if (daoMaster==null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询的操作，仅仅是一个接口
     * @return
     */
    public DaoSession getDaoSession(){
        if (daoSession==null){
            if (daoMaster==null){
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 打开输出日志的操作,默认是关闭的
     */
    public void setDebug(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作,数据库开启的时候，使用完毕了必须要关闭
     */
    public void closeConnection(){
        closeHelper();
        closeDaoSession();
    }
    public  void  closeHelper(){
        if (helper!=null){
            helper.close();;
            helper = null;
        }
    }
    public void closeDaoSession(){
        if (daoSession!=null){
            daoSession.clear();;
            daoSession = null;
        }
    }
}
