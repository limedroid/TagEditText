# TagEditText，类似微博标签的文本控件

[TagEditText](https://github.com/limedroid/TagEditText)实现了显示类似微博中的活动标签， 其特点是将一段文字中用'#'包裹的部分以特殊的颜色显示，并能进行点击交互。

<p align="center">
  <img src="/art/tagedittext.png" alt="TagEditText" />
</p>

## 主要功能

* 自定义包裹符号，如# ... # 或者 * ... *，或者 # ... * ，具体什么符号可以自定义，默认是 # ... #
* 自定义匹配的标签字体颜色
* 自定义标签点击事件

## 使用

* Gradle ： **compile 'cn.droidlover:TagEditText:1.0.0''**
* Github ： [TagEditText](https://github.com/limedroid/TagEditText)

## 说明
库中主要包括两个控件:
* TagEditText ：主要用于用户评论的编辑，用户可输入类似#...#的内容。
* TagTextView ：主要用于显示内容。

## 主要用法

#### TagTextView

```xml
 <cn.droidlover.tagedittext.TagTextView
        android:id="@+id/tagTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp"
        android:textSize="14sp" />
```
在java中
```java
tagTextView
           .tagColor(Color.RED)        //设置标签颜色
           .tag("#", "#");             //设置前后匹配符

tagTextView.setCallback(new TagSpan.Callback<String>() {     //设置标签点击事件
    @Override
    public void onClick(String data) {
        toast(data);
    }
})

tagTextView.text(str);                      //设置内容
```

#### TagEditText

```xml
<cn.droidlover.tagedittext.TagEditText
        android:id="@+id/tagEditText"
        android:layout_width="match_parent"
        android:hint="TagEditText"
        android:layout_height="wrap_content"
        android:layout_marginTop="50dp"
        android:background="@android:color/transparent"
        android:padding="16dp"
        android:textSize="14sp" />
```

在java中

```java
tagEditText
        .tag("#", "#")              //设置前后匹配符
        .tagColor(Color.BLUE);      //设置标签颜色
tagEditText.appendText("");              //追加文本
tagEditText.getTagList();                //获取标签集合
tagEditText.removeText("");              //删除指定
```









