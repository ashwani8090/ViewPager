package com.example.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ViewAdapter extends PagerAdapter {

    private final Context context;
    private final List<String> list;
    private TextView textView;

    public ViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // return super.instantiateItem(container, position);
/*
        LayoutInflater layoutInflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
*/


        LayoutInflater layoutInflater=LayoutInflater.from(context);
//        View view = layoutInflater.from(context).inflate(R.layout.view_page, container, true);

        ViewGroup view=(ViewGroup)layoutInflater.inflate(R.layout.view_page,container,false);
        textView = view.findViewById(R.id.textView);

        textView.setText(list.get(position));

        container.addView(view);
        Toast.makeText(context, "" + list.get(position), Toast.LENGTH_SHORT).show();

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //  super.destroyItem(container, position, object);

        container.removeView((View) object);


    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }
}
