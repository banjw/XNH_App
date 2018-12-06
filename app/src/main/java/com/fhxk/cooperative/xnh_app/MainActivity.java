package com.fhxk.cooperative.xnh_app;

import android.graphics.drawable.Drawable;
import android.support.percent.PercentFrameLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    //背景色
    private int selectedBackground;
    private int background;
    private int menuTextSelectColor;
    private int menuBackground;
    private int menuTextColor;
    private Drawable menuBorder;


    //底部容器
    private LinearLayout paymentLayout;
    private LinearLayout personChangeLayout;
    private LinearLayout paymentChangeLayout;
    //顶部
    private ImageView back;
    private TextView title;
    private ImageView search;
    //菜单
    private PercentFrameLayout unpaidFamilyLayout;
    private TextView unpaidFamily;
    private TextView paidFamily;
    //搜索框
    private PercentFrameLayout searchInputLayout;
    private EditText searchInput;
    private ImageView searchImg;

    private RecyclerView recyclerView;
    private List<Family> familyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏自带actionbar
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }
        selectedBackground = getResources().getColor(R.color.select_background,null);
        background = getResources().getColor(R.color.background,null);
        menuTextSelectColor = getResources().getColor(R.color.menu_text_select_color,null);
        menuBackground = getResources().getColor(R.color.menu_background,null);
        menuTextColor = getResources().getColor(R.color.menu_text_color,null);
        menuBorder = getResources().getDrawable(R.drawable.menu_border, null);

        final String paymentStr = getResources().getString(R.string.bottom_payment);
        final String psersonChangeStr = getResources().getString(R.string.bottom_person_change);
        final String paymentChangeStr = getResources().getString(R.string.bottom_payment_change);

        //默认选中缴费选项
        paymentLayout = (LinearLayout) findViewById(R.id.payment);
        paymentLayout.setBackgroundColor(selectedBackground);
        title = (TextView) findViewById(R.id.title);
        title.setText(paymentStr);
        unpaidFamily = (TextView) findViewById(R.id.unpaid_family);
        paidFamily = (TextView) findViewById(R.id.paid_family);
        unpaidFamilyLayout = (PercentFrameLayout) findViewById(R.id.unpaid_family_layout);
        searchInputLayout = (PercentFrameLayout) findViewById(R.id.search_input_layout);
        searchInput = (EditText) findViewById(R.id.search_input);
        searchImg = (ImageView) findViewById(R.id.search_img);
        searchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: searchImg");
                Toast.makeText(MainActivity.this,searchInput.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        initFamilies();//初始化数据
        recyclerView = (RecyclerView) findViewById(R.id.family_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FamilyAdapter familyAdapter = new FamilyAdapter(familyList);
        recyclerView.setAdapter(familyAdapter);

        paymentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: paymentLayout");
                paymentLayout.setBackgroundColor(selectedBackground);
                personChangeLayout.setBackgroundColor(background);
                paymentChangeLayout.setBackgroundColor(background);
                title.setText(paymentStr);
            }
        });

        personChangeLayout = (LinearLayout) findViewById(R.id.person_change);
        personChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: personChangeLayout");
                paymentLayout.setBackgroundColor(background);
                personChangeLayout.setBackgroundColor(selectedBackground);
                paymentChangeLayout.setBackgroundColor(background);
                title.setText(psersonChangeStr);
            }
        });
        paymentChangeLayout = (LinearLayout) findViewById(R.id.payment_change);
        paymentChangeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: paymentChangeLayout");
                paymentLayout.setBackgroundColor(background);
                personChangeLayout.setBackgroundColor(background);
                paymentChangeLayout.setBackgroundColor(selectedBackground);
                title.setText(paymentChangeStr);
            }
        });
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: back");
            }
        });
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: search");
                //显示输入框
                int state = searchInputLayout.getVisibility();
                if(state==View.GONE){
                    searchInputLayout.setVisibility(View.VISIBLE);
                }else {
                    searchInputLayout.setVisibility(View.GONE);
                }
            }
        });
        unpaidFamilyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: unpaidFamily");
                unpaidFamilyLayout.setBackground(menuBorder);
                unpaidFamily.setTextColor(menuTextSelectColor);
                paidFamily.setBackgroundColor(menuBackground);
                paidFamily.setTextColor(menuTextColor);
            }
        });
        paidFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: paidFamily");
                paidFamily.setBackground(menuBorder);
                paidFamily.setTextColor(menuTextSelectColor);
                unpaidFamilyLayout.setBackgroundColor(menuBackground);
                unpaidFamily.setTextColor(menuTextColor);
            }
        });
    }

    private void initFamilies(){
        for(int i = 0; i < 5; i++){
            Family f1 = new Family("赵能素[家]","5203010101010001");
            Family f2 = new Family("赵祥生[家]","5203010101010002");
            Family f3 = new Family("赵兰[家]","5203010101010003");
            Family f4 = new Family("冉义顺[家]","5203010101010004");
            Family f5 = new Family("王仲武[家]","5203010101010005");
            familyList.add(f1);
            familyList.add(f2);
            familyList.add(f3);
            familyList.add(f4);
            familyList.add(f5);
        }
    }

}
