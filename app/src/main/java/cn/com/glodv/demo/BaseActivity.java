package cn.com.glodv.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.com.goldv.netlib.exception.ServerException;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Desc:
 * Created by cheegon on 5/5/2019.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected CompositeDisposable mCompositeDisposable;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        mContext = this;
        mCompositeDisposable = new CompositeDisposable();
        initView();
        initListener();
        initData();
    }


    protected void initListener() {

    }

    protected void initView() {

    }

    protected void initData() {

    }

    @LayoutRes
    protected abstract int getLayoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.dispose();
    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dealSessionException(String s) {
        // 需要跳转重新登录
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dealReqFailed(ServerException e) {

        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dealRspError(Throwable t) {
        Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
