package com.example.podsstore.mainactivityadapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.podsstore.R;
import com.example.podsstore.category.SubCategoryActivity;
import com.example.podsstore.data.response.BestSellingProductResponse;

public class CustomAdapter extends PagerAdapter {
    private CustomAdapter.AdapterListener adapterListener;
private Context context;
    private Activity activity;
    private Integer[] imagesArray;
    private String[] namesArray;


    public void setAdapterListener(CustomAdapter.AdapterListener adapterListener) {
        this.adapterListener = adapterListener;
    }
    public CustomAdapter(Activity activity, Integer[] imagesArray, String[] namesArray) {

        this.activity = activity;
        this.imagesArray = imagesArray;
        this.namesArray = namesArray;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {

        LayoutInflater inflater = (activity).getLayoutInflater();
        //creating  xml file for custom viewpager
        View viewItem = inflater.inflate(R.layout.content_custom, container, false);
        //finding id
        ImageView imageView =  viewItem.findViewById(R.id.imageView);
        //setting data
        imageView.setBackgroundResource(imagesArray[position]);

        container.addView(viewItem);


        viewItem.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //this will log the page number that was click
                Log.i("TAG", "This page was clicked: " + position);
                if(position==0){
                  //  activity.startActivity(new Intent(activity, SubCategoryActivity.class));
                //    activity.getIntent().putExtra("userid","1".toString());
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","5".toString());
                    activity.startActivity(intent);
                }
                else if(position==1){
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","2".toString());
                    activity.startActivity(intent);

                } else if(position==2){
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","3".toString());
                    activity.startActivity(intent);
                } else if(position==3){
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","14".toString());
                    activity.startActivity(intent);
                } else if(position==4){
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","6".toString());
                    activity.startActivity(intent);
                } else if(position==5){
                    Intent intent=new Intent(activity.getBaseContext(),SubCategoryActivity.class);
                    intent.putExtra("userid","4".toString());
                    activity.startActivity(intent);
                }
            }
        });
        if(viewItem.getParent() != null) {
            ((ViewGroup)viewItem.getParent()).removeView(viewItem); // <- fix
        }


        ((ViewPager) container).addView(viewItem, 0);


        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imagesArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub
        return view == object;
    }
    public interface AdapterListener {


        void onItemClick(int position);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }
}