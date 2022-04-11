package com.simple.kotlin.jetpack.modelview

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.simple.kotlin.BR

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple.kotlin.jetpack.modelview
 * @ClassName:      RememberMe
 * @Description:     双向绑定
 * @Author:         haoruigang
 * @CreateDate:     2022/3/2 16:55
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/3/2 16:55
 * @UpdateRemark:   更新说明：
 * @Version:
 */
class RememberMe : BaseObservable() {
    private var flag:Boolean = false

    @Bindable
    fun getRememberMy():Boolean{
        return flag
    }

    fun setRememberMy(value:Boolean){
        // Avoids infinite loops (避免无限循环).
        Log.d("jetPack","isChecked=$value")
        if(value!=flag){
            flag = value
        }
        // Notify observers of a new value(通知观察者一个新值).
        notifyPropertyChanged(BR.rememberMy)
    }
}