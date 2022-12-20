package com.kaskin.manager.presentation.vendas.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentPedidosBinding
import com.kaskin.manager.presentation.adapters.AbasAdapter
import com.kaskin.manager.presentation.vendas.pedidos.finalizar_pedido.FinalizarPedidoFragment
import com.kaskin.manager.presentation.vendas.pedidos.pedidos_itens.PedidosItensFragment
import com.kaskin.manager.presentation.vendas.pedidos.summary_order.ResumoPedidoFragment

class PedidosFragment : Fragment() {

    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PedidosViewModel

    private lateinit var adapter: AbasAdapter

    private var codigoCliente: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPedidosBinding.inflate(inflater, container, false)

        if (!requireArguments().isEmpty) {
            val arguments = requireArguments().getInt(getString(R.string.visit_to_pedidos_args))

            codigoCliente = arguments
        }

        adapter = setupAdapter()

        val viewPager = binding.abasViewPagerPedidos
        viewPager.adapter = adapter

        val tabLayout = binding.abasPedidos
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val item = adapter.getPageTitle(position)
            if (item != null)
                tab.text = adapter.getPageTitle(position)
        }.attach()

        viewPager?.registerOnPageChangeCallback(viewOnPageChangeCallback)

        val root = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.abasViewPagerPedidos?.unregisterOnPageChangeCallback(viewOnPageChangeCallback)
        _binding = null
    }

    private fun setupAdapter(): AbasAdapter {
        val adapter = AbasAdapter(requireActivity())

        adapter.add(PedidosItensFragment(), getString(R.string.tab_title_items))
        adapter.add(ResumoPedidoFragment(), getString(R.string.tab_title_resumo))
        adapter.add(FinalizarPedidoFragment(), getString(R.string.tab_title_finalizacao))

        return adapter
    }

    private val viewOnPageChangeCallback: ViewPager2.OnPageChangeCallback = object :
        ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            if (adapter.itemCount > 0)
                adapter.refreshFragment(position)

            super.onPageSelected(position)
        }
    }
}