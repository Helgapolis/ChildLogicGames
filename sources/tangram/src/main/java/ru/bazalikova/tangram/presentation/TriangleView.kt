package ru.bazalikova.tangram.presentation

import android.content.Context
import android.util.AttributeSet

class TriangleView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FigureView(context, attrs, defStyleAttr) {

    override fun drawPath() {
        path.reset()
        path.moveTo(0f, 3 / 4f * height.toFloat())
        path.lineTo(width / 2f, height / 4f)
        path.lineTo(width.toFloat(), 3 / 4f * height.toFloat())
        path.close()
    }
}