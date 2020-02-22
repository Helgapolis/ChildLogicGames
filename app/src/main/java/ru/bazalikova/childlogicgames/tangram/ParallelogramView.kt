package ru.bazalikova.childlogicgames.tangram

import android.content.Context
import android.util.AttributeSet

class ParallelogramView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FigureView(context, attrs, defStyleAttr)
{
    override fun drawPath()
    {
        path.moveTo(width.toFloat(),0f)
        path.lineTo(width.toFloat()/2, 0f)
        path.lineTo(0f, height.toFloat())
        path.lineTo(width/2f, height.toFloat())
        path.close()
    }
}