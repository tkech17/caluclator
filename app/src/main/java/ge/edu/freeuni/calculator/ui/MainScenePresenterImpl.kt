package ge.edu.freeuni.calculator.ui

import ge.edu.freeuni.calculator.utils.EvaluateString

class MainScenePresenterImpl(private val view: MainScene.View) : MainScene.Presenter {

    companion object {
        private val operatorsAndDot: Set<Char> = setOf('+', '-', '/', '*', '.')
        private val operators: Set<Char> = setOf('+', '-', '/', '*')
    }

    override fun clearInputField() {
        view.clearInputField()
    }

    override fun themeChanged() {
        val themeStatus: MainActivity.ThemeStatus = view.getCurrentTheme()
        if (themeStatus == MainActivity.ThemeStatus.DARK) {
            view.setTheme(MainActivity.ThemeStatus.LIGHT)
        } else {
            view.setTheme(MainActivity.ThemeStatus.DARK)
        }
    }

    override fun onNumberOrOperator(value: String) {
        val prevInput: String = view.getInputText()
        val currInput: String = prevInput + value
        view.setInputText(currInput)
    }

    override fun evaluate() {
        val inputValue = view.getInputText()

        if (!isValidExpression(inputValue)) {
            view.setEvaluatedValue("Not Valid Expression")
        } else {
            clearInputField()
            val evaluatedValue: String = evaluateExpression(inputValue)
            view.setEvaluatedValue(evaluatedValue)
        }
    }

    private fun isValidExpression(expr: String): Boolean {
        if (expr.isBlank()) {
            return true
        }

        if (operatorsAndDot.contains(expr.first()) || operatorsAndDot.contains(expr.last())) {
            return false
        }

        for (i in 0..expr.length - 2) {
            if (operatorsAndDot.contains(expr[i]) && operatorsAndDot.contains(expr[i + 1])) {
                return false
            } else if (expr[i] == '/' && expr[i + 1] == '0') {
                return false
            }
        }
        return true
    }

    private fun evaluateExpression(expression: String): String {
        var spacedOperatorsExpr = ""
        for (c in expression) {
            if (operators.contains(c)) {
                spacedOperatorsExpr += " $c "
            } else {
                spacedOperatorsExpr += c
            }
        }

        val result: Double = EvaluateString.evaluate(spacedOperatorsExpr)

        if (isWhole(result)) {
            return result.toInt().toString()
        }
        return result.toString()
    }

    private fun isWhole(value: Double): Boolean {
        return value - value.toInt() == 0.0
    }

}


fun mainScenePresenter(view: MainScene.View): MainScene.Presenter {
    return MainScenePresenterImpl(view)
}