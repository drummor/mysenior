package com.weshape3d.customviews.mycostomviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.weshape3d.customviews.R;
/**
 * Created by WESHAPE-DEV02 on 2017/9/28.
 */

public class MatrixView extends View {
    public MatrixView(Context context) {
        super(context);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float[] pointsSrc = {getLeft(), getLeft(), getRight(), getTop(), getLeft(), getBottom(), getRight(),getBottom()};
        float[] pointsDst = {getLeft() - 10, getLeft() + 50,  getRight() + 120,getTop() - 90, getTop() + 20, getBottom() + 30, getRight() + 20, getBottom() + 60};
        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setPolyToPoly(pointsSrc, 0, pointsDst, 0, 4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        canvas.save();
        canvas.concat(matrix);
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, 30, 30, paint);
        canvas.restore();
    }
}
