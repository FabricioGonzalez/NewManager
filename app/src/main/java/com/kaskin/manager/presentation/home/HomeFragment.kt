package com.kaskin.manager.presentation.home

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.room.Room
import com.kaskin.manager.Controllers.ConnectionChecker
import com.kaskin.manager.Models.LoggedInUserView
import com.kaskin.manager.R
import com.kaskin.manager.data.database.employee.EmployeeDatabase
import com.kaskin.manager.databinding.FragmentHomeBinding
import com.kaskin.manager.presentation.home.viewmodels.HomeViewModel

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
        super.onCreateView(inflater, container, savedInstanceState)
        setHasOptionsMenu(true);

        val homeViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (arguments != null) {
            // The getPrivacyPolicyLink() method will be created automatically.
            val user: LoggedInUserView =
                requireArguments().getSerializable("USER") as LoggedInUserView
            homeViewModel.UpdateUser(user)
        }

        var context = requireContext()

        val db = Room.databaseBuilder(
            context,
            EmployeeDatabase::class.java,
            "manager.db"
        ).build()

        homeViewModel.GetEmployeeData(
            db
        )

        homeViewModel.employee.observe(
            viewLifecycleOwner
        ) {
            root.findViewById<TextView>(R.id.database_info).text = it.nome
        }

        val header = activity?.findViewById<TextView>(R.id.header_view_user)
        val item = activity?.findViewById<TextView>(R.id.header_view_code)

        header?.text = homeViewModel.user.value?.displayName
        item?.text = homeViewModel.user.value?.setor

        UpdateConnectionState(root)
/*        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        return root
    }

    fun GetDatabaseData() {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController =
            requireActivity().findNavController(R.id.nav_host_fragment_content_manager_mobile)
        // Handle item selection
        return when (item.itemId) {
            R.id.mnuVendas -> {
                navController.navigate(R.id.action_nav_home_to_HomeNavigation)
                return true
            }
            else -> false
        }
    }

    private fun UpdateConnectionState(root: View) {
        connectionChecker = ConnectionChecker(root.context)

        val connectionState = root.findViewById<ImageView>(R.id.connection_state)
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