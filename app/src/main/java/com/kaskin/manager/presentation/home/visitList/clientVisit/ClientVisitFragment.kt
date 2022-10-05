package com.kaskin.manager.presentation.home.visitList.clientVisit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.kaskin.manager.databinding.FragmentClientVisitBinding

class ClientVisitFragment : Fragment() {

    private val clientVisitViewModel by activityViewModels<ClientVisitViewModel>()

    private var _binding: FragmentClientVisitBinding? = null

    private val binding get() = _binding!!

    var day: String = "1"

    fun SetDay(day: String) {
        this.day = day
    }

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

        clientVisitViewModel.UpdateDay(day)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}