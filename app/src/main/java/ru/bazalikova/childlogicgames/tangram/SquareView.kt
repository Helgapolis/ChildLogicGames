package ru.bazalikova.childlogicgames.tangram

import android.content.Context
import android.util.AttributeSet

class SquareView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FigureView(context, attrs, defStyleAttr) {

    override fun drawPath() {
        path.moveTo(0f, height.toFloat())
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(width.toFloat(), 0f)
        path.lineTo(0f, 0f)
        path.close()
    }
}