package ru.bazalikova.childlogicgames.tangram

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.house_view.view.*

class HouseView
@kotlin.jvm.JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
: RelativeLayout(context, attrs, defStyleAttr)
{
    private var xDelta = 0
    private var yDelta = 0

    var listener: TangramViewListener? = null

    fun setListeners() {
        val triangleList = listOf(partHouseRoofTriangle, partHouseWallTriangle)
        setTouchListener(triangleViewFirst, triangleList)
        setTouchListener(triangleViewSecond, triangleList)

        setTouchListener(squareView, listOf(partHouseSquare))
        setTouchListener(parallelogramView, listOf(partHouseParallelogram))
        setTouchListener(middleTriangleView, listOf(partHouseWallMiddleTriangle))

        val smallTriangleList =
            listOf(partHouseWallSmallTriangleFirst, partHouseWallSmallTriangleSecond)

        setTouchListener(smallTriangleViewFirst, smallTriangleList)
        setTouchListener(smallTriangleViewSecond, smallTriangleList)
    }

    private fun setTouchListener(figureView: FigureView, partHouseViews: List<FigureView>) {
        val marginLayoutParams = figureView.layoutParams as? MarginLayoutParams ?: return
        val top = marginLayoutParams.topMargin
        val bottom = marginLayoutParams.bottomMargin
        val left = marginLayoutParams.leftMargin
        val right = marginLayoutParams.rightMargin

        figureView.setOnTouchListener{ view, event ->

            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val layoutParams = view.layoutParams as LayoutParams

                    xDelta = x - layoutParams.leftMargin
                    yDelta = y - layoutParams.topMargin
                }

                MotionEvent.ACTION_UP -> {
                    for (partHouseView in partHouseViews) {
                        val triangleViewRect = Rect()
                        view.getHitRect(triangleViewRect)
                        val partHouseViewRect = Rect()
                        partHouseView.getHitRect(partHouseViewRect)

                        if (isRectFit(
                                triangleViewRect,
                                partHouseViewRect
                            ) && !partHouseView.isCovered
                        ) {
                            figureView.layoutParams = partHouseView.layoutParams
                            figureView.setOnTouchListener(null)
                            figureView.startMergeAnimation(partHouseView)
                            partHouseView.isCovered = true

                            if (isHouseBuild()) {
                                listener?.onTangramBuild()
                            }

                            return@setOnTouchListener true
                        }
                    }

                    val layoutParams = view.layoutParams as LayoutParams

                    layoutParams.leftMargin = left
                    layoutParams.topMargin = top
                    layoutParams.rightMargin = right
                    layoutParams.bottomMargin = bottom

                    view.layoutParams = layoutParams
                }

                MotionEvent.ACTION_MOVE -> {
                    val layoutParams = view.layoutParams as LayoutParams

                    layoutParams.leftMargin = x - xDelta
                    layoutParams.topMargin = y - yDelta
                    layoutParams.rightMargin = -250
                    layoutParams.bottomMargin = -250

                    view.layoutParams = layoutParams
                }
            }

            true
        }
    }

    private fun isHouseBuild(): Boolean {
        val houseParts = listOf(
            partHouseParallelogram,
            partHouseRoofTriangle,
            partHouseSquare,
            partHouseWallMiddleTriangle,
            partHouseWallSmallTriangleFirst,
            partHouseWallSmallTriangleSecond,
            partHouseWallTriangle
        )

        for (partHouse in houseParts) {
            if (!partHouse.isCovered) {
                return false
            }
        }

        return true
    }

    private fun isRectFit(rect1: Rect, rect2: Rect): Boolean {
        val diff = 20
        return rect1.centerX() + diff >= rect2.centerX() &&
                rect1.centerX() - diff <= rect2.centerX() &&
                rect1.centerY() + diff >= rect2.centerY() &&
                rect1.centerY() - diff <= rect2.centerY()
    }
}