package ge.edu.freeuni.calculator.ui.customview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import ge.edu.freeuni.calculator.R


class CalculatorKeyboard(context: Context?, attrs: AttributeSet) : ConstraintLayout(context, attrs) {


    init {
        inflate(context, R.layout.calculator_keyboard, this);
    }

}