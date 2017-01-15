package com.chenupt.bsdiff.sample;

import android.content.Context;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

/**
 * Created by chenupt@gmail.com on 2017/1/15.
 */

public class Utils {

    public static String md5(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
            for (int i = 0; i < 32 - value.length(); i++) {
                value = "0" + value;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return value;
    }

    public static void copyAssetsToFile(Context context, String fileName) {
        File srcFile = new File(context.getFilesDir(), fileName);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = context.getAssets().open(fileName);
            os = FileUtils.openOutputStream(srcFile);
            IOUtils.copy(is, os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(is);
        }
    }

}
