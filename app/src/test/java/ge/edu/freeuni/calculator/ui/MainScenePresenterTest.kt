package ge.edu.freeuni.calculator.ui

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

internal class MainScenePresenterTest {

    private lateinit var view: MainScene.View
    private lateinit var presenter: MainScene.Presenter

    @Test
    fun testClearInputField() {
        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }

        presenter = mainScenePresenter(view)
        presenter.clearInputField()

        verify(view, times(1)).clearInputField()
    }

    @Test
    fun testOnNumberOrOperator() {
        view = mock(MainScene.View::class.java)
            .also { `when`(it.getInputText()).thenReturn("1") }

        `when`(view.setInputText(anyString())).thenAnswer {
            assertEquals("1+", it.arguments[0])
        }

        presenter = mainScenePresenter(view)
        presenter.onNumberOrOperator("+")
    }

    @Test
    fun testThemeChangedToDarkMode() {
        view = mock(MainScene.View::class.java)
            .also { `when`(it.getCurrentTheme()).thenReturn(MainActivity.ThemeStatus.LIGHT) }

        `when`(view.setTheme(MainActivity.ThemeStatus.DARK)).thenAnswer {
            assertEquals(MainActivity.ThemeStatus.DARK, it.arguments[0])
        }

        presenter = mainScenePresenter(view)
        presenter.themeChanged()
    }

    @Test
    fun testThemeChangedToLightMode() {
        view = mock(MainScene.View::class.java)
            .also { `when`(it.getCurrentTheme()).thenReturn(MainActivity.ThemeStatus.LIGHT) }

        `when`(view.setTheme(MainActivity.ThemeStatus.LIGHT)).thenAnswer {
            assertEquals(MainActivity.ThemeStatus.LIGHT, it.arguments[0])
        }

        presenter = mainScenePresenter(view)
        presenter.themeChanged()
    }

    @Test
    fun testEvaluateWhenNotValidExpression() {
        val expectedValue = "Not Valid Expression"

        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }

        `when`(view.setEvaluatedValue(anyString())).thenAnswer {
            assertEquals(expectedValue, it.arguments[0])
        }

        presenter = mainScenePresenter(view)

        `when`(view.getInputText()).thenReturn("+1")
        presenter.evaluate()

        `when`(view.getInputText()).thenReturn("1+")
        presenter.evaluate()

        `when`(view.getInputText()).thenReturn("1+1+*")
        presenter.evaluate()

        `when`(view.getInputText()).thenReturn("1/0")
        presenter.evaluate()

        `when`(view.getInputText()).thenReturn("1..0")
        presenter.evaluate()

        `when`(view.getInputText()).thenReturn("1.")
        presenter.evaluate()
    }


    @Test
    fun testEvaluateWhenValidExpression1() {
        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }

        `when`(view.setEvaluatedValue(anyString())).thenAnswer {
            assertEquals("6", it.arguments[0])
        }

        presenter = mainScenePresenter(view)

        `when`(view.getInputText()).thenReturn("1 + 1 * 10 / 2")
        presenter.evaluate()
    }

    @Test
    fun testEvaluateWhenValidExpression2() {
        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }

        `when`(view.setEvaluatedValue(anyString())).thenAnswer {
            assertTrue((it.arguments[0] as String).startsWith("3.3333"))
        }

        presenter = mainScenePresenter(view)

        `when`(view.getInputText()).thenReturn("10 / 3")
        presenter.evaluate()
    }

    @Test
    fun testEvaluateWhenValidExpression3() {
        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }

        `when`(view.setEvaluatedValue(anyString())).thenAnswer {
            assertEquals("5", it.arguments[0])
        }

        presenter = mainScenePresenter(view)

        `when`(view.getInputText()).thenReturn("10 / 2")
        presenter.evaluate()
    }


}