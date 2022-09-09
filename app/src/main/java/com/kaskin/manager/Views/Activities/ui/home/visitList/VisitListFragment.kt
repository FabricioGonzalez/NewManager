package com.kaskin.manager.Views.Activities.ui.home.visitList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.kaskin.manager.Views.Activities.ui.home.visitList.clientVisit.ClientVisitFragment
import com.kaskin.manager.Views.Adapters.AbasAdapter
import com.kaskin.manager.databinding.FragmentVisitListBinding

class VisitListFragment() : Fragment() {

    private var _binding: FragmentVisitListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val visitViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[VisitListViewModel::class.java]

        _binding = FragmentVisitListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = AbasAdapter(requireActivity())

        adapter.adicionar(ClientVisitFragment().SetDay("1"), "Primeira Aba")
        adapter.adicionar(ClientVisitFragment().SetDay("2"), "Segunda Aba")
        adapter.adicionar(ClientVisitFragment().SetDay("3"), "Terceira Aba")

        val viewPager = binding.abasViewPager
        viewPager.adapter = adapter

        val tabLayout = binding.abas
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}