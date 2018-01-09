package com.weshape3d.customviews.mycostomviews;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2017/9/19.
 */

public class MyDialog extends Dialog {

    public MyDialog(@NonNull Context context) {
        super(context);


    }

    public MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected MyDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("drummor","dialog --onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d("drummor","MyDialog--onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("drummor","MyDialog--onStop");
        super.onStop();
    }

    @Override
    public void onDetachedFromWindow() {
        Log.d("drummor","MyDialog--onDetachedFromWindow");
        super.onDetachedFromWindow();
    }

    @Override
    public void onAttachedToWindow() {
        Log.d("drummor","MyDialog--onAttachedToWindow");
        super.onAttachedToWindow();
    }

}
