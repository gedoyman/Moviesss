package com.littleaozora.hendra.moviessss;

import android.app.Application;

/**
 * Created by Hendra on 7/28/2017.
 */

public class Moviesss extends Application {
    private boolean initLoadData = false;

    public boolean isInitLoadData() {
        return initLoadData;
    }

    public void setInitLoadData(boolean initLoadData) {
        this.initLoadData = initLoadData;
    }
}
