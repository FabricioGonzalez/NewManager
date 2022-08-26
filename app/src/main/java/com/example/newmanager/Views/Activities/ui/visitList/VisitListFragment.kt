package com.example.newmanager.Views.Activities.ui.visitList

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newmanager.Views.Activities.ui.clientVisit.ClientVisitFragment
import com.example.newmanager.Views.Adapters.AbasAdapter
import com.example.newmanager.databinding.FragmentVisitListBinding

class VisitListFragment() : Fragment(){

    private var _binding: FragmentVisitListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(VisitListViewModel::class.java)

        _binding = FragmentVisitListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = AbasAdapter(fragmentManager!!)

        adapter.adicionar(ClientVisitFragment().SetDay("1"), "Primeira Aba")
        adapter.adicionar(ClientVisitFragment().SetDay("2"), "Segunda Aba")
        adapter.adicionar(ClientVisitFragment().SetDay("3"), "Terceira Aba")

        val viewPager = binding.abasViewPager
        viewPager.adapter = adapter

        val tabLayout = binding.abas
        tabLayout.setupWithViewPager(viewPager)

        return root
    }

}