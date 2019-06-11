package cn.com.glodv.demo;


import cn.com.goldv.netlib.exception.ServerException;
import cn.com.goldv.netlib.function.observer.HttpObserver;

/**
 * Desc:可用于mvp等自定义,统一处理,
 * Created by cheegon on 4/29/2019.
 */
public abstract class CustomObserver<T> extends HttpObserver<T> {
    private BaseView mBaseView;

    public CustomObserver(BaseView baseView) {
        mBaseView = baseView;
    }

    public void loadCompleted() {
        if (mBaseView != null) {
            mBaseView.hideProgressDialog();
        }
    }

    @Override
    public void loadError(Throwable throwable) {
        if (mBaseView != null) {
            mBaseView.dealRspError(throwable);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mBaseView != null) {
            mBaseView.showProgressDialog();
        }
    }

    @Override
    public void loadFailed(ServerException e) {
        if (mBaseView != null) {
            mBaseView.dealReqFailed(e);
        }
    }

    @Override
    public void loadSessionException(String s) {
        if (mBaseView != null) {
            mBaseView.dealSessionException(s);
        }
    }
}
