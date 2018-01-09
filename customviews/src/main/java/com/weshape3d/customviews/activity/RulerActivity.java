package com.weshape3d.customviews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.weshape3d.customviews.R;
import com.weshape3d.customviews.mycostomviews.RulerWidget;

public class RulerActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler);
        RulerWidget rulerWidget = (RulerWidget) findViewById(R.id.ruler);
        text = (TextView) findViewById(R.id.text);
        rulerWidget.setRulerCallBack(new RulerWidget.RulerCallBack() {
            @Override
            public void changeScale(String textScale) {
                text.setText(textScale);

            }
        });
    }
}
