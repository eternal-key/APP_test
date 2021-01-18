package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class SignActivity extends AppCompatActivity{
    private TextView tv2;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new MyAMapLocationListener();
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        init();
        tv2 = findViewById(R.id.tv_2);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到另一个界面
                Intent intent = new Intent(SignActivity.this, SignActivity2.class);
                startActivity(intent);
                Toast.makeText(SignActivity.this,"操作成功",Toast.LENGTH_SHORT).show();//设置点击事件
            }
        });

        EditText tvtab1 = findViewById(R.id.et_1);
        Drawable drawable1 = getResources().getDrawable(R.drawable.location2) ;
        drawable1.setBounds(0,0,80,80);
        tvtab1.setCompoundDrawables(null,null,drawable1,null);
        tvtab1.setCursorVisible( false );


    }

    private void init() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(false);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();


    }

    //定位成功回调信息，设置相关消息
    // aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
    //aMapLocation.getLatitude();//获取纬度
    //aMapLocation.getLongitude();//获取经度
    //aMapLocation.getAccuracy();//获取精度信息
    //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //Date date = new Date(aMapLocation.getTime());
    //df.format(date);//定位时间
    // aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    // aMapLocation.getCountry();//国家信息
    //aMapLocation.getProvince();//省信息
    // aMapLocation.getCity();//城市信息
    // aMapLocation.getDistrict();//城区信息
    //aMapLocation.getStreet();//街道信息
    //aMapLocation.getStreetNum();//街道门牌号信息
    //aMapLocation.getCityCode();//城市编码
    //aMapLocation.getAdCode();//地区编码
    // mSetting.putString(Setting.CITY_NAME, aMapLocation.getCity());


    private class MyAMapLocationListener implements AMapLocationListener {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                EditText et_1 =findViewById(R.id.et_1);

                et_1.setText(aMapLocation.getStreetNum()+"纬度:"+aMapLocation.getLatitude()+"经度 ："+aMapLocation.getLongitude());
                if (aMapLocation.getErrorCode() == 0) {



                    Log.e("位置：", aMapLocation.getAddress());

                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }

    }
}
