package ge.edu.freeuni.calculator.ui

class MainScenePresenterImpl(private val view: MainScene.View) : MainScene.Presenter {

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
        clearInputField()
        val evaluatedValue: String = evaluateExpression(inputValue)
        view.setEvaluatedValue(evaluatedValue)
    }

    private fun evaluateExpression(expression: String): String {
        return expression
    }
}


fun mainScenePresenter(view: MainScene.View): MainScene.Presenter {
    return MainScenePresenterImpl(view)
}