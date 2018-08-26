package com.example.ai.imooc_viewpager_anim.pagetransformer;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ViewPager的动画效果
 */
public class DepthPageTransformer implements ViewPager.PageTransformer {

    private static float MIN_SCALE = 0.75f;

    /**
     * A页切换到B页，A页的position 0.0~-1，B页的position 1~0.0
     * 0代表当前屏幕显示的view的position，1代表当前view的下一个view所在的position，-1代表当前view的前一个view所在的position
     * @param view
     * @param position
     */
    @SuppressLint("NewApi")
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)//This page is way off-screen to the left.
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]Use //the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) { // (0,1]// Fade the page out.
            view.setAlpha(1 - position);
            // Counteract the default slide transition
            view.setTranslationX(pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE)
                    * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);

        }
    }
}