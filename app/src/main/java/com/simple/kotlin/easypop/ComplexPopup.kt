package com.simple.kotlin.easypop

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gang.kotlin.popup.BasePopup
import com.simple.kotlin.R

/**
 * Created by haoruigang on 2017/8/4.
 */
class ComplexPopup protected constructor(private val mContext: Context?) :
    BasePopup<ComplexPopup?>() {
    private var mOkBtn: Button? = null
    private var mCancelBtn: Button? = null
    private var mRecyclerView: RecyclerView? = null
    private var mComplexAdapter: ComplexAdapter? = null
    override fun initAttributes() {
        setContentView(R.layout.layout_complex,
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        setFocusAndOutsideEnable(false)
            ?.setBackgroundDimEnable(true)
            ?.setDimValue(0.5f)
    }

    override fun initViews(view: View?, basePopup: ComplexPopup?) {
        mOkBtn = findViewById(R.id.btn_ok)
        mCancelBtn = findViewById(R.id.btn_cancel)
        mRecyclerView = findViewById(R.id.rv_complex)
        mComplexAdapter = ComplexAdapter()
        mRecyclerView?.layoutManager = LinearLayoutManager(mContext,
            LinearLayoutManager.VERTICAL,
            false)
        mRecyclerView?.adapter = mComplexAdapter
        val list: MutableList<String> = ArrayList(1)
        for (i in 0..4) {
            list.add("烤肉盖饭")
        }
        mComplexAdapter?.setNewData(list as List<String?>?)
        mOkBtn?.setOnClickListener { dismiss() }
        mCancelBtn?.setOnClickListener { dismiss() }
        mComplexAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.btn_complex_delete -> mComplexAdapter?.remove(position)
                else -> {}
            }
        }
    }

    fun setAbc() {}

    companion object {
        private const val TAG = "ComplexPopup"
        fun create(context: Context?): ComplexPopup {
            return ComplexPopup(context)
        }
    }

    init {
        setContext(mContext)
    }
}