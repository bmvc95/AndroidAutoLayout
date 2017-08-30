在做android项目中，遇到最多的问题就是适配，各种尺寸的机型，让开发者头痛，自己在开发中也存在这个问题，不过我目前的公司大多项目是demo的性质，但是最近一个产品化的项目，让我在适配问题上遇到了不少困难。

于是想要使用别人的研究成果，就找到了鸿祥大神写的https://github.com/hongyangAndroid/AndroidAutoLayout 库，他的设计思路是根据UI设计的效果图尺寸，然后在不同手机上根据比例进行缩放，感觉满足绝大多数适配需求，可惜大神太忙，这个库已经停止维护了。

针对鸿祥的设计思路，自己也写了一个自动布局的库https://github.com/tenny1225/AndroidAutoLayout，目地也是使用方式简单，尽量少改现有的代码。以下有部分照搬鸿祥博客里面的内容

假设我们拿到一张设计图：
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/874510-433ad9d2d6d2c402.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这样的设计图开发中很常见吧，有些公司可能需要自己去测量。

按照我们的思想：

`布局直接抄设计图上的尺寸`

对于布局文库应该这么写：
```java
<com.xz.autoLayout.AutoRelativeLayout
android:layout_width="match_parent"
android:layout_height="86px"
android:layout_marginTop="26px"
android:background="#ffffffff">

<ImageView
    android:id="@+id/id_tv_add"
    android:layout_width="34px"
    android:layout_height="34px"
    android:layout_gravity="center_vertical"
    android:layout_marginLeft="276px"
    android:layout_marginTop="26px"
    android:src="@mipmap/add"
    />

<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_centerVertical="true"
    android:layout_marginLeft="26px"
    android:layout_toRightOf="@id/id_tv_add"
    android:text="新增旅客"
    android:textColor="#1fb6c4"
    android:textSize="32px"
    />
</com.xz.autoLayout.AutoRelativeLayout>
```
可以看到只有第一个RelativeLayout修改成了自定义的AutoRelativeLayout，就可以做到根据设计图，自动适配。
###用法
1. 设定设计图尺寸

在Application的oncreate方法中加入：
```java
  AutoLayoutManager.init(this,720,1280,false);//720×1280是ui设计图的尺寸
//第四隔参数是设计图是否有状态栏
```
2.修改布局文件
将所有布局文件中的第一个viewgroup修改成相应的Auto...Layout，目前实现的有
AutoLinearLayout，AutoRelativeyout，AutoFrameLayout，对于在其他的viewgroup，可以实现IAuto接口重写相应方法进行适配，具体参考AutoLinearLayout：
```java
<?xml version="1.0" encoding="utf-8"?>
<com.xz.autoLayout.AutoLinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="140px"
    android:layout_height="140px"
    android:gravity="center"
    android:orientation="vertical">
    <ImageView
        android:id="@+id/iv_icon"
        android:layout_width="80px"
        android:layout_height="80px"
        android:src="@mipmap/ic_launcher"
       android:tag="w-h" />

    <TextView
        android:id="@+id/tv_action_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="8px"
        android:gravity="center"
        android:text="@string/next"
        android:textColor="?attr/actionBarTextColor"
        android:textSize="22px"

        />

</com.xz.autoLayout.AutoLinearLayout>
```
4.直接适配
可以调用auto方法进行适配，例如在baseActivity或者baseFragment中使用
```java
AutoUtil.auto(View v)
```
3. 对与长宽一致性问题
由于长宽缩放的存在，所有在布局文件中，长宽同时设置为200px，其实最后显示他们长宽是不同的，此时可以设置tag "w-h"和"h-w"属性
```java
<TextView
        android:layout_width="85px"
        android:layout_height="70px"
        android:layout_alignTop="@+id/iv_edit"
        android:layout_marginLeft="30px"
         android:tag="w-h" 
        android:layout_marginTop="30px"
        android:background="#aaffffff"
        android:gravity="center"
        android:text="@string/compare"
        android:textColor="?attr/actionBarTextColor" />
```
`"w-h"`表示宽度的尺寸缩放参考高度，`"h-w"`表示高度的尺寸缩放参考宽度。

4. 适配模式
这个库默认对所有view的所有属性进行了适配，所以就算你设置了某个属性值为12dp，它还是把他当做px对待进行缩放，所以对于不需要进行缩放的view，可以在tag中加入相应标志，让库忽略掉对应属性，下表面列出来可识别的tag标志

`not` 表示对于view的所有属性都不适配

`!w`不适配宽度

`!h`不适配高度

`!p`不适配padding

`!pl`不适配paddingLeft

`!pt`不适配paddingTop

`!pr`不适配paddingRight

`!pb`不适配paddingBottom

`!m`不适配margin

`!ml`不适配marginLeft

`!mt`不适配marginTop

`!mr`不适配marginRight

`!mb`不适配marginBottom

`!maw`不适配maxWidth

`!mah`不适配maxHeight

`!miw`不适配minWidth

`!mih`不适配minHeight

`!ts`不适配TextSize

`w-h`width参照height缩放

`h-w`height参照width缩放

`ml-h`marginLeft参照height缩放

`mr-h`marginRight参照height缩放

`mt-w`marginTop参照width缩放

`mb-w`marginBottom参照width缩放

`pl-h`paddingLeft参照height缩放

`pr-h`paddingRight参照height缩放

`pt-w`paddingTop参照width缩放

`pb-w`paddingBottom参照width缩放

`maw-h`maxWidth参照height缩放

`mah-w`maxHeight参照width缩放

`miw-h`minWidth参照height缩放

`mih-w`minHeight参照width缩放

`ts-w`TextSize参照width缩放

```java
<TextView
        android:layout_width="85px"
        android:layout_height="70px"
        android:layout_alignTop="@+id/iv_edit"
        android:layout_marginLeft="30px"
         android:tag="w-h,ml-h"   <!--width参照height缩放,marginLeft参照height缩放-->
        android:layout_marginTop="30px"
        android:background="#aaffffff"
        android:gravity="center"
        android:text="@string/compare"
        android:textColor="?attr/actionBarTextColor" />
```
