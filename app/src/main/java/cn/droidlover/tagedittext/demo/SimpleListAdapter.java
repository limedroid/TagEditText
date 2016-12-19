package cn.droidlover.tagedittext.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wanglei on 2016/12/1.
 */

public abstract class SimpleListAdapter<T, H> extends XListAdapter<T> {

    public SimpleListAdapter(Context context) {
        super(context);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        H holder = null;
        T item = data.get(position);

        if (convertView == null) {
            convertView = View.inflate(context, getLayoutId(), null);
            holder = newViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (H) convertView.getTag();
        }

        convert(holder, item, position);

        return convertView;
    }

    protected abstract H newViewHolder(View convertView);

    protected abstract int getLayoutId();

    protected abstract void convert(H holder, T item, int position);
}
