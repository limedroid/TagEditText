package cn.droidlover.tagedittext.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import cn.droidlover.tagedittext.TagEditText;
import cn.droidlover.tagedittext.TagTextView;

public class MainActivity extends AppCompatActivity {

    TagTextView tv_tag;
    TagEditText et_tag;

    String str = "#活动一##面试#今年下半年以来，东北经济部分数据出现企稳的苗头，但仍面临不少问题困难。李克强#活动二#明确表示，推动东北经济脱困向好，实现新#活动三#一轮振兴，事关全国经济发展和转型升级大局，事关区域协调发展全局";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_tag = (TagTextView) findViewById(R.id.tv_tag);
        et_tag = (TagEditText) findViewById(R.id.et_tag);

        tv_tag.tagColor(Color.RED).text(str);

//        et_tag.appendText("");
//        et_tag.getTagList();
//        et_tag.removeText("");
    }
}
