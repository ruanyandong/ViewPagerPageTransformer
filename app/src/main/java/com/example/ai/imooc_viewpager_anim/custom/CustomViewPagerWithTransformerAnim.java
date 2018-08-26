package com.example.ai.imooc_viewpager_anim.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewPager的缓存机制，一次只能缓存3个page，
 */
public class CustomViewPagerWithTransformerAnim extends ViewPager {

    private View mLeft;
    private View mRight;
    private float mTrans;
    private float mScale;
    private static final float MIN_SCALE = 0.5f;

    private Map<Integer,View> mChildren = new HashMap<Integer, View>();


    public void setViewForPosition(View view,int position){
        mChildren.put(position,view);

    }

    public void removeViewFromPosition(int position){
        mChildren.remove(position);
    }

    public CustomViewPagerWithTransformerAnim(@NonNull Context context) {
        super(context);
    }

    public CustomViewPagerWithTransformerAnim(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 第0页到第一页：position = 0；offset：0~1；最后滑动停止position=1，offset=0
     * 第一页到第0页：position = 0;offset:1~0;
     *
     * 第一页到第二页：position = 1；offset：0~1；最后滑动停止position=2，offset=0
     * 第二页到第一页：position = 1；offset：1~0；
     *
     * @param position
     * @param offset
     * @param offsetPixels 屏幕的宽，即每滑动一页的偏移距离
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        Log.d("TAG", "onPageScrolled:position=="+position+" offset=="+offset+"  offsetPixels=="+offsetPixels);

        mLeft = mChildren.get(position);
        mRight = mChildren.get(position+1);

        animStack(mLeft,mRight,offset,offsetPixels);
        super.onPageScrolled(position, offset, offsetPixels);
    }

    private void animStack(View left, View right, float offset, int offsetPixels) {

        if (right != null){
            // 从0页到1页，offset：0~1
            mScale = (1-MIN_SCALE)*offset+MIN_SCALE;
            // ?????
            mTrans = -getWidth()-getPageMargin()+offsetPixels;

            right.setScaleX(mScale);
            right.setScaleY(mScale);

            right.setTranslationX(mTrans);
        }
        if (left!=null){
            left.bringToFront();
        }
    }
}
