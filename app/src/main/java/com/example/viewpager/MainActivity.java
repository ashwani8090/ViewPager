package com.example.viewpager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> list;
    ViewPager viewPager;
    TabLayout tabLayout;

    VideoView videoView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.View);
        tabLayout=findViewById(R.id.tabMode);
      //  videoView=findViewById(R.id.videoView3);
        imageView=findViewById(R.id.imageView);
        list=new ArrayList<>();
        list.add("myname");
        list.add("myname1");
        list.add("myname2");


        list.add("myname3");
        list.add("myname4");

        list.add("myname5");


        ViewAdapter viewAdapter=new ViewAdapter(this,list);

        viewPager.setAdapter(viewAdapter);

        tabLayout.setupWithViewPager(viewPager,true);


        Glide.with(this).load(R.raw.loading3).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade()
                .into(imageView);




        viewPager.setPageTransformer(false, new PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                //**ROATAION TRANSFORMATION**//*
                page.setRotationY(position * -30);
            }
        });
       // viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
       /* viewPager.setPageTransformer(false, new PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                *//**ALPHA TRANSFORMATION**//*
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setAlpha(normalizedposition);
            }
        });
*/


    }
}
class ZoomOutPageTransformer implements PageTransformer {
    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        int pageHeight = view.getHeight();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0f);

        } else if (position <= 1) { // [-1,1]
            // Modify the default slide transition to shrink the page as well
            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
            if (position < 0) {
                view.setTranslationX(horzMargin - vertMargin / 2);
            } else {
                view.setTranslationX(-horzMargin + vertMargin / 2);
            }

            // Scale the page down (between MIN_SCALE and 1)
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            // Fade the page relative to its size.
            view.setAlpha(MIN_ALPHA +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_ALPHA));

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0f);
        }
    }

}