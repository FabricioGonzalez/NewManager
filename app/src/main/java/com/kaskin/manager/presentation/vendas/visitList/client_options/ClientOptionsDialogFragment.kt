package com.kaskin.manager.presentation.vendas.visitList.client_options

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentClientOptionsBinding

class ClientOptionsDialogFragment(private val codigo: Int) : DialogFragment() {

    private var _binding: FragmentClientOptionsBinding? = null

    private val binding get() = _binding!!

    companion object {
        fun newInstance(codigo: Int) = ClientOptionsDialogFragment(codigo)
    }

    private lateinit var viewModel: ClientOptionsViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        return requireActivity().let {

            _binding = FragmentClientOptionsBinding.inflate(requireActivity().layoutInflater)


            binding.btnVenda.setOnClickListener {
                val navController =
                    requireActivity().findNavController(R.id.nav_host_fragment_content_manager_mobile)
                val bundle = Bundle()
                bundle.putInt(
                    getString(R.string.visit_to_pedidos_args),
                    codigo
                ) // Serializable Object

                navController.navigate(R.id.action_visitListFragment_to_pedidosFragment, bundle)

                this.dialog?.cancel()

            }

            // Use the Builder class for convenient dialog construction
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            AlertDialog.Builder(it)
                .setView(binding.root.rootView)
                .setMessage(R.string.dialog_client_options)
                /*.setPositiveButton(R.string.start,
                    DialogInterface.OnClickListener { dialog, id ->
                        // START THE GAME!
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })*/
                // Create the AlertDialog object and return it
                .create()
        }
    }
}
