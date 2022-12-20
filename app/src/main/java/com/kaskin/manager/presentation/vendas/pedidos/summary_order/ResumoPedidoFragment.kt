package com.kaskin.manager.presentation.vendas.pedidos.summary_order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kaskin.manager.databinding.FragmentResumoPedidoBinding


class ResumoPedidoFragment : Fragment() {

    private var _binding: FragmentResumoPedidoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentResumoPedidoBinding.inflate(inflater, container, false)

        val root = binding.root

        return root
    }


}