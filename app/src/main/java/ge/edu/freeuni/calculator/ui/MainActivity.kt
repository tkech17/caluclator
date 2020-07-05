package ge.edu.freeuni.calculator.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import ge.edu.freeuni.calculator.R
import ge.edu.freeuni.calculator.ui.customview.CalculatorKeyboard

class MainActivity : AppCompatActivity(), MainScene.View {

    enum class ThemeStatus {
        LIGHT,
        DARK
    }

    private var theme: ThemeStatus = ThemeStatus.DARK

    private lateinit var container: ConstraintLayout
    private lateinit var themeChanger: TextView
    private lateinit var inputField: TextView
    private lateinit var resultField: TextView
    private lateinit var calculatorKeyboard: CalculatorKeyboard
    private lateinit var presenter: MainScene.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)

        presenter = mainScenePresenter(this)
        container = findViewById(R.id.activity_main_container)
        themeChanger = findViewById(R.id.activity_main_switch_to_light_theme)
        inputField = findViewById(R.id.activity_main_input)
        resultField = findViewById(R.id.activity_main_result)
        calculatorKeyboard = findViewById(R.id.activity_main_calculator_keyboard)

        addThemeChangerListener()
        addCalculatorKeyboardListeners()
    }

    private fun addThemeChangerListener() {
        themeChanger.setOnClickListener { presenter.themeChanged() }
    }

    private fun addCalculatorKeyboardListeners() {
        calculatorKeyboard.listenCalculatorEvents(presenter)
    }

    override fun getCurrentTheme(): ThemeStatus {
        return theme
    }

    override fun setTheme(theme: ThemeStatus) {
        this.theme = theme
        if (theme == ThemeStatus.LIGHT) {
            themeChanger.setTextColor(ContextCompat.getColor(this, R.color.light_green))

            val drawableColor: Drawable? = ContextCompat.getDrawable(this, R.color.white)
            inputField.background = drawableColor
            container.background = drawableColor
            calculatorKeyboard.setTheme(this.applicationContext, theme)

        } else {
            themeChanger.setTextColor(ContextCompat.getColor(this, R.color.light_yellow))
            container.background = ContextCompat.getDrawable(this, R.color.black)
            inputField.background = ContextCompat.getDrawable(this, R.color.dark_grey)
            calculatorKeyboard.setTheme(this.applicationContext, theme)
        }
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