package com.simple.kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.simple.kotlin.R
import com.simple.kotlin.databinding.ActivityArouterBinding
import com.simple.kotlin.base.Constance.ACTIVITY_URL_AROUTER
import com.simple.kotlin.jetpack.modelview.UserData

@Route(path = ACTIVITY_URL_AROUTER) // Route path 注解
class ARouterActivity : AppCompatActivity() {

    private val TAG: String = "ARouter"

    private lateinit var aRouterBinding: ActivityArouterBinding

    @Autowired
    @JvmField
    var name: String = "";

    @Autowired
    @JvmField
    var age: Int = 0

    @Autowired(name = "user")
    @JvmField
    var userData: UserData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Arouter 注入
        ARouter.getInstance().inject(this)

        aRouterBinding = DataBindingUtil.setContentView(this, R.layout.activity_arouter)


        val str = "姓名：$name,年龄：$age,user:$userData"
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        Log.i(TAG, str)


        aRouterBinding.apply {

            lineChart.apply {
                // todo 测试数据 Generate some random values.
                val amountList = arrayListOf(191, 177, 190, 33, 144, 45, 38)
                val dateList =
                    arrayListOf("12.31", "12.30", "12.29", "12.28", "12.27", "12.26", "12.25")

                updateTime(amountList, dateList)
            }

        }
    }
}