package com.example.ai.imooc_viewpager_anim;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.ai.imooc_viewpager_anim.custom.CustomViewPagerWithTransformerAnim;
import com.example.ai.imooc_viewpager_anim.pagetransformer.RotateDownPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class CustomActivity extends AppCompatActivity {

    private CustomViewPagerWithTransformerAnim mViewPager;

    private int[] mImageIds = new int[]{R.drawable.guide1,R.drawable.guide2,R.drawable.guide3};

    private List<ImageView> mImages = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_custom);

        mViewPager = findViewById(R.id.view_pager);

//        // 为ViewPager添加动画效果,3.0后才有效
//        mViewPager.setPageTransformer(true, new RotateDownPageTransformer());

        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return mImageIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mViewPager.removeViewFromPosition(position);
                container.removeView(mImages.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                ImageView imageView = new ImageView(CustomActivity.this);
                imageView.setImageResource(mImageIds[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImages.add(imageView);
                mViewPager.setViewForPosition(imageView,position);
                return imageView;
            }

        });

    }

}
