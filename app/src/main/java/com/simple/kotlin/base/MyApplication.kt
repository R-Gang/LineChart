package com.simple.kotlin.base

import com.alibaba.android.arouter.launcher.ARouter
import com.gang.library.BaseApp
import com.gang.library.common.user.Config
import com.simple.kotlin.BuildConfig

/**
 * @ProjectName: JetPack_Simple
 * @Package: com.simple.kotlin
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: haoruigang
 * @CreateDate: 2022/3/7 16:30
 */
class MyApplication : BaseApp() {
    override fun onCreate() {

        Config.statusBarEnabled = false

        super.onCreate()

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }

        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}