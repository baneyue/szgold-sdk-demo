package cn.com.glodv.demo;

import android.app.Application;

import cn.com.goldv.netlib.app.GoldV;
import cn.com.goldv.netlib.type.EnvType;

/**
 * Desc:
 * Created by cheegon on 4/4/2019.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GoldV.getInstance().init(this, "test01", "XQL6OthRWikQQHQm2msH");
        GoldV.getInstance().setDebugMode(true);
        GoldV.getInstance().setEnv(EnvType.TEST_TYPE);
    }
}
