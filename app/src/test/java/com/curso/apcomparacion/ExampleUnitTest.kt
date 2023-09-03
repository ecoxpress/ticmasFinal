package com.curso.apcomparacion

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import viewModel.resultCompararViewModel


class ExampleUnitTest {
    private lateinit var _viewModel : resultCompararViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()
    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        _viewModel = resultCompararViewModel()
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    /****************************/
    val vacio = null
    @Test
    fun verificarEstadoInicialdelResultado() = runTest{
        val value = _viewModel.comparar.value?.igual
        TestCase.assertEquals(vacio,value)
    }

    /****************************/
    private val noIguales = "LOS TEXTOS NO SON IGUALES"
    @Test
    fun verificarCadenasNoIguales() = runTest{
        launch {
            val cad1= "Hola"
            val cad2= "hola"
            _viewModel.compararCadenas(cad1,cad2)
        }
        advanceUntilIdle()
        val resultado = _viewModel.comparar.value?.igual
        TestCase.assertEquals(noIguales, resultado)
    }
    private val Iguales = "LOS TEXTOS SON IGUALES"
    @Test
    fun verificarCadenasIguales() = runTest{
        launch {
            val cad1= "Hola"
            val cad2= "Hola"
            _viewModel.compararCadenas(cad1,cad2)
        }
        advanceUntilIdle()
        val resultado = _viewModel.comparar.value?.igual
        TestCase.assertEquals(Iguales, resultado)
    }
    /*************************************/
    @Test
    fun verificarTresCadenasYTerminaIguales() = runTest{
        val cad1= "Hola"
        val cad2= "Holaaaaaaaa"
        launch {
            _viewModel.compararCadenas(cad1,cad2)
        }
        val cad3= "Hola"
        launch {
            _viewModel.compararCadenas(cad1,cad3)
        }
        advanceUntilIdle()
        val resultado = _viewModel.comparar.value?.igual
        TestCase.assertEquals(Iguales, resultado)
    }
}