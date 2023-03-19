package com.grozziie.testingApplications;

import android.view.ViewGroup;

import com.google.android.gms.dynamite.DynamiteModule;
import com.smarteist.autoimageslider.SliderViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {
    List<SliderItem>sliderItems=new ArrayList<>();
    Context context;
    Animation animation;

    // constructor for our adapter class.
    public SliderAdapter(Context context, List<SliderItem> mSliderItems) {
        this.context = context;
        this.sliderItems = mSliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        // inside the on Create view holder method we are
        // inflating our layout file which we have created.
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        try {
            animation= AnimationUtils.loadAnimation(viewHolder.itemView.getContext(),R.anim.alerter_slide_in_from_top);
            Picasso.get().load(sliderItems.get(position).getImage()).placeholder(R.drawable.drake_error).into(viewHolder.imageView);
            viewHolder.imageView.setAnimation(animation);
        }
        catch (Exception e) {
        }


    }

    @Override
    public int getCount() {
        // returning the size of our array list.
        return sliderItems.size();
    }

    // view holder class for initializing our view holder.
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        // variables for our view and image view.
        View itemView;
        ImageView imageView;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            itemView=this.itemView;
            imageView=(ImageView)itemView.findViewById(R.id.darkparent);


        }
    }
}