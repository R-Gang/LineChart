package com.simple.kotlin.easypop

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gang.kotlin.popup.BasePopup
import com.gang.library.common.utils.dip2px
import com.simple.kotlin.R

/**
 * Created by haoruigang on 2017/8/7.
 */
class GiftPopup : BasePopup<GiftPopup?>() {
    private var mRecyclerView: RecyclerView? = null
    protected override fun initAttributes() {
        setContentView(R.layout.layout_gift)
        setHeight(dip2px(200))
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
        setFocusAndOutsideEnable(true)
    }

    protected override fun initViews(view: View?, basePopup: GiftPopup?) {
        mRecyclerView = findViewById(R.id.rv_gift)
        mRecyclerView?.setLayoutManager(GridLayoutManager(mRecyclerView?.getContext(),
            4,
            GridLayoutManager.VERTICAL,
            false))
        val list = createList()
        val adapter = GiftAdapter()
        adapter.setNewData(list)
        mRecyclerView?.setAdapter(adapter)
    }

    private fun createList(): List<String> {
        val list: MutableList<String> = ArrayList(1)
        for (i in 0..14) {
            list.add("")
        }
        return list
    }

    companion object {
        fun create(): GiftPopup {
            return GiftPopup()
        }
    }
}