package com.liyong.listviewcategorydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.liyong.listviewcategorydemo.adapter.CategoryAdapter;
import com.liyong.listviewcategorydemo.bean.Category;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_list;
    private List<Category> listData;
    private CategoryAdapter mCustomBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化UI
        initViews();
        //加载数据
        initData();
    }

    /**
     * 初始化UI
     */
    private void initViews() {
        lv_list = (ListView) findViewById(R.id.lv_list);

    }

    /**
     * 加载数据
     */
    private void initData() {
        // 数据
        listData = getData();
        mCustomBaseAdapter = new CategoryAdapter(getApplicationContext(), listData);
        // 适配器与ListView绑定
        lv_list.setAdapter(mCustomBaseAdapter);
        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), (String) mCustomBaseAdapter.getItem(position),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 创建测试数据
     *
     * @return
     */
    public List<Category> getData() {
        List<Category> listData = new ArrayList<>();
        Category categoryOne = new Category("Java");
        categoryOne.addItem("Java编程思想");
        categoryOne.addItem("Java从入门到精通");
        categoryOne.addItem("深入理解Java虚拟机:JVM高级特性与最佳实践");
        categoryOne.addItem("Java核心技术(卷1):基础知识");
        Category categoryTwo = new Category("Android");
        categoryTwo.addItem("第一行代码");
        categoryTwo.addItem("Android软件安全与逆向分析");
        categoryTwo.addItem("Android应用UI设计模式");
        Category categoryThree = new Category("HTML5");
        categoryThree.addItem("HTML5+CSS3从入门到精通");
        categoryThree.addItem("响应式Web设计:HTML5和CSS3实战");
        categoryThree.addItem("HTML5移动Web开发实战详解");
        categoryThree.addItem("疯狂HTML 5/CSS3/JavaScript讲义");
        listData.add(categoryOne);
        listData.add(categoryTwo);
        listData.add(categoryThree);
        return listData;
    }
}
