package com.weshape3d.aidldemo;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * Created by WESHAPE-DEV02 on 2017/10/23.
 */

public class Book implements Parcelable {
    public int bookId;
    public String bookName;
    public Book(int bookId,String bookName){
        this.bookId = bookId;
        this.bookName = bookName;
    }

    protected Book(Parcel in) {
        bookId = in.readInt();
        bookName = in.readString();
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(bookId);
        dest.writeString(bookName);
    }
    public static final Parcelable.ClassLoaderCreator<Book> CREATOR = new Parcelable.ClassLoaderCreator<Book>(){

        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }

        @Override
        public Book createFromParcel(Parcel source, ClassLoader loader) {
            return new Book(source);
        }
    };


}
