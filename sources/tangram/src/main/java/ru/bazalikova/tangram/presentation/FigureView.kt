package ru.bazalikova.tangram.presentation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import ru.bazalikova.tangram.R

abstract class FigureView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {

    protected val paint: Paint
    protected val strokePaint: Paint
    protected val path = Path()
    protected val matr = Matrix()

    var rotate: Float

    var isCovered: Boolean = false

    init {
        strokePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 2f
            style = Paint.Style.STROKE
        }

        val typeArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.FigureView, 0, 0)
        val paintColor = typeArray?.getColor(R.styleable.FigureView_color, Color.WHITE)
        rotate = typeArray?.getFloat(R.styleable.FigureView_rotate, 0f) ?: 0f
        typeArray?.recycle()

        paint = Paint().apply {
            color = paintColor ?: Color.TRANSPARENT
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        drawPath()

        if (rotate != 0f) {
            matr.reset()
            matr.setRotate(rotate, width / 2f, height / 2f)
            path.transform(matr)
        }

        canvas.drawPath(path, paint)
        canvas.drawPath(path, strokePaint)
    }

    protected abstract fun drawPath()

    fun startRotateAnimation(newRotate: Float) {
        val animator = ValueAnimator.ofFloat(this.rotate, newRotate).apply {
            duration = 2000
            addUpdateListener {
                rotate = it.animatedValue as? Float ?: rotate
                invalidate()
            }
        }

        animator.start()
    }
}