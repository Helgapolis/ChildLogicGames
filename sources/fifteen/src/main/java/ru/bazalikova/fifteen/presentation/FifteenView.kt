package ru.bazalikova.fifteen.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TableRow
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.activity_fifteen.view.*
import ru.bazalikova.fifteen.R
import ru.bazalikova.fifteen.data.Cell
import ru.bazalikova.fifteen.data.FifteenField
import javax.inject.Inject

class FifteenView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    ConstraintLayout(context, attrs, defStyleAttr), IFifteenView {
    @Inject
    lateinit var presenter: FifteenPresenter

    fun onFinishInflate(presenter: FifteenPresenter) {
        this.presenter = presenter

        actFifteenRepeatButton.setOnClickListener {
            presenter.onRepeatButtonClick()
        }

        actFifteenCancelButton.setOnClickListener {
            presenter.onCancelButtonClick()
        }
    }

    override fun setField(field: FifteenField) {
        actFifteenFieldLayout.removeAllViews()

        val padding = convertDpToPx(
            context,
            context.resources.getDimension(R.dimen.fifteen_cell_padding)
        ).toInt()

        for (i in 0 until field.width) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )

            for (j in 0 until field.height) {
                val imageView = CellImageView(context)
                val drawableId = context.resources.getIdentifier(
                    "img_" + field[i, j].toString(),
                    "drawable",
                    context.packageName
                )
                imageView.setImageResource(drawableId)
                imageView.setPadding(padding, padding, padding, padding)
                imageView.scaleType = ImageView.ScaleType.CENTER
                imageView.tag = Cell(i, j)

                imageView.clickListener = object : CellImageView.ClickListener {
                    override fun onCellClicked(cell: Cell) {
                        presenter.move(cell)
                    }
                }

                tableRow.addView(imageView, j)
            }

            actFifteenFieldLayout.addView(tableRow, i)
        }
    }

    override fun setRepeatButton(isVisible: Boolean) {
        actFifteenRepeatButton.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    override fun setGameOver(isVisible: Boolean) {
        actFifteenGoodResultTextView.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun convertDpToPx(context: Context, dp: Float): Float {
        return dp * context.resources.displayMetrics.density
    }
}