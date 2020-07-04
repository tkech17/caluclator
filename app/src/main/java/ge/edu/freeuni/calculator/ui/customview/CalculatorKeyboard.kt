package ge.edu.freeuni.calculator.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.GridLayout
import ge.edu.freeuni.calculator.R
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
        val calculatorGrid: GridLayout = this.getChildAt(0) as GridLayout

        for (i in 0 until calculatorGrid.childCount) {
            val button: Button = calculatorGrid.getChildAt(i) as Button

            if (button.id !in notNumberOrOperators) {
                button.setOnClickListener { presenter.onNumberOrOperator(button.text.toString()) }
            }
        }
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

}