package cn.com.glodv.demo;


import cn.com.goldv.netlib.exception.ServerException;

/**
 *
 *可用于mvp
 */

public interface BaseView {

    void hideProgressDialog();

    void showProgressDialog();

    void dealSessionException(String s);

    void dealReqFailed(ServerException e);

    void dealRspError(Throwable t);
}
