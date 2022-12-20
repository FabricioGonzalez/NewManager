package br.com.kaskin.presentation.communication.ui.recieve

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kaskin.manager.databinding.FragmentCommunicationRecieveBinding

class RecieveFragment : Fragment() {

    private var _binding: FragmentCommunicationRecieveBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val recieveViewModel =
            ViewModelProvider(this)[RecieveViewModel::class.java]

        _binding = FragmentCommunicationRecieveBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recievePedidoButton: Button = binding.recievePedido

        recievePedidoButton.setOnClickListener {
            recieveViewModel.recieveCarga()
        }
        /*recieveViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}