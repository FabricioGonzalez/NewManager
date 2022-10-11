package com.kaskin.manager.presentation.home.visitList.clientVisit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaskin.manager.databinding.FragmentClientVisitBinding
import com.kaskin.manager.presentation.adapters.client.ClientListAdapter
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.launch

class ClientVisitFragment(
    private val day: Int,
    private val setor: Int,
) : Fragment() {

    private var _binding: FragmentClientVisitBinding? = null

    private val binding get() = _binding!!
    private lateinit var clientListAdapter: ClientListAdapter

    // Initialize the ViewModel using ViewModelProvider.Factory
    private val clientVisitViewModel: ClientVisitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientVisitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.texto
        clientVisitViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        clientListAdapter = ClientListAdapter()

        setupClientList()

        observersSetup()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupClientList() = binding.clientList?.apply {
        adapter = clientListAdapter
        layoutManager =
            LinearLayoutManager(this.context)
    }


    private fun observersSetup() {
        viewLifecycleOwner.lifecycleScope.launch {
            clientVisitViewModel.clients.collect { result ->
                when (result) {
                    is Resource.Loading -> {

                    }
                    is Resource.Error -> {
                        clientListAdapter.SetDataSet(emptyList())
                        Log.e("AppError", "clients: ${result.message}")
                    }
                    is Resource.Success -> {
                        result.data?.let { clientListAdapter.SetDataSet(it) }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (clientVisitViewModel != null) {

            clientVisitViewModel.changeDay(day)
            clientVisitViewModel.getClients(setor)
        }
    }

}