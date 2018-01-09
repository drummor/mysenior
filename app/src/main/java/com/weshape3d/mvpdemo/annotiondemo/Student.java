package com.weshape3d.mvpdemo.annotiondemo;

import java.lang.reflect.Field;

/**
 * Created by WESHAPE-DEV02 on 2017/12/21.
 */

@StudentDes(ID = 1008,name = "Frank")
public class Student {
    String name ;
    int ID ;
    static void getStudent(Student student){
        Class<?extends Student> cls = student.getClass();
        StudentDes studentDes = (StudentDes) cls.getAnnotation(StudentDes.class);
        /**
         * 在注解中获取名字和学号
         */
        String name = studentDes.name();
        int ID = studentDes.ID();
        Field[] fields = cls.getFields();
        for(Field field:fields){
           if(field.getName().equals("name")) {
               try {
                   field.setAccessible(true);
                   field.set(student,name);//设置名字
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }else if (field.getName().equals("ID")){
               try {
                   field.setAccessible(true);
                   field.set(student,ID);//设置学号
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
        }

    }
}
