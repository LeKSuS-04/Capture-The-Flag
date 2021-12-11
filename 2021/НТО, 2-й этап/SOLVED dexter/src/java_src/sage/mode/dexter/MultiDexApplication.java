package sage.mode.dexter;

import android.app.Application;
import android.content.Context;

public class MultiDexApplication extends Application {
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
