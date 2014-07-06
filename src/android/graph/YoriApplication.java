package android.graph;

import android.app.Application;
import android.graph.YoriApplication;


public class YoriApplication extends Application {

    private ServerUtility server;
    public static YoriApplication Instance = null;

    public void onCreate() {
        server = ServerUtility.getInstance();
        server.initalize(this);
        Instance = this;
    }
}
