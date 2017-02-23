package samnnyexample.pagerrecyclerviewexample;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sam on 2017-02-12.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.RecyclerViewHolder> {
  private Context context;
  private static final int IS_HEADER = 0;
//  private static final int IS_FOOTER = 3;
  private static final int IS_NORMAL = 1;

  private String[] colorId = new String[]{"#00A5BF", "#1D697C", "#763568", "#A4345D", "#C91F37" ,"#FF4E20" ,"#FFA631"};
  private String[] SampleText = new String[]{"hello", "this", "is", "material", "design","!","!"};


  //view pager
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  private ViewPager viewPager;
  private List<ImageView> imageViews;

  private String[] titles;
  private int[] imageResId = new int[] { R.drawable.ad1, R.drawable.ad2, R.drawable.ad3, R.drawable.ad4 };
  private List<View> dots;
  private TextView tv_title;
  private int currentItem = 0;
  //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++



  //constructor
  public CustomerAdapter(Context context) {
    this.context = context;
  }

  @Override
  public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    RecyclerViewHolder holder;
    // Create different view holder with different flag
    if (viewType == IS_HEADER) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_header, viewGroup, false);

      imageViews = new ArrayList<ImageView>();
      for (int i = 0; i < imageResId.length; i++) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(imageResId[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageViews.add(imageView);

      }
      dots = new ArrayList<View>(); // Pager indicators
      dots.add(view.findViewById(R.id.v_dot0));
      dots.add(view.findViewById(R.id.v_dot1));
      dots.add(view.findViewById(R.id.v_dot2));
      dots.add(view.findViewById(R.id.v_dot3));
      holder = new RecyclerViewHolder(view,IS_HEADER);
      return holder;


    }
//    else if (viewType == IS_FOOTER) {
//      you can define your special footer item here.
//      return holder;
//    }

    else if (viewType==IS_NORMAL){
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_recycler_item, viewGroup, false);
      holder = new RecyclerViewHolder(view,IS_NORMAL);
      return holder;
    }
    return null;
  }

  @Override
  public void onBindViewHolder(final RecyclerViewHolder recyclerViewHolder, int position) {
    //对不同的Item相应不同的操作
    if(position!=0&&position!=colorId.length+1&&recyclerViewHolder.viewType==IS_NORMAL){
      recyclerViewHolder.imgView.setBackgroundColor(Color.parseColor(colorId[position]));
      recyclerViewHolder.txtView.setText(SampleText[position]);
    }
  }

  @Override
  public int getItemCount() {
    return colorId.length;

  }

  @Override
  public int getItemViewType(int position) {
    if (position == 0) {
      return IS_HEADER;
    }
//    else if(position==datas.size()){
//      return IS_FOOTER;
//    }

    else {
      return IS_NORMAL;
    }
  }

  class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public int viewType;

    public ImageView imgView;
    public TextView txtView;
    public RecyclerViewHolder(View itemView,int viewType) {
      super(itemView);
      this.viewType = viewType;
      if(viewType==IS_HEADER){

        ViewPager viewPager = (ViewPager) itemView.findViewById(R.id.vpheader);
        viewPager.setAdapter(new MyAdapter());
        viewPager.setOnPageChangeListener(new MyPageChangeListener());

      }
//      if(viewType==IS_FOOTER){
//        //do some sthing
//      }
      if(viewType==IS_NORMAL){
        txtView = (TextView) itemView.findViewById(R.id.homeTextView);
        imgView = (ImageView) itemView.findViewById(R.id.homeCateView);

      }
    }
  }

  //######################################################################################viewpager  */
  private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
    private int oldPosition = 0;

    public void onPageSelected(int position) {
      currentItem = position;
//      tv_title.setText(titles[position]);
      dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
      dots.get(position).setBackgroundResource(R.drawable.dot_focused);
      oldPosition = position;
    }

    public void onPageScrollStateChanged(int arg0) {
    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
  }

  private class MyAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return imageResId.length;
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
      ((ViewPager) arg0).addView(imageViews.get(arg1));
      return imageViews.get(arg1);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
      ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
      return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }
  }


}
