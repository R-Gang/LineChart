package com.simple.kotlin.jetpack.modelview

import java.io.Serializable

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple.kotlin.jetpack.modelview
 * @ClassName:      UserData
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/3/1 15:08
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/3/1 15:08
 * @UpdateRemark:   更新说明：
 * @Version:
 */
data class UserData(var name: String, var age: Int) : Serializable
