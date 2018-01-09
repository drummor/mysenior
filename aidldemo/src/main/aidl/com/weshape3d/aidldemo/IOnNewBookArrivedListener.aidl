// IOnNewBookArrivedListener.aidl
package com.weshape3d.aidldemo;
import com.weshape3d.aidldemo.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArrivedListener {
     void onNewBookArrived(in Book newBook);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

}

