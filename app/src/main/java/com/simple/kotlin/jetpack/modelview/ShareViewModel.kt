package com.simple.kotlin.jetpack.modelview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple
 * @ClassName:      ShareViewModel
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/2/25 17:13
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/2/25 17:13
 * @UpdateRemark:   更新说明：
 * @Version:        2
 */
class ShareViewModel : ViewModel() {

    var number = MutableLiveData<Int>(0)

    fun add(n:Int){
        number.value = n
    }
}