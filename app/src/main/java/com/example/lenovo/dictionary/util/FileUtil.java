package com.example.lenovo.dictionary.util;

/**
 * Created by lenovo on 2017/12/18.
 */
 import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class FileUtil {
    private String SDPath;
    private String AppPath;
    private static FileUtil fileUtil;
    private FileUtil()
    {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            SDPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/";
            File fileV= new File(SDPath,"VocabularyBuilder");
            AppPath = fileV.getAbsolutePath()+"/";
        }
    }
    public static FileUtil getInstance()
    {
        if(fileUtil==null)
        {
            synchronized (FileUtil.class)
            {
                if(fileUtil==null)
                {
                    fileUtil = new FileUtil();
                }
            }
        }
        return fileUtil;
    }
    public File createSDDir(String path,String dirName)
    {
        File dir = new File(path+dirName);
        if(dir.exists()&&dir.isDirectory())
        {
            return dir;
        }
        dir.mkdir();
        Log.d("测试","创建目录成功");
        return dir;
    }
    public File createSDFile(String path,String fileName)
    {
        File file = new File(path+fileName);
        if(file.exists()&&file.isFile())
        {
            return file;
        }
        try
        {
            file.createNewFile();
            Log.d("测试","创建文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public void writeToSD(String path,String fileName,InputStream inputStream)
    {
        OutputStream outputStream = null;
        try {
            File dir = createSDDir(AppPath,path);
            File file = createSDFile(dir.getAbsolutePath()+"/",fileName);
            outputStream = new FileOutputStream(file);
             int length;
             byte[] buffer = new byte[2*1024];
             while((length = inputStream.read(buffer))!=-1)
            {
                outputStream.write(buffer,0,length);
            }
            outputStream.flush();
            Log.d("测试","写入成功");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("测试","写入失败");
        } finally {
            try{
                if(outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public String getPathInSD(String fileName)
    {
        File file = new File(AppPath+fileName);
        if(file.exists())
            return file.getAbsolutePath();
            return "";
    }
}




