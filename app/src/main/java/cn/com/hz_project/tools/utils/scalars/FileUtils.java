package cn.com.hz_project.tools.utils.scalars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ku on 2016/7/25.
 * 读取本地指定文件夹下文件
 */
public class FileUtils {

    // 获取当前目录下所有的文件
    public static ArrayList<String> getAllFileName(String fileAbsolutePath) throws IOException {
        ArrayList<String> vecFile = new ArrayList<>();
        File file = new File(fileAbsolutePath);

        CreateText(file);

        File[] subFile = file.listFiles();

        if (subFile == null)
            return null;
        else {
            for (int iFileLength = 0; iFileLength < subFile.length; iFileLength++) {
                // 判断是否为文件夹
                if (!subFile[iFileLength].isDirectory()) {
                    String filename = subFile[iFileLength].getName();
                    vecFile.add(filename);
                }
            }
            return vecFile;
        }
    }

    //创建文件夹及文件
    public static void CreateText(File file) throws IOException {
        if (!file.exists()) {
            try {
                //按照指定的路径创建文件夹
                file.mkdirs();
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
