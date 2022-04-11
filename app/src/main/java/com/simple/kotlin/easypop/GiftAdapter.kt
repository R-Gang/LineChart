package com.simple.kotlin.easypop

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.simple.kotlin.R

/**
 * Created by haoruigang on 2017/8/7.
 */
class GiftAdapter : BaseQuickAdapter<String?, BaseViewHolder?>(R.layout.layout_item_gift, null) {
    override fun convert(baseViewHolder: BaseViewHolder?, s: String?) {}
}