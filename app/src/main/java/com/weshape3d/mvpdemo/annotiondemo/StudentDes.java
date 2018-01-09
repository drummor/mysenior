package com.weshape3d.mvpdemo.annotiondemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by WESHAPE-DEV02 on 2017/12/21.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface StudentDes {
    String name() default "";
    int ID() default -1;
}
