package ge.edu.freeuni.calculator.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ge.edu.freeuni.calculator.R
import ge.edu.freeuni.calculator.ui.customview.CalculatorKeyboard

class MainActivity : AppCompatActivity(), MainScene.View {

    private lateinit var inputField: TextView
    private lateinit var resultField: TextView
    private lateinit var calculatorKeyboard: CalculatorKeyboard
    private lateinit var presenter: MainScene.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = mainScenePresenter(this)
        inputField = findViewById(R.id.activity_main_input)
        resultField = findViewById(R.id.activity_main_result)
        calculatorKeyboard = findViewById(R.id.activity_main_calculator_keyboard)

        addCalculatorKeyboardListeners()
    }

    private fun addCalculatorKeyboardListeners() {
        calculatorKeyboard.listenCalculatorEvents(presenter)
    }

    override fun setEvaluatedValue(value: String) {
        resultField.text = value
    }

    override fun clearInputField() {
        inputField.text = ""
    }

    override fun getInputText(): String {
        return inputField.text.toString()
    }

    override fun setInputText(value: String) {
        inputField.text = value
    }

}