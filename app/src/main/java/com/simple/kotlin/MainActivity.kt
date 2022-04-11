package com.simple.kotlin

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.simple.kotlin.R
import com.simple.kotlin.databinding.ActivityMainBinding
import com.simple.kotlin.base.Constance
import com.simple.kotlin.base.MyLifecycleObserver1
import com.simple.kotlin.jetpack.modelview.LDViewModel
import com.simple.kotlin.jetpack.modelview.MyViewModel
import com.simple.kotlin.jetpack.modelview.RememberMe
import com.simple.kotlin.jetpack.modelview.UserData
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainBinding: ActivityMainBinding

    private val model by viewModels<MyViewModel>()

    private val model1 by viewModels<LDViewModel>()

    private val model2 by lazy { RememberMe() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 原始方式
        // setContentView(R.layout.activity_main)

        /*
        方式1 视图绑定
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        */

        /*
        方式2 数据绑定
        */
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        mainBinding.btnClick.setOnClickListener(this)
        mainBinding.txtName.text = model.number.toString()


        mainBinding.btnChange.setOnClickListener {
            model1.updateName("LiveData使用:${(0..100).random()}")
            // txtName.text = model1.nameLd.toString()
        }
        mainBinding.btnChange1.setOnClickListener {
            thread {
                model1.updateNameThread("子线程使用LiveData:${(0..100).random()}")
            }
        }
        model1.nameLd.observe(this) {
            mainBinding.txtName1.text = it
        }

        mainBinding.mainData = this
        mainBinding.rememberMy = model2

        // Lifecycle
        // lifecycle.addObserver(MyLifecycleObserver())
        lifecycle.addObserver(MyLifecycleObserver1())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnClick -> {
                model.number = ++model.number
                mainBinding.txtName.text = model.number.toString()
            }
        }
    }

    fun onBindableCk() {
        if (model2.getRememberMy()) {
            model2.setRememberMy(false)
        } else {
            model2.setRememberMy(true)
        }

    }

    fun onToARouter() {
        ARouter.getInstance()
            .build(Constance.ACTIVITY_URL_AROUTER)
            .withString("name", "刚刚好")
            .withInt("age", 27)
            .withSerializable("user", UserData("刚刚好", 27))
            .navigation(this)
    }

    fun onToPopup() {
        ARouter.getInstance()
            .build(Constance.ACTIVITY_URL_POPUP)
            .navigation(this)
    }

}