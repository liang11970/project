package cn.com.hz_project.tools.db;

import android.content.Context;
import android.util.Log;


import com.hz_project.entity.Student;
import com.hz_project.entity.StudentDao;

import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * 完成对某一张表的具体操作,ORM 操作的是对象，Student
 * Created by luoliwen on 16/4/16.
 */
public class DBCommonUtils {
    private DaoManager manager;

    public DBCommonUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 完成对数据库中student 表的插入操作
     *
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        boolean flag = false;

        flag = manager.getDaoSession().insert(student) != -1 ? true : false;
        Log.i("CommonUtils", "----insertStudent--result is -->>" + flag);
        return flag;
    }

    /**
     * 插入多条记录，需要开辟新的线程
     *
     * @param students
     * @return
     */
    public boolean insertMultStudent(final List<Student> students) {
        boolean flag = false;

        try {
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student : students) {
                        manager.getDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 完成对student的某一条记录的修改
     *
     * @param student
     * @return
     */
    public boolean updateStudent(Student student) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param student
     * @return
     */
    public boolean deleteStudent(Student student) {
        boolean flag = false;
        try {
            //按照指定的id进行删除 delete from student where _id = ?
            manager.getDaoSession().delete(student);
            //manager.getDaoSession().deleteAll();//删除所有的记录
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     *
     */
    public void deleteAllStudent(Class cls){
        manager.getDaoSession().deleteAll(cls);
    }
    /**
     * 返回多行记录
     *
     * @return
     */
    public List<Student> listAll() {
        return manager.getDaoSession().loadAll(Student.class);
    }

    /**
     * 按照主键返回单行记录
     *
     * @param key
     * @return
     */
    public Student listOneStudent(long key) {
        return manager.getDaoSession().load(Student.class, key);
    }

    public void query1() {
        //使用native sql进行查询操作，
        List<Student> list = manager.getDaoSession().queryRaw(Student.class, " where name like ? and _id > ? ", new String[]{"%李%", "1002"});
        Log.i("--->>", "" + list);
    }

    /**
     * select  * from student where name like ? or name =? or
     * <  <= >= !=  in between and
     * select * from student where age > 23 and address like "江西"
     */
    public void query2() {
        //查询构建器
        QueryBuilder<Student> builder = manager.getDaoSession().queryBuilder(Student.class);
        List<Student> list = builder.where(StudentDao.Properties.Age.ge(23)).where(StudentDao.Properties.Address.like("江西")).list();
        Log.i("--->>", "" + list);
    }

    public void query3() {

        //逻辑与 和 逻辑或 是双目运算符
       QueryBuilder<Student> builder = manager.getDaoSession().queryBuilder(Student.class);
        //select * from student where (address='北京' or age > 50) and name like '%张%'
        builder.whereOr(StudentDao.Properties.Address.eq("北京"), StudentDao.Properties.Age.ge(50));
        builder.whereOr(StudentDao.Properties.Id.ge(2), StudentDao.Properties.Age.ge(10)).limit(3);//区前三条数据

        //builder.where(StudentDao.Properties.Name.like("张"));
        List<Student> list = builder.list();
        Log.i("--->>", "" + list);
    }

}
