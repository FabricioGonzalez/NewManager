package com.kaskin.manager.presentation.home.visitList.clientVisit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kaskin.manager.databinding.FragmentClientVisitBinding
import com.kaskin.manager.presentation.adapters.Updatable
import javax.inject.Inject

class ClientVisitFragment(
    private val day: Int,
    private val setor: Int,
) : Fragment(), Updatable {

    private var _binding: FragmentClientVisitBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var myViewModelAssistedFactory: ClientVisitViewModel.Factory

    // Initialize the ViewModel using ViewModelProvider.Factory
    private val clientVisitViewModel: ClientVisitViewModel by viewModels {
        ClientVisitViewModel.provideFactory(
            myViewModelAssistedFactory, day
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientVisitBinding.inflate(inflater, container, false)
        val root: View = binding.root

/*
        val factory = EntryPointAccessors.fromActivity(
            requireActivity() as Activity,
            ManagerMobile.ViewModelFactoryProvider::class.java
        ).clientVisitViewModel()
*/

        clientVisitViewModel.changeDay()

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
        if (clientVisitViewModel != null) {
            clientVisitViewModel.getClients(setor)
        }
    }
}