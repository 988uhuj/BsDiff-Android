package com.chenupt.bsdiff;

/**
 * Created by chenupt@gmail.com on 2016/12/23.
 */

public class BsDiff {

    static {
        System.loadLibrary("bspatch");
    }

    public static native int patch(String oldFile, String newFile, String patchPath);
}
