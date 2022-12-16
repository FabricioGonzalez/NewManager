package com.kaskin.manager.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kaskin.manager.R
import com.kaskin.manager.databinding.FragmentTesteBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [teste.newInstance] factory method to
 * create an instance of this fragment.
 */
class teste : Fragment() {

    private var _binding: FragmentTesteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentTesteBinding.inflate(inflater, container, false)

        val navHostFragment = binding.fragmentContainerView2
        val navController = findNavController()
        /*val navController = binding.fragmentContainerView2.findNavController()*/

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_blank,
                R.id.nav_blank2
            )
        )

        (requireActivity() as AppCompatActivity).setupActionBarWithNavController(
            navController = navController,
            appBarConfiguration
        )

        binding.bottonNav.setupWithNavController(navController!!)

        val root = binding.root


        return root
    }

}