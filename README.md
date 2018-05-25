# PagerRecyclerViewExample(test)

ScreenShot
-----------
![Demo Screenshot][1]


How it works?
----------------------
We first define 2 different views: 1.header view 2.normal view (in this case, a CardVIew).

RecyclerView Adapter will determine which type of view it will gets based on the index of the recyclerview item. (Here we only have two cases: a header or not a header.)

Add it to your project
----------------------
 In your build.gradle: [version may vary]

```groovy

dependencies{
    compile  compile 'com.android.support:recyclerview-v7:24.2.1'
}

```
In this example, I used CardView to fill the RecyclerView, so the dependency for this specific example needs to compile CardView.
But hey, if you don't need it for this to work. :)

Usage
-----

Define `HeaderView` in xml layout with custom attributes.
```xml
<FrameLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="220dp"
    android:id="@+id/imageViewer"
    android:layout_below="@+id/toolbar">

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="25dip"
        android:layout_gravity="bottom"
        android:gravity="center"
        android:orientation="vertical">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="3dip"
            android:gravity="center" >

            <View
                android:id="@+id/v_dot0"
                style="@style/dot_style"
                android:background="@drawable/dot_focused" />

            <View
                android:id="@+id/v_dot1"
                style="@style/dot_style" />

            <View
                android:id="@+id/v_dot2"
                style="@style/dot_style" />

            <View
                android:id="@+id/v_dot3"
                style="@style/dot_style" />
        </LinearLayout>
    </LinearLayout>

    <android.support.v4.view.ViewPager
        android:id="@+id/vpheader"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_marginBottom="2dp"/>
</FrameLayout>
```

In order to indicate which view we are currently at in the header, we define 2 types of dots: the focused fot and the normal dot.(put them in the drawable)

* the focused dot
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="oval" >
    <corners android:radius="5dip" />
    <solid android:color="#aaFFFFFF" />
</shape>
```

* The normal dot
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval" >
    <corners android:radius="5dip" />
    <solid android:color="#33000000" />
</shape>
```

Now we finished the define of header, so what happen to the rest of the item?<br />
Remember it could be anything you want, I will leave you to decide. :)

Nothing special in the Activity class, everything works the same as defining a normal recycler view.

0. define the flags

```java
 private static final int IS_HEADER = 0;
  private static final int IS_NORMAL = 1;
```


1. RecyclerView Adapter decides which type will it gets by calling the getItemViewType function

```java
  public int getItemViewType(int position) {
    if (position == 0) {
      return IS_HEADER;
    }
    else {
      return IS_NORMAL;
    }
  }
```


2. In RecyclerViewAdapter

```java

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
      dots = new ArrayList<View>(); // an array of indicators
      dots.add(view.findViewById(R.id.v_dot0));
      dots.add(view.findViewById(R.id.v_dot1));
      dots.add(view.findViewById(R.id.v_dot2));
      dots.add(view.findViewById(R.id.v_dot3));
      holder = new RecyclerViewHolder(view,IS_HEADER);
      return holder;
    }

    else if (viewType==IS_NORMAL){
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_recycler_item, viewGroup, false);
      holder = new RecyclerViewHolder(view,IS_NORMAL);
      return holder;
    }
    return null;
  }
```

3. In ViewPagerAdapter

```java
 private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
    private int oldPosition = 0;

    public void onPageSelected(int position) {
      currentItem = position;
      dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
      dots.get(position).setBackgroundResource(R.drawable.dot_focused);
      oldPosition = position;
    }

    public void onPageScrollStateChanged(int arg0) {
    }

    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }
  }
```




Do you want to contribute?
--------------------------

Please, do it! We'd like to improve this library with your help :)











[1]: ./example.gif
