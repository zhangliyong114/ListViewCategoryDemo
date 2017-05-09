package com.liyong.listviewcategorydemo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类实体类
 * Created by Administrator on 2017/5/9.
 */

public class Category {
    //标题
    private String mCategoryName;
    //每个标题下的集合
    private List<String> mCategoryItem = new ArrayList<String>();

    public Category(String mCategroyName) {
        mCategoryName = mCategroyName;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void addItem(String pItemName) {
        mCategoryItem.add(pItemName);
    }

    /**
     * 获取Item内容
     *
     * @param pPosition
     * @return
     */
    public String getItem(int pPosition) {
        // Category排在第一位
        if (pPosition == 0) {
            return mCategoryName;
        } else {
            return mCategoryItem.get(pPosition - 1);
        }
    }

    /**
     * 当前类别Item总数。Category也需要占用一个Item
     *
     * @return
     */
    public int getItemCount() {
        return mCategoryItem.size() + 1;
    }

}
