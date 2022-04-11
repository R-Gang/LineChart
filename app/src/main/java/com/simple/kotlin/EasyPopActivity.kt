package com.simple.kotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.gang.kotlin.popup.*
import com.gang.library.common.utils.*
import com.gang.library.ui.activity.BaseActivity
import com.simple.kotlin.base.Constance.ACTIVITY_URL_POPUP
import com.simple.kotlin.databinding.PopupReleaseDownBinding
import com.simple.kotlin.easypop.CmmtPopup
import com.simple.kotlin.easypop.ComplexPopup
import com.simple.kotlin.easypop.GiftPopup
import com.simple.kotlin.views.TitleBar

@Route(path = ACTIVITY_URL_POPUP)
class EasyPopActivity : BaseActivity() {
    private var mTitleBar: TitleBar? = null
    private var mCircleBtn: Button? = null
    private var mAboveBtn: Button? = null
    private var mRightBtn: Button? = null
    private var mBgDimBtn: Button? = null
    private var mAnyBgDimBtn: Button? = null
    private var mGiftBtn: Button? = null
    private var mCmmtBtn: Button? = null
    private var mComplexBtn: Button? = null
    private var mEverywhereTv: AppCompatTextView? = null
    private var rlNested: LinearLayout? = null
    private var dimView: RelativeLayout? = null
    private var mComplexBgDimView: LinearLayout? = null
    private var mCirclePop: EasyPopup? = null
    private var mAbovePop: EasyPopup? = null
    private var mBgDimPop: EasyPopup? = null
    private var mAnyBgDimPop: EasyPopup? = null
    private var mGiftPopup: GiftPopup? = null
    private var mCmmtPopup: CmmtPopup? = null
    private var mComplexPopup: ComplexPopup? = null
    private var mEverywherePopup: EverywherePopup? = null
    private var mLastX = 0f
    private var mLastY = 0f

    override val layoutId: Int = R.layout.activity_easy_pop

    @SuppressLint("ClickableViewAccessibility")
    override fun initView(savedInstanceState: Bundle?) {
        mTitleBar = findViewById(R.id.tb_easy)
        mTitleBar?.setTile("Easy Pop")

        releaseBinding = PopupReleaseDownBinding.inflate(layoutInflater)

        mCircleBtn = findViewById(R.id.btn_circle_comment)
        mAboveBtn = findViewById(R.id.btn_above)
        mRightBtn = findViewById(R.id.btn_right)
        mBgDimBtn = findViewById(R.id.btn_bg_dim)
        mAnyBgDimBtn = findViewById(R.id.btn_bg_dim_any)
        mGiftBtn = findViewById(R.id.btn_gift)
        mCmmtBtn = findViewById(R.id.btn_pop_cmmt)
        mComplexBtn = findViewById(R.id.btn_complex)
        mComplexBgDimView = findViewById(R.id.ll_complex_bg_dim)
        mEverywhereTv = findViewById(R.id.tv_pop_everywhere)
        rlNested = findViewById(R.id.rlNested)
        dimView = findViewById(R.id.dimView)
        initCirclePop()
        initAbovePop()
        initBgDimPop()
        initAnyBgDimPop()
        initGiftPop()
        initCmmtPop()
        initComplexPop()
        mEverywherePopup = EverywherePopup.create(this)
            .setContentView(R.layout.layout_everywhere_pop)
            ?.setAnimationStyle(com.gang.kotlin.R.style.LeftTopPopAnim)
            ?.apply()
        mEverywhereTv?.setOnTouchListener { v, event ->
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                mLastX = event.getRawX()
                mLastY = event.getRawY()
                LogUtil.i(this@EasyPopActivity, "onTouch x=$mLastX,y=$mLastY")
            }
            false
        }
        mEverywhereTv?.setOnLongClickListener { v ->
            val point = PointF(mLastX, mLastY)
            mEverywherePopup?.showEverywhere(v, mLastX.toInt(), mLastY.toInt(), point, IntArray(2))
            true
        }
    }

    override fun initData() {
        mTitleBar?.setOnTitleListener(object : TitleBar.OnTitleListener {
            override fun onLeftClick(view: View?) {
                finish()
            }

            override fun onRightClick(view: View?) {
                showQQPop(view)
            }

            override fun onTitleClick(view: View?) {
                showWeiboPop(view)
            }

        })

    }

    override fun onClick() {
        mCircleBtn?.setOnClickListener { onClick(it) }
        mAboveBtn?.setOnClickListener { onClick(it) }
        mRightBtn?.setOnClickListener { onClick(it) }
        mBgDimBtn?.setOnClickListener { onClick(it) }
        mAnyBgDimBtn?.setOnClickListener { onClick(it) }
        mGiftBtn?.setOnClickListener { onClick(it) }
        mCmmtBtn?.setOnClickListener { onClick(it) }
        mComplexBtn?.setOnClickListener { onClick(it) }
    }

    /**
     * 发布弹框
     */
    private var releaseBinding: PopupReleaseDownBinding? = null
    private val mReleasePop by lazy {
        EasyPopup.create().setContentView(releaseBinding?.root)
            ?.setAnimationStyle(R.style.RightTopPopAnim)
            ?.setOnViewListener(object : EasyPopup.OnViewListener {
                override fun initViews(view: View?, popup: EasyPopup?) {
                    releaseBinding?.apply {
                        vArrow.background =
                            TriangleDrawable(ARROWDIRECTION.TOP,
                                Color.parseColor("#FFFFFF"))
                        rlArticle.setOnClickListener { onClick(it) }
                        rlVideo.setOnClickListener { onClick(it) }
                    }
                }
            })
            ?.setOnDismissListener { dimView?.gone() }
            ?.setFocusAndOutsideEnable(true)
            //                .setBackgroundDimEnable(true)
            //                .setDimValue(0.5f)
            //                .setDimColor(Color.RED)
            //                .setDimView(mTitleBar)
            ?.apply()
    }

    private fun showQQPop(view: View?) {
        val offsetX: Int = dip2px(20) - view?.width as Int / 2
        val offsetY: Int = (mTitleBar?.height as Int - view.height) / 2
        dimView?.layoutParams?.height = rlNested?.measuredHeight
        dimView?.show()
        mReleasePop?.showAtAnchorView(view, YGravity.BELOW, XGravity.ALIGN_RIGHT, offsetX, offsetY)
    }

    private val mWeiboPop by lazy {
        EasyPopup.create()
            .setContentView(this, R.layout.layout_center_pop)
            ?.setAnimationStyle(R.style.TopPopAnim)
            ?.setOnViewListener(object : EasyPopup.OnViewListener {
                override fun initViews(view: View?, popup: EasyPopup?) {
                    val arrowView = view?.findViewById<View>(R.id.v_arrow_weibo)
                    arrowView?.background = TriangleDrawable(ARROWDIRECTION.TOP, Color.WHITE)
                }
            })
            ?.setFocusAndOutsideEnable(true)
            ?.apply()
    }

    private fun showWeiboPop(view: View?) {
        val offsetY: Int = (mTitleBar?.height as Int - view?.height as Int) / 2
        mWeiboPop?.showAtAnchorView(view, YGravity.BELOW, XGravity.CENTER, 0, offsetY)
    }

    private fun initCirclePop() {
        mCirclePop = EasyPopup.create()
            .setContentView(this, R.layout.layout_circle_comment)
            ?.setAnimationStyle(R.style.RightPopAnim)
            ?.setFocusAndOutsideEnable(true)
            ?.setOnViewListener(object : EasyPopup.OnViewListener {
                override fun initViews(view: View?, popup: EasyPopup?) {
                    view?.findViewById<View>(R.id.tv_zan)?.setOnClickListener {
                        showToast("赞")
                        popup?.dismiss()
                    }
                    view?.findViewById<View>(R.id.tv_comment)?.setOnClickListener {
                        showToast("评论")
                        popup?.dismiss()
                    }
                }
            })
            ?.setOnDismissListener {
                Log.e(TAG, "onDismiss: mCirclePop")
            }
            ?.apply()
    }

    private fun showCirclePop(view: View) {
        mCirclePop?.showAtAnchorView(view, YGravity.CENTER, XGravity.LEFT, 0, 0)
    }

    private fun initAbovePop() {
        mAbovePop = EasyPopup.create()
            .setContentView(this, R.layout.layout_any)
            ?.setFocusAndOutsideEnable(true)
            ?.setOnDismissListener {
                Log.e(TAG, "onDismiss: mAbovePop")
            }
            ?.apply()
    }

    private fun showAbovePop(view: View) {
        mAbovePop?.showAtAnchorView(view, YGravity.ABOVE, XGravity.CENTER)
    }

    private fun showRightPop(view: View) {
        mAbovePop?.showAtAnchorView(view, YGravity.CENTER, XGravity.RIGHT)
    }

    private fun initBgDimPop() {
        mBgDimPop = EasyPopup.create()
            .setContentView(this, R.layout.layout_any)
            ?.setFocusAndOutsideEnable(true)
            ?.setBackgroundDimEnable(true)
            ?.setDimValue(0.4f)
            ?.apply()
    }

    private fun showBgDimPop(view: View) {
        mBgDimPop?.showAtAnchorView(view, YGravity.ALIGN_TOP, XGravity.ALIGN_LEFT)
    }

    private fun initAnyBgDimPop() {
        mAnyBgDimPop = EasyPopup.create()
            .setContentView(this, R.layout.layout_any)
            ?.setFocusAndOutsideEnable(true)
            ?.setBackgroundDimEnable(true)
            ?.setDimValue(0.4f)
            ?.setDimView(mTitleBar)
            ?.setDimColor(Color.YELLOW)
            ?.apply()
    }

    private fun showAnyBgDimPop(view: View) {
        mAnyBgDimPop?.showAtAnchorView(view, YGravity.ALIGN_BOTTOM, XGravity.ALIGN_RIGHT)
    }

    private fun initGiftPop() {
        mGiftPopup = GiftPopup.create()
            .setContext(this)
            ?.apply()
    }

    private fun showGiftPop(view: View) {
        mGiftPopup?.showAtLocation(view, Gravity.BOTTOM, 0, 0)
    }

    private fun initComplexPop() {
        mComplexPopup = ComplexPopup.create(this)
            .setDimView(mComplexBgDimView)
            ?.apply()
    }

    private fun showComplexPop(view: View) {
//        mComplexPopup.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
        mComplexPopup?.showAtLocation(view, Gravity.BOTTOM, 0, 0)
    }

    private fun initCmmtPop() {
        mCmmtPopup = CmmtPopup.create(this)
            .setOnCancelClickListener {
                mCmmtPopup?.apply {
                    if (isShowing) {
                        //无法隐藏输入法。只有toggle方法起作用...
                        hideKeyboard(this@EasyPopActivity)
                        dismiss()
                    }
                }
            }
            .setOnOkClickListener {
                mCmmtPopup?.apply {
                    if (isShowing) {
                        //无法隐藏输入法。只有toggle方法起作用...
                        hideKeyboard(this@EasyPopActivity)
                        dismiss()
                    }
                }
            }
            .apply()
    }

    private fun showCmmtPop(view: View) {
        mCmmtPopup?.showSoftInput()
            ?.showAtLocation(view, Gravity.BOTTOM, 0, 0)
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_circle_comment -> showCirclePop(v)
            R.id.btn_above -> showAbovePop(v)
            R.id.btn_right -> showRightPop(v)
            R.id.btn_bg_dim -> showBgDimPop(v)
            R.id.btn_bg_dim_any -> showAnyBgDimPop(v)
            R.id.btn_gift -> showGiftPop(v)
            R.id.btn_pop_cmmt -> showCmmtPop(v)
            R.id.btn_complex -> showComplexPop(v)
        }
    }

    companion object {
        private const val TAG = "EasyPopActivity"
    }
}