package ru.bazalikova.fifteen.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import ru.bazalikova.fifteen.data.Cell

class CellImageView(context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0) : ImageView(context, attrs, defStyleAttrs) {

    var clickListener: ClickListener? = null

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)

        if (event?.action == MotionEvent.ACTION_DOWN)
            performClick()

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()

        val cell = tag as? Cell ?: return true
        clickListener?.onCellClicked(cell)
        return true
    }

    interface ClickListener {
        fun onCellClicked(cell: Cell)
    }
}