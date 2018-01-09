package com.weshape3d.customviews.mycostomviews.myimgmanager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

/**
 * Created by WESHAPE-DEV02 on 2017/9/28.
 *
 * Bitmap 像素点处理
 */

public class BitmapUtil {
    /**
     * 照片底片效果
     * @param bitmap
     * @return
     */
    public static Bitmap handlerImgNegative(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int color;
        int r,g,b,a;
        Bitmap bitmap1 = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        int[] oldPx = new int[width*height];
        int[] newPx = new int[width*height];
        bitmap.getPixels(oldPx,0,width,0,0,width,height);

        for(int i = 0;i<width*height;i++){
            color = oldPx[i];

            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;
            if(r>255){
                r = 255;
            }else if(r <0){
                r = 0;
            }
            if(g>255){
                g = 255;
            }else if(r <0){
                g = 0;
            }
            if(b>255){
               b = 255;
            }else if(r <0){
                b = 0;
            }
            newPx[i] = Color.argb(a,r,g,b);
        }
        bitmap1.setPixels(newPx,0,width,0,0,width,height);
        return bitmap1;
    }

}
