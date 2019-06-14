package cn.com.glodv.demo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.com.goldv.netlib.app.GoldV;
import cn.com.goldv.netlib.entity.client.rsp.LoginEntity;
import cn.com.goldv.netlib.entity.client.rsp.SubAccountEntity;
import cn.com.goldv.netlib.entity.trade.rsp.TodayFundsEntity;
import cn.com.goldv.netlib.exception.ServerException;
import cn.com.goldv.netlib.function.ClientRequestManager;
import cn.com.goldv.netlib.function.TradeRequestManager;
import cn.com.goldv.netlib.function.observer.LoginObserver;
import cn.com.goldv.netlib.function.observer.SubAccountObserver;

public class MainActivity extends BaseActivity {


    private EditText etAccount;
    private EditText etPwd;
    private Button btnLogin;
    private Button btnLogout;
    private Button btnFunds;
    private Button btnTest;
    private TextView tvFunds;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        etAccount = findViewById(R.id.et_account);
        etPwd = findViewById(R.id.et_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogout = findViewById(R.id.btn_logout);
        btnFunds = findViewById(R.id.btn_funds);
        btnTest = findViewById(R.id.btn_test);
        tvFunds = findViewById(R.id.tv_funds);
        etAccount.setText("1300000006");
        etPwd.setText("a123456");

    }

    @Override
    protected void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = etAccount.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                // 登陆接口,为了风控需要申请位置权限
                mCompositeDisposable.add(ClientRequestManager.getInstance().reqLogin(account, pwd, Uitls.getGPS(mContext),
                        new LoginObserver<LoginEntity>() {
                            @Override
                            public void loadSucceeded(LoginEntity loginEntity) {
                                // 响应成功
                                Toast.makeText(mContext, "登录成功:"+loginEntity.bank_no, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void loadFailed(ServerException e) {
                                // 服务器请求错误
                                String code = e.getCode();// 错误码
                                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void loadError(Throwable throwable) {
                                // 网络错误
                                Toast.makeText(mContext, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoldV.getInstance().logout();//登出需要调用
                Toast.makeText(mContext, "登出成功", Toast.LENGTH_SHORT).show();
            }
        });

        btnFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompositeDisposable.add(TradeRequestManager.getInstance()
                        .reqTodayFunds(new CustomObserver<TodayFundsEntity>(MainActivity.this) {
                            @Override
                            public void loadSucceeded(TodayFundsEntity todayFundsEntity) {
                                Toast.makeText(mContext, "请求成功", Toast.LENGTH_SHORT).show();
                                tvFunds.setText("当前资金:" + todayFundsEntity.curr_bal);
                            }
                        })
                );
            }
        });
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   TradeRequestManager.getInstance().reqDeclarationOrder(OrderType.TYPE_OPEN_MORE, "Ag(T+D)", "3513", "1", new HttpObserver<DeclarationEntity>() {
                    @Override
                    public void loadSucceeded(DeclarationEntity declarationEntity) {
                        Toast.makeText(mContext, "报单成功", Toast.LENGTH_SHORT).show();
                    }
                });*/
        /*              TradeRequestManager.getInstance().reqCancelOrder("00000004", new HttpObserver<BaseRspBean>() {
                    @Override
                    public void loadSucceeded(BaseRspBean baseRspBean) {
                        Toast.makeText(mContext,"撤单成功",Toast.LENGTH_SHORT).show();
                    }
                });*/
   /*             TradeRequestManager.getInstance().reqQueryDelegationFlow(FlowQueryType.TYPE_QUERY_BY_DAYS,
                        "1", "100", "1", "", "",
                        new HttpObserver<QueryDelegationFlowListEntity>() {
                            @Override
                            public void loadSucceeded(QueryDelegationFlowListEntity queryHistoryFundListEntity) {
                                Toast.makeText(mContext, "委托单流水请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/
       /*       TradeRequestManager.getInstance().reqQueryDelegationTodayFlow("1", "20",
                        new HttpObserver<QueryDelegationFlowTodayListEntity>() {
                            @Override
                            public void loadSucceeded(QueryDelegationFlowTodayListEntity queryMatchFlowListEntity) {
                                Toast.makeText(mContext, "今日委托单流水请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/
            /*          TradeRequestManager.getInstance().reqQueryMatchFlow(FlowQueryType.TYPE_QUERY_BY_DAYS,
                        "1", "10", "180", "", "",
                        new HttpObserver<QueryMatchFlowListEntity>() {
                            @Override
                            public void loadSucceeded(QueryMatchFlowListEntity queryHistoryFundListEntity) {
                                Toast.makeText(mContext,"成交单流水请求成功",Toast.LENGTH_SHORT).show();
                            }
                        });*/
       /*                     TradeRequestManager.getInstance().reqQueryMatchTodayFlow("1", "20", new HttpObserver<QueryMatchFlowListEntity>() {
                    @Override
                    public void loadSucceeded(QueryMatchFlowListEntity queryMatchFlowListEntity) {
                        Toast.makeText(mContext,"今日成交单流水请求成功",Toast.LENGTH_SHORT).show();
                    }
                });*/
            /*           TradeRequestManager.getInstance().reqQueryDeferPosition(new HttpObserver<QueryDeferPositionListEntity>() {
                            @Override
                            public void loadSucceeded(QueryDeferPositionListEntity queryMatchFlowListEntity) {
                                Toast.makeText(mContext, "持仓详情请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/
                /*     TradeRequestManager.getInstance().reqQueryHistoryFund(FlowQueryType.TYPE_QUERY_BY_DAYS,
                        "1", "10", "180", "", "",
                        new HttpObserver<QueryHistoryFundListEntity>() {
                            @Override
                            public void loadSucceeded(QueryHistoryFundListEntity queryHistoryFundListEntity) {
                                Toast.makeText(mContext,"历史资金流水请求成功",Toast.LENGTH_SHORT).show();
                            }
                        });*/
    /*                         TradeRequestManager.getInstance().reqQueryRisk(new HttpObserver<RiskEntity>() {
                    @Override
                    public void loadSucceeded(RiskEntity riskEntity) {
                        Toast.makeText(mContext,"风险查询请求成功",Toast.LENGTH_SHORT).show();
                    }
                });*/
         /*           mCompositeDisposable.add(TradeRequestManager.getInstance().reqQuerySettle("20190314", new HttpObserver<CheckDayListEntity>() {
                    @Override
                    public void loadSucceeded(CheckDayListEntity checkDayListEntity) {
                        Toast.makeText(mContext,"日结单请求成功",Toast.LENGTH_SHORT).show();
                    }
                }));*/
     /*                   TradeRequestManager.getInstance().reqFundsTransferIn("100","a123456",
                        new HttpObserver<FundsTransferEntity>() {
                            @Override
                            public void loadSucceeded(FundsTransferEntity queryMatchFlowListEntity) {
                                Toast.makeText(mContext, "资金转入请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/
         /*           TradeRequestManager.getInstance().reqFundsTransferOut("100","a123456",
                        new HttpObserver<FundsTransferEntity>() {
                            @Override
                            public void loadSucceeded(FundsTransferEntity queryMatchFlowListEntity) {
                                Toast.makeText(mContext, "资金转出请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/
          /*      TradeRequestManager.getInstance().reqFundsTransferFlow(FlowQueryType.TYPE_QUERY_BY_DAYS,
                        "1", "10", "", "180", "", "",
                        new HttpObserver<FundsTransferFlowListEntity>() {
                            @Override
                            public void loadSucceeded(FundsTransferFlowListEntity queryHistoryFundListEntity) {
                                Toast.makeText(mContext, "资金出入流水请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });*/

     /*           ClientRequestManager.getInstance().reqModifyLoginPassword("a123456", "a12345", new HttpObserver<BaseRspBean>() {
                    @Override //  如果使用我提供的账号,在更改完密码后,请及时改回来
                    public void loadSucceeded(BaseRspBean baseRspBean) { //  如果使用我提供的账号,在更改完密码后,请及时改回来
                        Toast.makeText(mContext, "修改登陆密码成功", Toast.LENGTH_SHORT).show(); //  如果使用我提供的账号,在更改完密码后,请及时改回来
                    } //  如果使用我提供的账号,在更改完密码后,请及时改回来
                });*/
    /*            TradeRequestManager.getInstance().reqModifyFundsPassword("a123456", "a12345", new HttpObserver<BaseRspBean>() {//  如果使用我提供的账号,在更改完密码后,请及时改回来
                    @Override//  如果使用我提供的账号,在更改完密码后,请及时改回来
                    public void loadSucceeded(BaseRspBean baseRspBean) {//  如果使用我提供的账号,在更改完密码后,请及时改回来
                        Toast.makeText(mContext,"修改资金密码成功",Toast.LENGTH_SHORT).show();//  如果使用我提供的账号,在更改完密码后,请及时改回来
                    }//  如果使用我提供的账号,在更改完密码后,请及时改回来
                });*/
            /*    ClientRequestManager.getInstance().reqAvailableBanks(true,  new HttpObserver<AvailableBankListEntity>() {
                    @Override
                    public void loadSucceeded(AvailableBankListEntity baseRspBean) {
                        Toast.makeText(mContext,"获取银行列表成功",Toast.LENGTH_SHORT).show();
                    }
                });*/
     /*           mCompositeDisposable.add(TradeRequestManager.getInstance().reqCalcTradeRate("Ag(T+D)",
                        "6666666", "3518",
                        new CalcRateObserver<CalcTradeRateBean>() {

                            @Override
                            public void loadSucceeded(CalcTradeRateBean calcTradeRateBean) {
                                Toast.makeText(mContext,"计算成功:"+calcTradeRateBean.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void loadFailed(ServerException e) {
                                Log.d("cheegon", e.getMessage());// 请求错误
                            }

                            @Override
                            public void loadError(Throwable t) {
                                Log.d("cheegon", t.getMessage());//网络异常
                            }
                        }));*/
/*                mCompositeDisposable.add(TradeRequestManager.getInstance().reqCalcTradeRate("Ag(T+D)",
                        "6666666", "3518", "1",
                        new CalcRateObserver<CalcTradeRateBean>() {

                            @Override
                            public void loadSucceeded(CalcTradeRateBean calcTradeRateBean) {
                                Toast.makeText(mContext,"计算成功:"+calcTradeRateBean.toString(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void loadFailed(ServerException e) {
                                Log.d("cheegon", e.getMessage());// 请求错误
                            }

                            @Override
                            public void loadError(Throwable t) {
                                Log.d("cheegon", t.getMessage());//网络异常
                            }
                        }));*/
                mCompositeDisposable.add(ClientRequestManager.getInstance().reqSubAccountInfo(new SubAccountObserver<SubAccountEntity>() {
                    @Override
                    public void loadSucceeded(SubAccountEntity subAccountEntity) {
                        Toast.makeText(mContext, "获取子账户信息成功", Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        });
    }
}
