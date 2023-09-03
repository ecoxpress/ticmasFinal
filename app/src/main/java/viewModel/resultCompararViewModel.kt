package viewModel

import Model.resultComparar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class resultCompararViewModel: ViewModel() {
    private val comparacion = MutableLiveData<resultComparar>()
    val comparar: LiveData<resultComparar> = comparacion

    fun compararCadenas(cadena1:String,cadena2:String){
        val resultado = if(cadena1 == cadena2) "LOS TEXTOS SON IGUALES" else "LOS TEXTOS NO SON IGUALES"
        comparacion.value = resultComparar(resultado)
    }

    // Agrega la función de reset para restablecer el valor de comparacion
    fun reset() {
        comparacion.value = resultComparar("") // Restablece el valor a una cadena vacía
    }
}