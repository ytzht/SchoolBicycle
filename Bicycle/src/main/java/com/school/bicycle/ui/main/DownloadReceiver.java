package com.school.bicycle.ui.main;

import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ytzht on 2017/07/28 下午9:12
 */

public class DownloadReceiver extends BroadcastReceiver {

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onReceive(Context context, Intent intent) {
        long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);

        Log.d("=====", "下载的IDonReceive: " + completeDownloadId);

        DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            DownloadManager.Query query = new DownloadManager.Query();
            //在广播中取出下载任务的id
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            query.setFilterById(id);
            Cursor c = manager.query(query);
            if (c.moveToFirst()) {
                //获取文件下载路径
                String filename = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                //如果文件名不为空，说明已经存在了，拿到文件名想干嘛都好
                if (filename != null) {
                    Log.d("=====", "下载完成的文件名为：" + filename);
                    //     /storage/emulated/0/zhnet/T台.apk
                    Toast.makeText(context, "请点击安装", Toast.LENGTH_SHORT).show();


                    //执行安装
                    Intent intent_ins = new Intent(Intent.ACTION_VIEW);
                    intent_ins.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent_ins.setDataAndType(Uri.parse("file://" + filename), "application/vnd.android.package-archive");
                    context.getApplicationContext().startActivity(intent_ins);
//                    filename = filename.substring(filename.lastIndexOf("/")+1, filename.lastIndexOf("."));
//                    Log.d("=====", "截取后的文件名onReceive: "+filename);
                }
            }
        } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())) {
            long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
            //点击通知栏取消下载
//            manager.remove(ids);
//            Toast.makeText(context, "已经取消下载", Toast.LENGTH_SHORT).show();
        }
    }
}