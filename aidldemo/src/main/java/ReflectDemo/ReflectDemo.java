package ReflectDemo;

import android.os.Looper;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * Created by WESHAPE-DEV02 on 2018/1/4.
 */

public class ReflectDemo {
    /**
     * 获取某个对象的属性
     */
    public static Object getProperty(Object owner,String propertyName) {
        Object property = null;
        try {
            Class<?> clz = owner.getClass();
            Field field = clz.getDeclaredField(propertyName);
            field.setAccessible(true);
            property = field.get(owner);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return property;
    }

    /**
     * 获取某个类的静态属性值
     */
    public static Object getStaticPropertyValue(Class clz,String staticPropertyName){
        Object object = null;
        try {
            Field field = clz.getDeclaredField(staticPropertyName);
            field.setAccessible(true);
            object = field.get(clz);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 执行某对象的方法
     */

    public static  void invokeMethod(Object owner,String methodName,Object[] args){
        Class<?> clz = owner.getClass();
        //组装参数
        Class<?>[] margs = new Class[args.length];
        for(int i=0;i<args.length;i++){
            Object o = args[0];
            Class<?> c = o.getClass();
            margs[i] = c;
        }
        //执行
        try {
           Method method =  clz.getDeclaredMethod(methodName,margs);
            method.setAccessible(true);
            method.invoke(owner,args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param parameterizedType
     * @param i
     * @return
     */
    private static Class getGenericClass(ParameterizedType parameterizedType, int i) {

        Object genericClass = parameterizedType.getActualTypeArguments()[i];////1.获得<>中实际类型
        if (genericClass instanceof ParameterizedType) { // 处理多级泛型
            return (Class) ((ParameterizedType) genericClass).getRawType();////获得第一层<>前面实际类型
        } else if (genericClass instanceof GenericArrayType) { // 处理数组泛型
            //获取数组元素类型
            return (Class) ((GenericArrayType) genericClass).getGenericComponentType();
        } else if (genericClass instanceof TypeVariable) { // 处理泛型擦拭对象
            return (Class) getClass(((TypeVariable) genericClass).getBounds()[0], 0);
        } else {
            ThreadLocal local;
            Looper.prepare();
            return (Class) genericClass;
        }
    }

    private static Class getClass(Type type, int i) {
        if (type instanceof ParameterizedType) { // 处理泛型类型
            return getGenericClass((ParameterizedType) type, i);
        } else if (type instanceof TypeVariable) {
            return (Class) getClass(((TypeVariable) type).getBounds()[0], 0); // 处理泛型擦拭对象
        } else {// class本身也是type，强制转型
            return (Class) type;
        }
    }
}
