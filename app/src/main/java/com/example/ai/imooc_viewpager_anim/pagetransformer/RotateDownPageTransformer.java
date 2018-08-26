package com.example.ai.imooc_viewpager_anim.pagetransformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

public class RotateDownPageTransformer implements ViewPager.PageTransformer{

    private static final float MAX_ROTATE = 20f;

    private float mRotate;

    /**
     * A页角度变化0~-20;B页角度变化20~0
     */
    @Override
    public void transformPage(@NonNull View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) {
            view.setRotation(0);

            /**
             * A页切换到B页，A页的position 0.0~-1，B页的position 1~0.0
             */
        } else if (position <= 0) {  // A页 0.0~-1
            // 0~-20
            mRotate = position*MAX_ROTATE;

            view.setPivotX(pageWidth/2);
            view.setPivotY(view.getMeasuredHeight());
            view.setRotation(mRotate);

        } else if (position <= 1) { // B页1~0.0
            // 20 ~ 0
            mRotate = position*MAX_ROTATE;

            view.setPivotX(pageWidth/2);
            view.setPivotY(view.getMeasuredHeight());
            view.setRotation(mRotate);
        } else {

            view.setRotation(0);
        }
    }

}
