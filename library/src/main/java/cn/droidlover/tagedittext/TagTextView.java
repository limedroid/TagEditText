package cn.droidlover.tagedittext;


import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagTextView extends TextView {
    private String prefixTag = "#";
    private String endTag = "#";
    private String tagRegex = DEF_REGEX;

    private int tagColor = -1;       //标签颜色

    public static final String DEF_REGEX = "(#.+?#)";     //默认的正则表达式

    private TagSpan.Callback<String> callback;

    public TagTextView(Context context) {
        super(context);
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallback(TagSpan.Callback<String> callback) {
        this.callback = callback;
    }

    public TagSpan.Callback<String> getCallback() {
        return callback;
    }

    public void text(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            setText("");
            return;
        }

        if (TextUtils.isEmpty(tagRegex)) tagRegex = DEF_REGEX;

        Pattern pattern = Pattern.compile(tagRegex);
        Matcher matcher = pattern.matcher(text);

        ArrayList<String> tagList = new ArrayList<>();
        while (matcher.find()) {
            tagList.add(matcher.group(1));
        }

        if (tagList.isEmpty()) {
            setText(text);
            return;
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        int lastPos = 0;
        for (String item : tagList) {
            int pos = text.toString().indexOf(item, lastPos);
            SpannableString span = TagSpan.getSpan(tagColor, item, item.replace(prefixTag, "").replace(endTag, ""), getCallback());
            builder = builder.delete(pos, pos + span.length());
            builder.insert(pos, span);
            lastPos = pos + span.length();
        }

        setText(builder);
    }


    /**
     * 设置标签颜色
     *
     * @param tagColor
     * @return
     */
    public TagTextView tagColor(int tagColor) {
        this.tagColor = tagColor;
        return this;
    }

    /**
     * 设置tag
     *
     * @param prefixTag
     * @param endTag
     */
    public void tag(String prefixTag, String endTag) {
        if (TextUtils.isEmpty(prefixTag) || TextUtils.isEmpty(endTag)) return;

        this.prefixTag = prefixTag;
        this.endTag = endTag;

        tagRegex = new StringBuilder("(")
                .append(prefixTag)
                .append(".+?")
                .append(endTag)
                .append(")")
                .toString();

    }


}
