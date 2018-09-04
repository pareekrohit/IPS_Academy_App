package com.ipsacademypune.user.ipsacademy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ipsacademypune.user.ipsacademy.Adapters.ImageAdapter;
import com.ipsacademypune.user.ipsacademy.Adapters.MyCustomPagerAdapter;

public class ImageFullScreenView extends AppCompatActivity {
    ViewPager viewPager;
    MyCustomPagerAdapter myCustomPagerAdapter;

//    ImageAdapter ia=new ImageAdapter(ImageFullScreenView.this);
//    Integer images[]=new Integer[ia.image.length];

    int images[] = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
            R.drawable.i, R.drawable.j};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen_view);

        viewPager = findViewById(R.id.pager);

//        for (int i=0;i>ia.image.length;i++){
//            images[i]=ia.image[i];
//        }


        myCustomPagerAdapter = new MyCustomPagerAdapter(ImageFullScreenView.this, images);
        viewPager.setAdapter(myCustomPagerAdapter);
    }
}
