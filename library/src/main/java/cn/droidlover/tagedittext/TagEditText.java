package cn.droidlover.tagedittext;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagEditText extends EditText {

    private String prefixTag = "#";
    private String endTag = "#";
    private String tagRegex = DEF_REGEX;

    public static final String DEF_REGEX = "(#.+?#)";     //默认的正则表达式
    private boolean isChanged;

    private int tagColor=-1;


    public TagEditText(Context context) {
        super(context);
        initView();
    }

    public TagEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TagEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable text) {
                if (TextUtils.isEmpty(text)) return;

                if (isChanged) return;


                if (text.toString().contains(prefixTag) || text.toString().contains(endTag)) {
                    if (TextUtils.isEmpty(tagRegex)) tagRegex = DEF_REGEX;

                    Pattern pattern = Pattern.compile(tagRegex);
                    Matcher matcher = pattern.matcher(text);

                    ArrayList<String> tagList = new ArrayList<>();
                    while (matcher.find()) {
                        tagList.add(matcher.group(1));
                    }

                    if (tagList.isEmpty()) {
                        return;
                    }

                    int lastPos = 0;
                    SpannableStringBuilder builder = new SpannableStringBuilder(text);
                    for (String item : tagList) {
                        int pos = text.toString().indexOf(item, lastPos);
                        SpannableString span = TagSpan.getSpan(tagColor, item, item, null);
                        builder = builder.delete(pos, pos + span.length());
                        builder.insert(pos, span);
                        lastPos = pos + span.length();
                    }

                    isChanged = true;
                    setText(builder);
                    isChanged = false;

                    setSelection(builder.length());

                    invalidate();
                }


            }
        });
    }

    /**
     * 设置tag
     *
     * @param prefixTag
     * @param endTag
     */
    public TagEditText tag(String prefixTag, String endTag) {
        if (TextUtils.isEmpty(prefixTag) || TextUtils.isEmpty(endTag)) return this;

        this.prefixTag = prefixTag;
        this.endTag = endTag;

        tagRegex = new StringBuilder("(")
                .append(prefixTag)
                .append(".+?")
                .append(endTag)
                .append(")")
                .toString();
        return this;
    }

    /**
     * 设置标签颜色
     *
     * @param tagColor
     * @return
     */
    public TagEditText tagColor(int tagColor) {
        this.tagColor = tagColor;
        return this;
    }


    /**
     * 追加文本
     *
     * @param text
     */
    public void appendText(String text) {
        if (TextUtils.isEmpty(text)) return;

        SpannableStringBuilder builder = new SpannableStringBuilder(getText());
        builder.append(text);
        setText(builder);
    }


    /**
     * 删除文本[删除最后匹配]
     *
     * @param text
     */
    public void removeText(String text) {
        if (TextUtils.isEmpty(text)) return;

        SpannableStringBuilder builder = new SpannableStringBuilder(getText());
        int pos = text.toString().indexOf(text);
        if (pos > -1) {
            try {
                builder = builder.delete(pos, pos + text.length());
                setText(builder);
            } catch (Exception e) {
            }

        }
    }

    /**
     * 获取标签集合
     *
     * @return
     */
    public List<String> getTagList() {
        Pattern pattern = Pattern.compile(tagRegex);
        Matcher matcher = pattern.matcher(getText());

        Set<String> picSet = new HashSet<>();
        ArrayList<String> tagList = new ArrayList<>();

        while (matcher.find()) {
            picSet.add(matcher.group(1).replace(prefixTag, "").replace(endTag, ""));
        }

        tagList.clear();
        tagList.addAll(picSet);     //去重
        return tagList;
    }


}

