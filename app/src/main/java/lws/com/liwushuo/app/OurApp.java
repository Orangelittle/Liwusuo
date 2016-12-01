package lws.com.liwushuo.app;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by zhangziyang on 2016/10/12.
 */

public class OurApp extends Application{
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Fresco.initialize(context);
    }
}
