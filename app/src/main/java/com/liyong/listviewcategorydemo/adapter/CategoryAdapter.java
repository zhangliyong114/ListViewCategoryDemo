package com.liyong.listviewcategorydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liyong.listviewcategorydemo.R;
import com.liyong.listviewcategorydemo.bean.Category;

import java.util.List;

/**
 * 适配器
 * Created by Administrator on 2017/5/9.
 */

public class CategoryAdapter extends BaseAdapter {
    //标题
    private static final int TYPE_CATEGORY_ITEM = 0;
    //标题下的子类内容
    private static final int TYPE_ITEM = 1;
    private List<Category> mListData;
    private LayoutInflater mInflater;

    public CategoryAdapter(Context context, List<Category> pData) {
        mListData = pData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mListData != null) {
            //  所有分类中item的总和是ListVIew  Item的总个数
            for (Category category : mListData) {
                count += category.getItemCount();
            }
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        // 异常情况处理
        if (mListData == null || position < 0 || position > getCount()) {
            return null;
        }
        // 同一分类内，第一个元素的索引值
        int categroyFirstIndex = 0;
        for (Category category : mListData) {
            int size = category.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            // item在当前分类内
            if (categoryIndex < size) {
                return category.getItem(categoryIndex);
            }
            // 索引移动到当前分类结尾，即下一个分类第一个元素索引
            categroyFirstIndex += size;
        }
        return null;
    }

    /**
     * 获取加载不同的布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        // 异常情况处理
        if (mListData == null || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }
        int categroyFirstIndex = 0;
        for (Category category : mListData) {
            int size = category.getItemCount();
            // 在当前分类中的索引值
            int categoryIndex = position - categroyFirstIndex;
            if (categoryIndex == 0) {
                return TYPE_CATEGORY_ITEM;
            }
            categroyFirstIndex += size;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_CATEGORY_ITEM:    //加载标题布局
                if (null == convertView) {
                    convertView = mInflater.inflate(R.layout.listview_item_header, null);
                }
                TextView textView = (TextView) convertView.findViewById(R.id.header);
                String itemValue = (String) getItem(position);
                textView.setText(itemValue);
                break;
            case TYPE_ITEM:     //加载列表布局
                ViewHolder viewHolder = null;
                if (null == convertView) {
                    convertView = mInflater.inflate(R.layout.listview_item, null);
                    viewHolder = new ViewHolder();
                    viewHolder.content = (TextView) convertView.findViewById(R.id.content);
                    viewHolder.contentIcon = (ImageView) convertView.findViewById(R.id.content_icon);
                    convertView.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) convertView.getTag();
                }
                // 绑定数据
                viewHolder.content.setText((String) getItem(position));
                viewHolder.contentIcon.setImageResource(R.mipmap.img_rand_icon);
                break;
        }

        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != TYPE_CATEGORY_ITEM;
    }


    /**
     * 行布局
     */
    private class ViewHolder {
        TextView content;
        ImageView contentIcon;
    }
}
