package com.kaskin.manager.presentation.home.visitList.clientVisit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.Views.Adapters.Updatable
import com.kaskin.manager.databinding.FragmentClientVisitBinding

class ClientVisitFragment(
    private val day: Int,
) : Fragment(), Updatable {

    // Creating viewmodel here with
    // the help of kotlin delegate property "by"
/*
    private val clientVisitViewModel: ClientVisitViewModel by activityViewModels(
        factoryProducer = { ViewModelProvider.NewInstanceFactory() }
    )
*/

    private var _binding: FragmentClientVisitBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientVisitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val clientVisitViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[ClientVisitViewModel::class.java]

        clientVisitViewModel.changeDay(day = day)

        val textView: TextView = binding.texto
        clientVisitViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun Update() {

    }
}