# AndroidAutoLayout
参考鸿祥的AutoLayout库的设计思路，自己封装的一个android 自动适配库
本库的设计准则的尽量不修改之前的项目代码，做到自动适配，大致的使用方式和鸿祥的AutoLayout库差不多
1.指定参考的设计图尺寸，在Appication的oncreate方法中加入
```java
    AutoLayoutManager.init(this,720,1280,false);//第三个参数是设计图中是否包含状态栏
```
2.将所有的layout文件的第一个viewgroup，换成相关的Auto...Layout,其他的都不用修改,例如
```java

<?xml version="1.0" encoding="utf-8"?>
<com.xz.autoLayout.AutoLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <android.support.v7.widget.Toolbar
        android:layout_width="match_parent"
        android:layout_height="100px"
        android:background="?attr/actionBarColor"
        app:contentInsetStart="0dp">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent">

            <ImageView
                android:id="@+id/btn_close"
                android:layout_width="wrap_content"
                android:layout_height="match_parent"
                android:layout_margin="30px"
                android:src="@mipmap/ic_close_black_36dp"
                android:tint="?attr/actionBarTextColor" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_centerInParent="true"
                android:text="@string/select_size_page_title"
                android:textColor="?attr/actionBarTextColor"
                android:textSize="30px"
                android:textStyle="bold" />
        </RelativeLayout>
    </android.support.v7.widget.Toolbar>

    <View
        android:layout_width="match_parent"
        android:layout_height="0.5dp"
        android:background="?attr/actionBarLineColor" />

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recycler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_marginTop="30px" />

</com.xz.autoLayout.AutoLinearLayout>
```
3.对于recyclerview除了需要上一步操作外，还需要在自定义的adapter中强制每个item测量一次，具体使用
```
 View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_action,parent,false);
 AutoUtil.auto(v);
```
