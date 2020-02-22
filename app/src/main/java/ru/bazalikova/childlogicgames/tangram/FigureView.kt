package ru.bazalikova.childlogicgames.tangram

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ru.bazalikova.childlogicgames.R

abstract class FigureView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr)
{
    protected val paint: Paint
    protected val strokePaint: Paint
    protected val path = Path()
    protected var rotate: Float
    protected val matr = Matrix()

    var isCovered: Boolean = false

    init
    {
        strokePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }

        val typeArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.FigureView, 0, 0)
        val paintColor = typeArray?.getColor(R.styleable.FigureView_color, Color.TRANSPARENT)
        rotate = typeArray?.getFloat(R.styleable.FigureView_rotate, 0f) ?: 0f

        paint = Paint().apply{
            color = paintColor ?: Color.TRANSPARENT
        }
    }

    override fun onDraw(canvas: Canvas?)
    {
        super.onDraw(canvas)

        if (canvas == null) return

        drawPath()

        if (rotate != 0f)
        {
            matr.reset()
            matr.setRotate(rotate, width/2f, height/2f)
            path.transform(matr)
        }

        canvas.drawPath(path, paint)
        canvas.drawPath(path, strokePaint)
    }

    protected abstract fun drawPath()

    fun startMergeAnimation(figureView: FigureView)
    {
        val animator = ValueAnimator.ofFloat(this.rotate, figureView.rotate).apply {
            duration = 2000
            addUpdateListener {
                rotate = it.animatedValue as? Float ?: rotate
                invalidate()
            }
        }

        animator.start()
    }
}