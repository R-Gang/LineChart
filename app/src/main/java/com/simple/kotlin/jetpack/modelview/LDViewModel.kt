package com.simple.kotlin.jetpack.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple
 * @ClassName:      LDViewModel
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/2/28 14:36
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/2/28 14:36
 * @UpdateRemark:   更新说明：
 * @Version:        3
 */
class LDViewModel : ViewModel() {
    val nameLd: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun updateName(name:String){
        nameLd.value = name
    }

    fun updateNameThread(name: String){
        nameLd.postValue(name)
    }

}