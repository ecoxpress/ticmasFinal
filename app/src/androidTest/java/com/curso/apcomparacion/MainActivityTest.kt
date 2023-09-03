package com.curso.apcomparacion

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    @get:Rule
    val rule: ActivityScenarioRule<*> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainActivity_ClicCompararVacio(){
        Espresso.onView(
            ViewMatchers.withId(R.id.btncomparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.idresultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Cadenas Iguales")
            )
        )
    }

    @Test
    fun mainActivity_ClicCompararCadenasIguales(){
        Espresso.onView(
            ViewMatchers.withId(R.id.idtexto1)
        ).perform(ViewActions.replaceText("Hola"))
        Espresso.onView(
            ViewMatchers.withId(R.id.idtexto2)
        ).perform(ViewActions.replaceText("Hola"))
        Espresso.onView(
            ViewMatchers.withId(R.id.btncomparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.idresultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("LOS TEXTOS NO SON IGUALES")
            )
        )
    }
    @Test
    fun mainActivity_ClicCompararCadenasNoIguales(){
        Espresso.onView(
            ViewMatchers.withId(R.id.idtexto1)
        ).perform(ViewActions.replaceText("Hola2"))
        Espresso.onView(
            ViewMatchers.withId(R.id.idtexto2)
        ).perform(ViewActions.replaceText("Hola"))
        Espresso.onView(
            ViewMatchers.withId(R.id.btncomparar)
        ).perform(
            ViewActions.click()
        )
        Espresso.onView(
            ViewMatchers.withId(R.id.idresultado)
        ).check(
            ViewAssertions.matches(
                ViewMatchers.withText("LOS TEXTOS NO SON IGUALES")
            )
        )
    }
}