package com.example.newmanager.Views.Activities.ui.home

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.newmanager.Controllers.ConnectionChecker
import com.example.newmanager.R
import com.example.newmanager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private lateinit var connectionChecker: ConnectionChecker

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        UpdateConnectionState(root)
//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    private fun UpdateConnectionState(root:View) {
        connectionChecker = ConnectionChecker(root!!.context)

        val connectionState = root!!.findViewById<ImageView>(R.id.connection_state)
        if (connectionChecker.CheckConnection()) {
            connectionState.setImageResource(R.drawable.ic_tab_logs_ativo)
            return
        }
        connectionState.setImageResource(R.drawable.ic_tab_logs_inativo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}