package br.com.kaskin.presentation.communication.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SendViewModel(/*private val pedidosDatabase: PedidosDatabaseRepository*/) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

/*    private val pedidosRepository = PedidosRepository()*/

    fun sendPedidos() {
        viewModelScope.launch(Dispatchers.IO) {
          /*  var pedidos = pedidosDatabase.getAllPedidosFeitos()

            pedidos.forEach { pdd ->
                pdd.Items.forEach { item ->
                    println("${pdd._ID_PDDPMT} - ${item.CODPDTCMZ} - ${item.QTDITEPDD} - ${item.QTDUNICXA} - ${item.VALTOTITEPDD}")
                }
            }
            pedidosRepository.sendPedidos(pedidos)*/
        }
    }

}