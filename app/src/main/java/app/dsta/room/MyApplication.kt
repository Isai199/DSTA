package app.dsta.room

import android.app.Application
import android.os.SystemClock

class MyApplication: Application() {

    @Override
    override fun onCreate() {
        DataBaseManager.instance.initializeDb(applicationContext)
        SystemClock.sleep(4000)
        super.onCreate()
    }
}