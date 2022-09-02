package com.kaskin.manager.Views.Activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.kaskin.manager.R
import com.kaskin.manager.databinding.ActivityManagerMobileBinding


class ManagerMobile : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityManagerMobileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManagerMobileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarManagerMobile.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_manager_mobile)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_client_registration,
                R.id.nav_supervisor,
                R.id.nav_communication,
                R.id.nav_database,
                R.id.nav_settings
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.manager_mobile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_manager_mobile)
        // Handle item selection
        return when (item.itemId) {
            R.id.mnuVendas -> {
                navController.navigate(R.id.action_nav_home_to_HomeNavigation)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_manager_mobile)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}