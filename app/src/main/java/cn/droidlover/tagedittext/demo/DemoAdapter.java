package cn.droidlover.tagedittext.demo;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import cn.droidlover.tagedittext.TagSpan;
import cn.droidlover.tagedittext.TagTextView;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/12/19 18:48
 * @editTime
 * @editor
 */
public class DemoAdapter extends SimpleListAdapter<String, DemoAdapter.ViewHolder> {


    String str = "#活动一##面试#今年下半年以来，东北经济部分数据出现企稳的苗头，但仍面临不少问题困难。李克强#活动二#明确表示，推动东北经济脱困向好，实现新#活动三#一轮振兴，事关全国经济发展和转型升级大局，事关区域协调发展全局";

    public DemoAdapter(Context context) {
        super(context);
    }


    @Override
    protected ViewHolder newViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_demo;
    }

    @Override
    protected void convert(ViewHolder holder, String item, int position) {
        holder.tvTag
                .tagColor(Color.RED)        //设置标签颜色
                .tag("#", "#");     //设置前后匹配符

        holder.tvTag.setCallback(new TagSpan.Callback<String>() {     //设置标签点击事件
            @Override
            public void onClick(String data) {
                Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
            }
        });
        holder.tvTag.text(str);
    }

    public static class ViewHolder {

        TagTextView tvTag;

        public ViewHolder(View itemView) {
            tvTag = (TagTextView) itemView.findViewById(R.id.tv_tag);
        }
    }
}
