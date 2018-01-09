package ReflectDemo;

import android.util.Log;

/**
 * Created by WESHAPE-DEV02 on 2018/1/4.
 */

public class Student {
    private String name = "";
    private static String ID = "798";
    public Student(String name,String ID){
        this.name = name;
        this.ID = ID;
    }

    private void print(){
        Log.d("drummor","执行了对象"+ID+"的print方法");
    }
}
