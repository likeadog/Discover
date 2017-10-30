package com.zhuang.discover.myview;

import android.content.Context;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zhuang on 2017/10/27.
 */

public class TextClickableSpan  extends ClickableSpan {


    @Override
    public void onClick(View widget) {
        Toast.makeText(widget.getContext(),"hello",Toast.LENGTH_LONG).show();
        Log.e("zhuang","click");
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(0xff507daf);
        ds.setUnderlineText(false);
    }
}