package com.chenupt.bsdiff.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chenupt.bsdiff.BsDiff;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvContent = (TextView) findViewById(R.id.text);

        init();
    }

    private void init() {
        Utils.copyAssetsToFile(this, "base.zip");
        Utils.copyAssetsToFile(this, "temp.patch");
        Utils.copyAssetsToFile(this, "target.zip");

        File baseFile = new File(getFilesDir() + File.separator + "base.zip");
        File tempPatch = new File(getFilesDir() + File.separator + "temp.patch");
        File targetFile = new File(getFilesDir() + File.separator + "target.zip");
        String md5 = Utils.md5(targetFile);

        String content = tvContent.getText().toString() +
                "base file size: " + FileUtils.sizeOf(baseFile) +
                "\n" +
                "patch size: " + FileUtils.sizeOf(tempPatch) +
                "\n" +
                "target size: " + FileUtils.sizeOf(targetFile) +
                "\n" +
                "target md5: " + md5 +
                "\n" +
                "\n" +
                "bspatch result: " +
                "\n";
        tvContent.setText(content);
    }

    public void bspatch(View view) {
        BsDiff.patch(getFilesDir() + "/base.zip", getFilesDir() + "/result.zip", getFilesDir() + "/temp.patch");
        File resultFile = new File(getFilesDir(), "result.zip");
        String md5 = Utils.md5(resultFile);
        Log.d(TAG, "bspatch: " + md5);

        String content = tvContent.getText().toString() + "md5: " + md5 +
                "\n" +
                "size: " + FileUtils.sizeOf(resultFile) +
                "\n";
        tvContent.setText(content);
    }

}
