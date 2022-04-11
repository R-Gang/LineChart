package com.simple.kotlin.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.simple.kotlin.R
import com.simple.kotlin.databinding.FragABinding
import com.simple.kotlin.jetpack.modelview.ShareViewModel

/**
 *
 * @ProjectName:    JetPack_Simple
 * @Package:        com.simple
 * @ClassName:      FragA
 * @Description:     java类作用描述
 * @Author:         haoruigang
 * @CreateDate:     2022/2/28 14:56
 * @UpdateUser:     更新者：
 * @UpdateDate:     2022/2/28 14:56
 * @UpdateRemark:   更新说明：
 * @Version:
 */
class FragA : Fragment() {

    private lateinit var fragABinding: FragABinding

    private val model by activityViewModels<ShareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        fragABinding = FragABinding.inflate(inflater, container, false)
        return fragABinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragABinding.textView.text = model.number.value.toString()

        fragABinding.btnClick = this
    }

    fun OnFragAClick(view: View?) {
        when (view?.id) {
            R.id.fragAbtnClick -> {
                val n = model.number.value?.toInt()?.plus(1) // ++
                if (n != null) {
                    //model.number.value = n
                    model.add(n)
                }
                fragABinding.textView.text = model.number.value.toString()
            }
        }

    }

}