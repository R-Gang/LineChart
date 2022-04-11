package com.simple.kotlin.easypop

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.gang.kotlin.popup.BasePopup
import com.gang.library.common.utils.dip2px
import com.gang.library.common.utils.hideSoftKeyboard
import com.gang.library.common.utils.showKeyBoard
import com.simple.kotlin.R

/**
 * Created by haoruigang on 2018/3/12.
 *
 * PopupWindow 中存在 EditText 隐藏键盘方法不起作用，只有 toggle 键盘方法才起作用
 * 注：建议由 EditText 需求的弹窗使用 DialogFragment
 */
class CmmtPopup(context: Context?) : BasePopup<CmmtPopup?>() {
    private var mCancelListener: View.OnClickListener? = null
    private var mOkListener: View.OnClickListener? = null
    var mCancelTv: AppCompatTextView? = null
    var mOkTv: AppCompatTextView? = null
    var mEditText: AppCompatEditText? = null
    override fun initAttributes() {
        setContentView(R.layout.layout_cmmt,
            ViewGroup.LayoutParams.MATCH_PARENT,
            dip2px(150))
        setFocusAndOutsideEnable(true)
            ?.setBackgroundDimEnable(true)
            ?.setAnimationStyle(R.style.BottomPopAnim)
            ?.setDimValue(0.5f)
            ?.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED)
            ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun initViews(view: View?, basePopup: CmmtPopup?) {
        mCancelTv = findViewById(R.id.tv_cancel)
        mOkTv = findViewById(R.id.tv_ok)
        mEditText = findViewById(R.id.et_cmmt)
        mCancelTv?.setOnClickListener(mCancelListener)
        mOkTv?.setOnClickListener(mOkListener)
    }

    fun setOnCancelClickListener(listener: View.OnClickListener?): CmmtPopup {
        mCancelListener = listener
        return this
    }

    fun setOnOkClickListener(listener: View.OnClickListener?): CmmtPopup {
        mOkListener = listener
        return this
    }

    fun showSoftInput(): CmmtPopup {
        mEditText?.apply {
            if (mEditText != null) {
                mEditText?.post { showKeyBoard(this) }
            }
        }
        return this
    }

    fun hideSoftInput(): CmmtPopup {
        mEditText?.apply {
            if (mEditText != null) {
                mEditText?.post { hideSoftKeyboard(this) }
            }
        }
        return this
    }

    companion object {
        fun create(context: Context?): CmmtPopup {
            return CmmtPopup(context)
        }
    }

    init {
        setContext(context)
    }
}