// IBookManager.aidl
package com.weshape3d.aidldemo;
import com.weshape3d.aidldemo.Book;
import com.weshape3d.aidldemo.IOnNewBookArrivedListener;


// Declare any non-default types here with import statements

interface IBookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
  List<Book> getBookList();
  void addBook(in Book book);

  void registerListener(IOnNewBookArrivedListener listener);
  void unregisterListener(IOnNewBookArrivedListener listener);

}
