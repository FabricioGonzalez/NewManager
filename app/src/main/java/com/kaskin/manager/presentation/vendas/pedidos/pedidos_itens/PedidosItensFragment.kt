package com.kaskin.manager.presentation.vendas.pedidos.pedidos_itens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaskin.manager.databinding.FragmentPedidosItensBinding

class PedidosItensFragment : Fragment() {

    private var _binding: FragmentPedidosItensBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

       _binding = FragmentPedidosItensBinding.inflate(inflater,container,false)



        val root = binding.root

        return root
    }
}