package ge.edu.freeuni.calculator.ui

import org.junit.jupiter.api.Assertions.assertEquals
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
    fun onEvaluate() {
        view = mock(MainScene.View::class.java)
            .also { doNothing().`when`(it).clearInputField() }
            .also { `when`(it.getInputText()).thenReturn("1+1") }

        `when`(view.setEvaluatedValue(anyString())).thenAnswer {
            assertEquals("1+1", it.arguments[0])
        }


        presenter = mainScenePresenter(view)

        presenter.evaluate()
    }


}