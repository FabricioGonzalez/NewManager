package com.kaskin.manager.Views.Activities.ui.home.visitList.clientVisit

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kaskin.manager.databinding.FragmentClientVisitBinding

class ClientVisitFragment : Fragment() {

    private var _binding: FragmentClientVisitBinding? = null

    private val binding get() = _binding!!

    var day: String = "1"

    fun SetDay(day: String): ClientVisitFragment {
        this.day = day
        return this
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val clientVisitViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(ClientVisitViewModel::class.java)

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