package ge.edu.freeuni.calculator.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.GridLayout
import androidx.core.content.ContextCompat
import ge.edu.freeuni.calculator.R
import ge.edu.freeuni.calculator.ui.MainActivity
import ge.edu.freeuni.calculator.ui.MainScene


class CalculatorKeyboard(context: Context?, attrs: AttributeSet) : GridLayout(context, attrs) {


    init {
        inflate(context, R.layout.calculator_keyboard, this);
    }


    private fun addClearInputFieldButtonListener(presenter: MainScene.Presenter) {
        val clearInputButton: Button = findViewById(R.id.calculator_keyboard_AC)
        clearInputButton.setOnClickListener { presenter.clearInputField() }
    }

    private fun addNumberAndOperatorsButtonListener(presenter: MainScene.Presenter) {
        val notNumberOrOperators: List<Int> = listOf(R.id.calculator_keyboard_AC, R.id.calculator_keyboard_percentage, R.id.calculator_keyboard_plus_minus, R.id.calculator_keyboard_evaluate)

        getKeyboardButtons()
            .filter { it.id !in notNumberOrOperators }
            .forEach { button -> button.setOnClickListener { presenter.onNumberOrOperator(button.text.toString()) } }
    }

    private fun getKeyboardButtons(): List<Button> {
        val calculatorGrid: GridLayout = this.getChildAt(0) as GridLayout
        return (0 until calculatorGrid.childCount).map { calculatorGrid.getChildAt(it) as Button }
    }

    fun listenCalculatorEvents(presenter: MainScene.Presenter) {
        addClearInputFieldButtonListener(presenter)
        addNumberAndOperatorsButtonListener(presenter)
        addEvaluateButtonListener(presenter)
    }

    private fun addEvaluateButtonListener(presenter: MainScene.Presenter) {
        val clearInputButton: Button = findViewById(R.id.calculator_keyboard_evaluate)
        clearInputButton.setOnClickListener { presenter.evaluate() }
    }

    fun setTheme(context: Context, theme: MainActivity.ThemeStatus) {
        if (MainActivity.ThemeStatus.DARK == theme) {
            getKeyboardButtons()
                .forEach { it.background = ContextCompat.getDrawable(context, R.drawable.button_shape_dark) }
        } else {
            getKeyboardButtons()
                .forEach { it.background = ContextCompat.getDrawable(context, R.drawable.button_shape_light) }
        }
    }

}