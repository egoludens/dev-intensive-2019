package ru.skillbranch.devintensive.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.Color.parseColor
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import ru.skillbranch.devintensive.R
import android.graphics.RectF
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.core.graphics.toRectF
import ru.skillbranch.devintensive.extensions.dpToPx


class CircleImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr)
{
    companion object {
        private const val DEFAULT_BORDER_COLOR = 0xFFFFFF
        private const val DEFAULT_BORDER_WIDTH : Int = 2

    }

    private var borderColor = DEFAULT_BORDER_COLOR
    private var borderWidth : Int = DEFAULT_BORDER_WIDTH

    private var maskPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var viewRect = Rect()
    private var borderRect = Rect()
    private lateinit var resultBitmap : Bitmap
    private lateinit var maskBitmap : Bitmap
    private lateinit var srcBitmap : Bitmap

    init {
        if (attrs != null) {
            val pxDefaultBorderWidth = context.dpToPx(DEFAULT_BORDER_WIDTH)
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            borderColor = a.getColor(R.styleable.CircleImageView_cv_borderColor, DEFAULT_BORDER_COLOR)
            borderWidth = a.getDimension(R.styleable.CircleImageView_cv_borderWidth, pxDefaultBorderWidth).toInt()
            a.recycle()
        }
        setupView()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (w != 0)
        {
            with(viewRect)
            {
                left = 0
                top = 0
                right = w
                bottom = h
            }
        }
        borderRect = viewRect
        val offset = (borderWidth / 2).toInt()
        borderRect.inset(offset, offset)
        buildResultBitmap()
    }

    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        canvas.drawBitmap(resultBitmap, viewRect, viewRect, null)
        if(borderPaint.strokeWidth > 0) {
            canvas.drawOval(borderRect.toRectF(), borderPaint)
        }
    }

    private fun setupView()
    {
        maskPaint.color = Color.RED
        maskPaint.style = Paint.Style.FILL

        with(borderPaint){
            style = Paint.Style.STROKE
            strokeWidth = context.dpToPx(borderWidth)
            color = borderColor
        }

    }
    private fun buildResultBitmap()
    {
        maskBitmap = Bitmap.createBitmap(viewRect.width(), viewRect.height(), Bitmap.Config.ALPHA_8)
        val maskCanvas = Canvas(maskBitmap)
        maskCanvas.drawOval(viewRect.toRectF(), maskPaint)
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

        srcBitmap = drawableToBitmap(drawable)

        resultBitmap = Bitmap.createBitmap(viewRect.width(), viewRect.height(), Bitmap.Config.ARGB_8888)
        val resultCanvas = Canvas(resultBitmap)
        resultCanvas.drawBitmap(maskBitmap, viewRect, viewRect, null)
        resultCanvas.drawBitmap(srcBitmap, viewRect, viewRect, maskPaint)
    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable)
        {
            return drawable.bitmap
        }
        else
        {
            val bitmap = Bitmap.createBitmap(viewRect.width(), viewRect.height(), Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }
    }

    @Dimension(unit = Dimension.DP)
    fun getBorderWidth(): Int {
        return borderWidth
    }

    fun setBorderWidth(@Dimension(unit = Dimension.DP) dp:Int) {
        borderWidth = dp
        setupView()
        buildResultBitmap()
        invalidate()
    }

    fun getBorderColor(): Int {
        return borderColor
    }

    fun setBorderColor(hex:String) {
        borderColor = parseColor(hex)
        setupView()
        buildResultBitmap()
        invalidate()
    }

    fun setBorderColor(@ColorRes colorId: Int) {
        borderColor = colorId
        setupView()
        buildResultBitmap()
        invalidate()
    }

//    у CircleImageView должны быть реализованы методы @Dimension getBorderWidth():Int,
//    setBorderWidth(@Dimension dp:Int),
//    getBorderColor():Int,
//    setBorderColor(hex:String),
//    setBorderColor(@ColorRes colorId: Int). Используй CircleImageView как ImageView для аватара пользователя (@id/iv_avatar)


}