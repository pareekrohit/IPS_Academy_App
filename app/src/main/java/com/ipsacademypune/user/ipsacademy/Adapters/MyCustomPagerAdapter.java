package com.ipsacademypune.user.ipsacademy.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ipsacademypune.user.ipsacademy.R;

public class MyCustomPagerAdapter extends PagerAdapter {

    Context context;
    int images[];
    LayoutInflater layoutInflater;

    int pos = SaveImagePosition_pref.getInt("position");

    public MyCustomPagerAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);

//        if (pos!=-1){
//            position=pos;
//        }


        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        container.addView(itemView);

        System.out.println("position//// :"+position);
//        SaveImagePosition_pref sp=new SaveImagePosition_pref(context);
//        sp.setInt("position",-1);

        //listening to image click
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
            }
        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
