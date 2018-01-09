package com.weshape3d.mvpdemo.annotiondemo;

import android.app.Activity;
import android.icu.text.DateFormat;
import android.view.View;

import com.weshape3d.mvpdemo.R;

import java.lang.reflect.Field;

/**
 * Created by WESHAPE-DEV02 on 2017/12/21.
 */

public class BindViewUtil {
    public static void binderView(Activity activity){
       Class<? extends Activity> mcls =  activity.getClass();
        Field[] fields = mcls.getFields();
        for(Field field:fields){
            BindView bindView = field.getAnnotation(BindView.class);
          if(bindView!=null)  {
              int viewID = bindView.value();
              View view =  activity.findViewById(viewID);
              try {
                  field.setAccessible(true);
                  field.set(activity,view);
              } catch (IllegalAccessException e) {
                  e.printStackTrace();
              }
          }
        }
    }
}
