# LineChart

使用JetPack组件开发-Chart图表控件-Popup弹窗

![line_chart.gif](https://github.com/R-Gang/Chart-Popup/blob/master/images/line_chart.gif)
![line_chart.png](https://github.com/R-Gang/Chart-Popup/blob/master/images/line_chart.png) 

## Usage

### 调用扩展LineChart
```
   lineChart.apply {
       // todo 测试数据 Generate some random values.
       val amountList = arrayListOf(191, 177, 190, 33, 144, 45, 38)
       val dateList = arrayListOf("12.31", "12.30", "12.29", "12.28", "12.27", "12.26", "12.25")
       updateTime(amountList, dateList)
   }
```

## 使用

### 创建 EasyPopup 对象

可以调用 setXxx() 方法进行属性设置，最后调用 createPopup() 方法实现对PopupWindow的初始化。
```
    /**
     * 发布弹框
     */
    private val mReleasePop by lazy {
        EasyPopup.create().setContentView(releaseBinding?.root)
            ?.setAnimationStyle(R.style.RightTopPopAnim)
            ?.setOnViewListener(object : EasyPopup.OnViewListener {
                override fun initViews(view: View?, popup: EasyPopup?) {
                    releaseBinding?.apply {
                        vArrow.background =
                            TriangleDrawable(ARROWDIRECTION.TOP,
                                Color.parseColor("#FFFFFF"))
                        rlArticle.setOnClickListener { ToastUtils.showShort("文章"); }
                        rlVideo.setOnClickListener { ToastUtils.showShort("视频"); }
                    }
                }
            })
            ?.setOnDismissListener { // todo 监听popup消失 }
            ?.setFocusAndOutsideEnable(true)
            //.setBackgroundDimEnable(true)
            //.setDimValue(0.5f)
            //.setDimColor(Color.RED)
            //.setDimView(mTitleBar)
            ?.apply()
    }
```

## Reference
[Android 实现一个自定义曲线图](https://blog.csdn.net/qq_16131393/article/details/89671919)

[Kotlin集成Arouter](https://www.jianshu.com/p/3991195031cd)
