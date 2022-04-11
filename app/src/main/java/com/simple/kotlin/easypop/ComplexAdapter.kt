package com.simple.kotlin.easypop

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.simple.kotlin.R


class ComplexAdapter : BaseQuickAdapter<String?, BaseViewHolder?>(R.layout.item_complex, null) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_complex_item, item)
        helper?.addOnClickListener(R.id.btn_complex_delete)
    }
}