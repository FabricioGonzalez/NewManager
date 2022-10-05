package com.kaskin.manager.presentation.home.visitList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.kaskin.manager.Views.Adapters.AbasAdapter
import com.kaskin.manager.databinding.FragmentVisitListBinding
import com.kaskin.manager.domain.week.entities.WeekDay
import com.kaskin.manager.presentation.home.visitList.clientVisit.ClientVisitFragment
import com.kaskin.manager.utils.Resource

class VisitListFragment : Fragment() {

    private var _binding: FragmentVisitListBinding? = null

    private val visitViewModel by activityViewModels<VisitListViewModel>()

    private lateinit var adapter: AbasAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentVisitListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = AbasAdapter(requireActivity())
        adapter.clear()
        
        setupObservers()

        val viewPager = binding.abasViewPager
        viewPager.adapter = adapter
        val tabLayout = binding.abas
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val item = adapter.getPageTitle(position)
            if (item != null)
                tab.text = adapter.getPageTitle(position)
        }.attach()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visitViewModel.getWeekDays()
    }

    private fun setupObservers() {
        lifecycleScope.launchWhenCreated {
            visitViewModel.weekDays.collect { result ->
                when (result) {
                    is Resource.Success<List<WeekDay>> -> {
                        result.data?.forEach { day ->
                            val fragment = ClientVisitFragment()
                            fragment.SetDay(day = day.dayNumber.toString())
                            adapter.add(fragment, day.dayName)
                        }
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error<List<WeekDay>> -> {
                        Toast
                            .makeText(
                                requireContext(),
                                result.message,
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                    is Resource.Loading<List<WeekDay>> -> {
                        Toast
                            .makeText(
                                requireContext(),
                                "Loading ...",
                                Toast.LENGTH_LONG
                            )
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}