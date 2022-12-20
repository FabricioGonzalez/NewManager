package com.kaskin.manager.presentation.vendas.pedidos.finalizar_pedido

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaskin.manager.databinding.FragmentFinalizarPedidoBinding

class FinalizarPedidoFragment : Fragment() {

    private var _binding: FragmentFinalizarPedidoBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalizarPedidoBinding.inflate(inflater, container, false)

        val root = binding.root

        return root
    }

}