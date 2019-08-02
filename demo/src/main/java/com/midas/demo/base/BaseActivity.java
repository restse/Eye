package com.midas.demo.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by midas on 2019/5/30
 * desc: BaseActivity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
//        Intent intent = getIntent();
//        if (intent != null) {
//            getIntent(intent);
//        }
        initView();
        initData();
        //  addActivityToStack();
    }


    /**
     * 加载布局
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /**
     * 获取 Intent 数据
     * @param intent the intent
     */
    // protected abstract void getIntent(Intent intent);

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(View.OnClickListener onClickListener, Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * 添加进栈
     */
//    private void addActivityToStack() {
//        AppActivityManager.getInstance().addActivity(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        AppActivityManager.getInstance().removeActivity(this);
//        super.onDestroy();
//    }
}
