package com.simple.kotlin.base

import android.util.Log
import androidx.lifecycle.*

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple
 * @ClassName:      MyLifecycleObserver
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/3/3 11:06
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/3/3 11:06
 * @UpdateRemark:   更新说明：
 * @Version:
 */
class MyLifecycleObserver:LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate(){
        Log.e("Lifecycle","onCreate")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart(){
        Log.e("Lifecycle","onStart")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume(){
        Log.e("Lifecycle","onResume")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause(){
        Log.e("Lifecycle","onPause")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop(){
        Log.e("Lifecycle","onStop")
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Log.e("Lifecycle","onDestroy")
    }

}

class MyLifecycleObserver1:DefaultLifecycleObserver{
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.e("Lifecycle","onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.e("Lifecycle","onStart:"+owner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))

    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.e("Lifecycle","onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.e("Lifecycle","onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.e("Lifecycle","onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.e("Lifecycle","onDestroy")
    }
}