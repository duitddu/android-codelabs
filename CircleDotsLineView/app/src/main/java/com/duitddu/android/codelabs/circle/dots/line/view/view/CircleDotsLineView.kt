package com.duitddu.android.codelabs.circle.dots.line.view.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.duitddu.android.codelabs.circle.dots.line.view.R
import kotlin.math.roundToInt

class CircleDotsLineView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
): View(context, attrs, defStyle) {

    private var dotSize: Float = 0f
    set(value) {
        field = value
        dotRadius = value / 2
    }

    private var dotSpacing: Float = 0f
    private var dotBorderSize: Float = 0f

    private var dotInnerColor: Int = Color.BLACK
    private var dotBorderColor: Int = Color.TRANSPARENT

    private var isVertical: Boolean = true

    private var innerPaint = Paint()
    private var borderPaint = Paint()

    private var dotRadius: Float = 0f

    private val dotCount: Int
        get() {
            val lineSize = (if (isVertical) measuredHeight else measuredWidth)
            val dotArea = dotSize + dotSpacing
            return (lineSize / dotArea).toInt()
        }

    init {
        init(attrs)
        setPaint()
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.run {
            context.obtainStyledAttributes(this, R.styleable.CircleDotsLineView)
        }?.run {
            dotSize = getDimension(R.styleable.CircleDotsLineView_dotSize, 0f)
            dotSpacing = getDimension(R.styleable.CircleDotsLineView_dotSpacing, 0f)
            dotBorderSize = getDimension(R.styleable.CircleDotsLineView_dotBorderSize, 0f)
            dotInnerColor = getColor(R.styleable.CircleDotsLineView_dotInnerColor, dotInnerColor)
            dotBorderColor = getColor(R.styleable.CircleDotsLineView_dotBorderColor, dotBorderColor)
            isVertical = getBoolean(R.styleable.CircleDotsLineView_vertical, isVertical)

            recycle()
        }
    }

    private fun setPaint() {
        innerPaint.run {
            color = dotInnerColor
            isAntiAlias = true
            style = Paint.Style.FILL
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }

        borderPaint.run {
            color = dotBorderColor
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            strokeWidth = dotBorderSize
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthDimension = if (isVertical) (dotSize + dotBorderSize).roundToInt() else widthMeasureSpec
        val heightDimension = if (isVertical) heightMeasureSpec else (dotSize + dotBorderSize).roundToInt()

        setMeasuredDimension(widthDimension, heightDimension)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val dotCount = dotCount
        val dotsAreaSize = (dotSpacing + dotSize) * dotCount
        val startOffset = if (isVertical) (measuredHeight - dotsAreaSize) / 2 else (measuredWidth - dotsAreaSize) / 2

        val startXPos = if (isVertical) measuredWidth / 2f else startOffset + dotSpacing / 2f + dotRadius
        val startYPos = if (isVertical) startOffset + dotSpacing / 2f + dotRadius else measuredHeight / 2f
        val drawOffset = dotSpacing + dotSize
        
        for (i in 0 until dotCount) {
            val xPos = if (isVertical) startXPos else startXPos + drawOffset * i
            val yPos = if (isVertical) startYPos + drawOffset * i else startYPos
            canvas?.drawCircle(xPos, yPos, dotRadius, borderPaint)
            canvas?.drawCircle(xPos, yPos, dotRadius, innerPaint)
        }
    }
}