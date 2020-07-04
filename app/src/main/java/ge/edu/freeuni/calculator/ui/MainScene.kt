package ge.edu.freeuni.calculator.ui

interface MainScene {

    interface View {
        fun clearInputField()
        fun getInputText(): String
        fun setInputText(value: String)
        fun setEvaluatedValue(value: String)
    }

    interface Presenter {
        fun clearInputField()
        fun onNumberOrOperator(value: String)
        fun evaluate()
    }

}