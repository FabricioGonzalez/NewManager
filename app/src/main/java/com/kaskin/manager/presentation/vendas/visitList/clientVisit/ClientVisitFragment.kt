package com.kaskin.manager.presentation.vendas.visitList.clientVisit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kaskin.manager.databinding.FragmentClientVisitBinding
import com.kaskin.manager.presentation.adapters.client.ClientListAdapter
import com.kaskin.manager.presentation.adapters.itemclick.ClickListener
import com.kaskin.manager.presentation.adapters.itemclick.RecyclerTouchListener
import com.kaskin.manager.presentation.vendas.visitList.client_options.ClientOptionsDialogFragment
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

    private var loadingProgressBar:ProgressBar? = null
    private var loadingBox:FrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientVisitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadingProgressBar = binding.loadingProgressbar
        loadingBox = binding.loading
        loadingProgressBar?.visibility = View.GONE

        clientListAdapter = ClientListAdapter()

        setupClientList()

        observersSetup()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showClientOptionsDialog(codigo:Int){

        // Create an instance of the dialog fragment and show it
        val dialog = ClientOptionsDialogFragment(codigo)
        dialog.show(requireActivity().supportFragmentManager, "NoticeDialogFragment")

    }

    private fun setupClientList() = binding.clientList.apply {
        adapter = clientListAdapter
        layoutManager =
            LinearLayoutManager(this.context)

        val dividerItemDecoration = DividerItemDecoration(
            context,
            (layoutManager as LinearLayoutManager).orientation
        )
        addItemDecoration(dividerItemDecoration)

        addOnItemTouchListener(
            RecyclerTouchListener(requireContext(),
                this, object : ClickListener {
                    override fun onClick(view: View?, position: Int) {
                        //Values are passing to activity & to fragment as well
                    }

                    override fun onDoubleClick(view: View?, position: Int) {
                        //Values are passing to activity & to fragment as well
                    }

                    override fun onLongClick(view: View?, position: Int) {

                        val client = (binding.clientList.adapter as ClientListAdapter).getItem(position)

                        showClientOptionsDialog(client.codigoCliente)
                    }
                })
        )

    }


    private fun observersSetup() {
        viewLifecycleOwner.lifecycleScope.launch {
            clientVisitViewModel.clients.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                            loadingProgressBar?.visibility = View.VISIBLE
                            loadingBox?.visibility = View.VISIBLE

                    }
                    is Resource.Error -> {
                        clientListAdapter.SetDataSet(emptyList())

                        loadingProgressBar?.visibility = View.GONE
                        loadingBox?.visibility = View.GONE

                        Log.e("AppError", "clients: ${result.message}")
                    }
                    is Resource.Success -> {
                        result.data?.let { clientListAdapter.SetDataSet(it) }
                        loadingProgressBar?.visibility = View.GONE
                        loadingBox?.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        clientVisitViewModel.changeDay(day)
        clientVisitViewModel.getClients(setor)
    }

}