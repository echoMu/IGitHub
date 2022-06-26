package com.echomu.github.ui.view

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import com.echomu.github.R
import com.echomu.github.utils.dip2px

/**
 * created by hjb.
 */
class AngleCircleImageView : AppCompatImageView {
    private var isCircle // 是否显示为圆形，如果为圆形则设置的corner无效
            = false
    private var isCoverSrc // border、inner_border是否覆盖图片
            = false
    private var borderWidth // 边框宽度
            = 0
    private var borderColor = Color.WHITE // 边框颜色
    private var innerBorderWidth // 内层边框宽度
            = 0
    private var innerBorderColor = Color.WHITE // 内层边框充色
    private var cornerRadius // 统一设置圆角半径，优先级高于单独设置每个角的半径
            = 0
    private var cornerTopLeftRadius // 左上角圆角半径
            = 0
    private var cornerTopRightRadius // 右上角圆角半径
            = 0
    private var cornerBottomLeftRadius // 左下角圆角半径
            = 0
    private var cornerBottomRightRadius // 右下角圆角半径
            = 0
    private var maskColor // 遮罩颜色
            = 0
    private lateinit var xfermode: Xfermode
    private var mWidth = 0
    private var mHeigh = 0
    private var radius = 0f
    private lateinit var borderRadii: FloatArray
    private lateinit var srcRadii: FloatArray
    private lateinit var srcRectF // 图片占的矩形区域
            : RectF
    private lateinit var borderRectF // 边框的矩形区域
            : RectF
    private val path = Path()
    private val paint = Paint()

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(
            context: Context,
            attrs: AttributeSet
    ) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(
            context: Context,
            attrs: AttributeSet,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeigh = h
        initBorderRectF()
        initSrcRectF()
    }

    override fun onDraw(canvas: Canvas) {
        // 使用图形混合模式来显示指定区域的图片
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayer(srcRectF, null)
        } else {
            canvas.saveLayer(srcRectF, null, Canvas.ALL_SAVE_FLAG)
        }
        if (!isCoverSrc) {
            val sx = 1.0f * (mWidth - 2 * borderWidth - 2 * innerBorderWidth) / mWidth
            val sy = 1.0f * (mHeigh - 2 * borderWidth - 2 * innerBorderWidth) / mHeigh
            // 缩小画布，使图片内容不被border、padding覆盖
            canvas.scale(sx, sy, mWidth / 2.0f, mHeigh / 2.0f)
        }
        super.onDraw(canvas)
        paint.reset()
        path.reset()
        if (isCircle) {
            path.addCircle(mWidth / 2.0f, mHeigh / 2.0f, radius, Path.Direction.CCW)
        } else {
            path.addRoundRect(srcRectF, srcRadii, Path.Direction.CCW)
        }
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.xfermode = xfermode
        canvas.drawPath(path, paint)
        paint.xfermode = null

        // 绘制遮罩
        if (maskColor != 0) {
            paint.color = maskColor
            canvas.drawPath(path, paint)
        }
        // 恢复画布
        canvas.restore()
        // 绘制边框
        drawBorders(canvas)
    }

    private fun drawBorders(canvas: Canvas) {
        if (isCircle) {
            if (borderWidth > 0) {
                drawCircleBorder(canvas, borderWidth, borderColor, radius - borderWidth / 2.0f)
            }
            if (innerBorderWidth > 0) {
                drawCircleBorder(canvas, innerBorderWidth, innerBorderColor, radius - borderWidth - innerBorderWidth / 2.0f)
            }
        } else {
            if (borderWidth > 0) {
                drawRectFBorder(canvas, borderWidth, borderColor, borderRectF, borderRadii)
            }
        }
    }

    private fun drawCircleBorder(canvas: Canvas, borderWidth: Int, borderColor: Int, radius: Float) {
        initBorderPaint(borderWidth, borderColor)
        path.addCircle(mWidth / 2.0f, mHeigh / 2.0f, radius, Path.Direction.CCW)
        canvas.drawPath(path, paint)
    }

    private fun drawRectFBorder(canvas: Canvas, borderWidth: Int, borderColor: Int, rectF: RectF, radii: FloatArray) {
        initBorderPaint(borderWidth, borderColor)
        path.addRoundRect(rectF, radii, Path.Direction.CCW)
        canvas.drawPath(path, paint)
    }

    private fun initBorderPaint(borderWidth: Int, borderColor: Int) {
        path.reset()
        paint.strokeWidth = borderWidth.toFloat()
        paint.color = borderColor
        paint.style = Paint.Style.STROKE
    }

    /**
     * 计算外边框的RectF
     */
    private fun initBorderRectF() {
        if (!isCircle) {
            borderRectF[borderWidth / 2.0f, borderWidth / 2.0f, mWidth - borderWidth / 2.0f] = mHeigh - borderWidth / 2.0f
        }
    }

    /**
     * 计算图片原始区域的RectF
     */
    private fun initSrcRectF() {
        if (isCircle) {
            radius = Math.min(mWidth, mHeigh) / 2.0f
            srcRectF[mWidth / 2.0f - radius, mHeigh / 2.0f - radius, mWidth / 2.0f + radius] = mHeigh / 2.0f + radius
        } else {
            srcRectF[0f, 0f, mWidth.toFloat()] = mHeigh.toFloat()
            if (isCoverSrc) {
                srcRectF = borderRectF
            }
        }
    }

    /**
     * 计算RectF的圆角半径
     */
    private fun calculateRadii() {
        if (isCircle) {
            return
        }
        if (cornerRadius > 0) {
            for (i in borderRadii.indices) {
                borderRadii[i] = cornerRadius.toFloat()
                srcRadii[i] = cornerRadius - borderWidth / 2.0f
            }
        } else {
            borderRadii[1] = cornerTopLeftRadius.toFloat()
            borderRadii[0] = borderRadii[1]
            borderRadii[3] = cornerTopRightRadius.toFloat()
            borderRadii[2] = borderRadii[3]
            borderRadii[5] = cornerBottomRightRadius.toFloat()
            borderRadii[4] = borderRadii[5]
            borderRadii[7] = cornerBottomLeftRadius.toFloat()
            borderRadii[6] = borderRadii[7]
            srcRadii[1] = cornerTopLeftRadius - borderWidth / 2.0f
            srcRadii[0] = srcRadii[1]
            srcRadii[3] = cornerTopRightRadius - borderWidth / 2.0f
            srcRadii[2] = srcRadii[3]
            srcRadii[5] = cornerBottomRightRadius - borderWidth / 2.0f
            srcRadii[4] = srcRadii[5]
            srcRadii[7] = cornerBottomLeftRadius - borderWidth / 2.0f
            srcRadii[6] = srcRadii[7]
        }
    }

    private fun calculateRadiiAndRectF(reset: Boolean) {
        if (reset) {
            cornerRadius = 0
        }
        calculateRadii()
        initBorderRectF()
        invalidate()
    }

    /**
     * 目前圆角矩形情况下不支持inner_border，需要将其置0
     */
    private fun clearInnerBorderWidth() {
        if (!isCircle) {
            innerBorderWidth = 0
        }
    }

    fun isCoverSrc(isCoverSrc: Boolean) {
        this.isCoverSrc = isCoverSrc
        initSrcRectF()
        invalidate()
    }

    fun isCircle(isCircle: Boolean) {
        this.isCircle = isCircle
        clearInnerBorderWidth()
        initSrcRectF()
        invalidate()
    }

    fun setBorderWidth(borderWidth: Int) {
        this.borderWidth = dip2px(context, borderWidth.toFloat())
        calculateRadiiAndRectF(false)
    }

    fun setBorderColor(@ColorInt borderColor: Int) {
        this.borderColor = borderColor
        invalidate()
    }

    fun setInnerBorderWidth(innerBorderWidth: Int) {
        this.innerBorderWidth = dip2px(context, innerBorderWidth.toFloat())
        clearInnerBorderWidth()
        invalidate()
    }

    fun setInnerBorderColor(@ColorInt innerBorderColor: Int) {
        this.innerBorderColor = innerBorderColor
        invalidate()
    }

    fun setCornerRadius(cornerRadius: Int) {
        this.cornerRadius = dip2px(context, cornerRadius.toFloat())
        calculateRadiiAndRectF(false)
    }

    fun setCornerTopLeftRadius(cornerTopLeftRadius: Int) {
        this.cornerTopLeftRadius = dip2px(context, cornerTopLeftRadius.toFloat())
        calculateRadiiAndRectF(true)
    }

    fun setCornerTopRightRadius(cornerTopRightRadius: Int) {
        this.cornerTopRightRadius = dip2px(context, cornerTopRightRadius.toFloat())
        calculateRadiiAndRectF(true)
    }

    fun setCornerBottomLeftRadius(cornerBottomLeftRadius: Int) {
        this.cornerBottomLeftRadius = dip2px(context, cornerBottomLeftRadius.toFloat())
        calculateRadiiAndRectF(true)
    }

    fun setCornerBottomRightRadius(cornerBottomRightRadius: Int) {
        this.cornerBottomRightRadius = dip2px(context, cornerBottomRightRadius.toFloat())
        calculateRadiiAndRectF(true)
    }

    fun setMaskColor(@ColorInt maskColor: Int) {
        this.maskColor = maskColor
        invalidate()
    }

    private fun init(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.AngleCircleImageView, 0, 0)
        for (i in 0 until ta.indexCount) {
            val attr = ta.getIndex(i)
            if (attr == R.styleable.AngleCircleImageView_is_cover_src) {
                isCoverSrc = ta.getBoolean(attr, isCoverSrc)
            } else if (attr == R.styleable.AngleCircleImageView_is_circle) {
                isCircle = ta.getBoolean(attr, isCircle)
            } else if (attr == R.styleable.AngleCircleImageView_n_border_width) {
                borderWidth = ta.getDimensionPixelSize(attr, borderWidth)
            } else if (attr == R.styleable.AngleCircleImageView_n_border_color) {
                borderColor = ta.getColor(attr, borderColor)
            } else if (attr == R.styleable.AngleCircleImageView_inner_border_width) {
                innerBorderWidth = ta.getDimensionPixelSize(attr, innerBorderWidth)
            } else if (attr == R.styleable.AngleCircleImageView_inner_border_color) {
                innerBorderColor = ta.getColor(attr, innerBorderColor)
            } else if (attr == R.styleable.AngleCircleImageView_corner_radius) {
                cornerRadius = ta.getDimensionPixelSize(attr, cornerRadius)
            } else if (attr == R.styleable.AngleCircleImageView_corner_top_left_radius) {
                cornerTopLeftRadius = ta.getDimensionPixelSize(attr, cornerTopLeftRadius)
            } else if (attr == R.styleable.AngleCircleImageView_corner_top_right_radius) {
                cornerTopRightRadius = ta.getDimensionPixelSize(attr, cornerTopRightRadius)
            } else if (attr == R.styleable.AngleCircleImageView_corner_bottom_left_radius) {
                cornerBottomLeftRadius = ta.getDimensionPixelSize(attr, cornerBottomLeftRadius)
            } else if (attr == R.styleable.AngleCircleImageView_corner_bottom_right_radius) {
                cornerBottomRightRadius = ta.getDimensionPixelSize(attr, cornerBottomRightRadius)
            } else if (attr == R.styleable.AngleCircleImageView_mask_color) {
                maskColor = ta.getColor(attr, maskColor)
            }
        }
        ta.recycle()
        borderRadii = FloatArray(8)
        srcRadii = FloatArray(8)
        borderRectF = RectF()
        srcRectF = RectF()
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        calculateRadii()
        clearInnerBorderWidth()
    }

}