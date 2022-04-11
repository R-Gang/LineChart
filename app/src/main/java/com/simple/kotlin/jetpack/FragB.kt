package com.simple.kotlin.jetpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.simple.kotlin.R
import com.simple.kotlin.databinding.FragBBinding
import com.simple.kotlin.jetpack.modelview.ShareViewModel
import com.simple.kotlin.jetpack.modelview.UserData

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
class FragB : Fragment() {

    private var fragBBinding: FragBBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = fragBBinding

    private val model by activityViewModels<ShareViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 方式一
        // fragBBinding = FragBBinding.inflate(inflater,container,false)

        // 方式二
        fragBBinding = DataBindingUtil.inflate(inflater, R.layout.frag_b, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.number.observe(viewLifecycleOwner) {
            binding?.textView?.text = it.toString()
        }

        fragBBinding?.nickname = "刚刚好"
        fragBBinding?.age = 28

        fragBBinding?.userData = UserData("郝瀚", 28)

        fragBBinding?.list = listOf(26, 38, 68)
        fragBBinding?.map = mapOf(0 to "Jack", 1 to "May")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragBBinding = null
    }

}