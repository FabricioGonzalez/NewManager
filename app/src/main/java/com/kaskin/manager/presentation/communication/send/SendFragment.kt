package br.com.kaskin.presentation.communication.ui.send

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.kaskin.manager.databinding.FragmentCommunicationSendBinding

class SendFragment : Fragment() {

    private var _binding: FragmentCommunicationSendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
   /* private lateinit var connection: Connection*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

       /* connection = Connection(requireContext())

        val sendViewModel by viewModels<SendViewModel> {
            ViewModelFactory(
                PedidosDatabaseRepository(connection)
            )
        }
*/
        _binding = FragmentCommunicationSendBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val sendPedidoButton: Button = binding.sendPedido

        sendPedidoButton.setOnClickListener {
        /*    sendViewModel.sendPedidos()*/
        }

        /* sendViewModel.text.observe(viewLifecycleOwner) {
             textView.text = it
         }*/
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}