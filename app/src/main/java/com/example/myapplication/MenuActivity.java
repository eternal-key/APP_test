package com.example.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class MenuActivity extends TabActivity implements View.OnClickListener{

    private static final String TAG = "TabHostActivity";
    private Bundle mbundle = new Bundle();
    private TabHost tab_host;
    private LinearLayout ll_1,ll_2,ll_3,ll_4,ll_5;
    private String FIRST_TAG = "first";
    private String SECOND_TAG = "second";
    private String THIRD_TAG = "third";
    private String FORTH_TAG = "forth";
    private String FIFTH_TAG = "fifth";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mbundle.putString("tag",TAG);
        ll_1 = findViewById(R.id.ll_1);
        ll_2 = findViewById(R.id.ll_2);
        ll_3 = findViewById(R.id.ll_3);
        ll_4 =findViewById(R.id.ll_4);
        ll_5 =findViewById(R.id.ll_5);
        ll_1.setOnClickListener(this);
        ll_2.setOnClickListener(this);
        ll_3.setOnClickListener(this);
        ll_4.setOnClickListener(this);
        ll_5.setOnClickListener(this);
        tab_host = getTabHost();
        tab_host.addTab(getNewTab(FIRST_TAG,R.string.Tabmenu_1,R.drawable.home2,MainActivity.class));
        tab_host.addTab(getNewTab(SECOND_TAG,R.string.Tabmenu_2,R.drawable.shibie, SignActivity.class));
        tab_host.addTab(getNewTab(THIRD_TAG,R.string.Tabmenu_3,R.drawable.files_empty, RectificationActivity.class));
        tab_host.addTab(getNewTab(FORTH_TAG,R.string.Tabmenu_4,R.drawable.user, UserActivity.class));
        tab_host.addTab(getNewTab(FIFTH_TAG,R.string.Tabmenu_5,R.drawable.flickr3, MoreActivity.class));
        changeContainerView(ll_1);
        //设置输入框导入图片的大小
        TextView tvtab1 = findViewById(R.id.tv_tab1);
        TextView tvtab2 = findViewById(R.id.tv_tab2);
        TextView tvtab3 = findViewById(R.id.tv_tab3);
        TextView tvtab4 = findViewById(R.id.tv_tab4);
        TextView tvtab5 = findViewById(R.id.tv_tab5);
        Drawable drawable1 = getResources().getDrawable(R.drawable.home2) ;
        drawable1.setBounds(0,0,80,80);
        tvtab1.setCompoundDrawables(null,drawable1,null,null);
        Drawable drawable2 = getResources().getDrawable(R.drawable.shibie) ;
        drawable2.setBounds(0,0,80,80);
        tvtab2.setCompoundDrawables(null,drawable2,null,null);
        Drawable drawable3 = getResources().getDrawable(R.drawable.files_empty) ;
        drawable3.setBounds(0,0,80,80);
        tvtab3.setCompoundDrawables(null,drawable3,null,null);

        Drawable drawable4 = getResources().getDrawable(R.drawable.user) ;
        drawable4.setBounds(0,0,80,80);
        tvtab4.setCompoundDrawables(null,drawable4,null,null);

        Drawable drawable5 = getResources().getDrawable(R.drawable.flickr3) ;
        drawable5.setBounds(0,0,80,80);
        tvtab5.setCompoundDrawables(null,drawable5,null,null);
    }

    private TabHost.TabSpec getNewTab(String spec,int label,int icon,Class<?>cls) {
        Intent intent = new Intent(this,cls).putExtras(mbundle);
        return tab_host.newTabSpec(spec).setContent(intent)
                .setIndicator(getString(label),getResources().getDrawable(icon));
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.ll_1||view.getId() == R.id.ll_2||view.getId() == R.id.ll_3||view.getId() == R.id.ll_4||view.getId() == R.id.ll_5){
            changeContainerView(view);
        }
    }
    private void changeContainerView(View view){
        ll_1.setSelected(false);
        ll_2.setSelected(false);
        ll_3.setSelected(false);
        ll_4.setSelected(false);
        ll_5.setSelected(false);
        view.setSelected(true);
        if(view == ll_1){
            tab_host.setCurrentTabByTag(FIRST_TAG);
        }else  if(view == ll_2){
            tab_host.setCurrentTabByTag(SECOND_TAG);
        }else  if(view == ll_3){
            tab_host.setCurrentTabByTag(THIRD_TAG);
        }else  if(view == ll_4){
            tab_host.setCurrentTabByTag(FORTH_TAG);
        }else  if(view == ll_5){
            tab_host.setCurrentTabByTag(FIFTH_TAG);
        }
    }

}
