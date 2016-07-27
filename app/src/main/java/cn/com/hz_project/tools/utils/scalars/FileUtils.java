package cn.com.hz_project.tools.utils.scalars;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by ku on 2016/7/25.
 */
public class FileUtils {

    // 获取当前目录下所有的文件
    public static Vector<String> getAllFileName(String fileAbsolutePath) {
        Vector<String> vecFile = new Vector<String>();
        File file = new File(fileAbsolutePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
}
