package com.kaskin.manager.presentation.home

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentHomeBinding
import com.kaskin.manager.domain.employee.entities.Employee
import com.kaskin.manager.presentation.user.login.states.LoggedInUserView
import com.kaskin.manager.utils.ConnectionChecker
import com.kaskin.manager.utils.Resource
import kotlinx.coroutines.launch

class HomeFragment() : Fragment() {


    private val homeViewModel by activityViewModels<HomeViewModel>()

    private lateinit var connectionChecker: ConnectionChecker

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupObservers()
        menuSetup()

        if (arguments != null) {
            // The getPrivacyPolicyLink() method will be created automatically.
            val args = requireArguments()

            val user: LoggedInUserView =
                args.getSerializable(getString(R.string.home_to_visit_args)/*, LoggedInUserView::class.java*/) as LoggedInUserView
            homeViewModel.updateUser(user)
        }

        UpdateConnectionState(root)

        return root
    }

    private fun menuSetup() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val navController =
                    requireActivity()
                        .findNavController(R.id.nav_host_fragment_content_manager_mobile)
                // Handle item selection
                return when (menuItem.itemId) {
                    R.id.mnuVendas -> {
                        val bundle = Bundle()
                        bundle.putSerializable(
                            getString(R.string.home_to_visit_args),
                            homeViewModel.user.value.data
                        ) // Serializable Object

                        navController?.navigate(R.id.action_nav_home_to_HomeNavigation, bundle)
                        return true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            homeViewModel.employee.collect { result ->
                when (result) {
                    is Resource.Success<Employee> -> {
                        if (result.data?.setor != 0) {
                            binding.databaseInfo
                                .text =
                                "${getString(R.string.setor_text)} - ${result.data?.setor} - ${result.data?.nome}"

                        } else {
                            binding.databaseInfo
                                .text =
                                getString(R.string.database_not_found)
                        }
                        binding.homePageLoadingBar?.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        Toast
                            .makeText(
                                requireContext(),
                                result.message,
                                Toast.LENGTH_LONG
                            )
                            .show()

                        binding.homePageLoadingBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.homePageLoadingBar?.visibility = View.VISIBLE
                    }
                }
            }

            homeViewModel.user.collect { result ->
                when (result) {
                    is Resource.Success<LoggedInUserView> -> {
                        result.data.let { logged ->
                            requireActivity()?.findViewById<TextView>(R.id.header_view_user)?.text =
                                logged?.displayName
                            requireActivity()?.findViewById<TextView>(R.id.header_view_code)?.text =
                                logged?.setor
                        }
                        binding.homePageLoadingBar?.visibility = View.GONE
                    }
                    is Resource.Error -> {
                        Toast
                            .makeText(
                                requireContext(),
                                result.message,
                                Toast.LENGTH_LONG
                            )
                            .show()
                        binding.homePageLoadingBar?.visibility = View.GONE
                    }
                    is Resource.Loading -> {
                        binding.homePageLoadingBar?.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
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
    }*/

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