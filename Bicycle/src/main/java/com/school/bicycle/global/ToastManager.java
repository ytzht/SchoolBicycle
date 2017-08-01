package com.school.bicycle.global;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by Jero on 2017/3/16 0016.
 */

public class ToastManager {
    private static Toast mToast;
    private static Context mContext;

    public static void send(@NonNull final Context context, @NonNull final String text, final int duration) {
        if (mToast == null) {
            mContext = context;
            mToast = Toast.makeText(context, text, duration);
        } else if (mContext == context) {
            mToast.setText(text);
            mToast.setDuration(duration);
        } else {
            mContext = context;
            mToast = Toast.makeText(context, text, duration);
        }
        mToast.show();
    }

    public static void send(@NonNull final Context context, @NonNull final String text) {
        send(context, text, Toast.LENGTH_LONG);
    }

    public static void send(@NonNull final Context context, @NonNull final int id) {
        send(context, context.getString(id), Toast.LENGTH_LONG);
    }
}
