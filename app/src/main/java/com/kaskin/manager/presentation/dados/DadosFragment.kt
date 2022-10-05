package com.kaskin.manager.presentation.dados

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.kaskin.manager.Views.Adapters.AbasAdapter
import com.kaskin.manager.databinding.FragmentDadosBinding
import com.kaskin.manager.presentation.dados.database_backup.DatabaseBackupFragment
import com.kaskin.manager.presentation.dados.database_clean_up.DatabaseCleanUpFragment
import com.kaskin.manager.presentation.dados.database_list.DatabaseListFragment


class DadosFragment : Fragment() {
    private lateinit var viewModel: DadosViewModel

    private var _binding: FragmentDadosBinding? = null

    private val binding get() = _binding!!

    private val map: MutableMap<String, Fragment> =
        mutableMapOf(
            Pair("Bancos de Dados", DatabaseListFragment()),
            Pair("Limpeza de Banco de Dados", DatabaseCleanUpFragment()),
            Pair("Backup de Banco de Dados", DatabaseBackupFragment())
        )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DadosViewModel::class.java]

        _binding = FragmentDadosBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val adapter = AbasAdapter(requireActivity())
        map.forEach { item ->
            adapter.add(item.value, item.key)
        }

        val viewPager = binding.abasViewPager
        viewPager?.adapter = adapter

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    adapter.refreshFragment(0)
                } /*else if (position == 1) {
                    adapter.refreshFragment(1)
                } else if (position == 2) {
                    adapter.refreshFragment(2)
                }*/
                super.onPageSelected(position)
            }
        })

        val tabLayout = binding.abas
        TabLayoutMediator(tabLayout!!, viewPager!!) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.abasViewPager?.unregisterOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {

                } else if (position == 1) {
                    // you are on the second page
                } else if (position == 2) {
                    // you are on the third page
                }
                super.onPageSelected(position)
            }
        })
    }
}