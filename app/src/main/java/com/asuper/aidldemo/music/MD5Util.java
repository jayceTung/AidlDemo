package com.asuper.aidldemo.music;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author super
 * @date 2018/1/25
 */
public class MD5Util {
    private static final String FILE_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath();
    private static final String FILE_VIDEO = FILE_ROOT + File.separator + "Movies" + File.separator + "earth.rmvb";


    public static String getMD5() {
        StringBuffer md5str = new StringBuffer();
        try {
            File file = new File(FILE_VIDEO);
            if (file.exists()) {
                Log.i("DMC", "file exists");
            } else {
                Log.i("DMC", "file is not exists");
                return "";
            }
            FileInputStream fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            byte[] mdbytes = md.digest();

            // convert the byte to hex format
            for (int i = 0; i < mdbytes.length; i++) {
                md5str.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Log.i("DMC md5 = ", md5str.toString());
        return md5str.toString();
    }
}
