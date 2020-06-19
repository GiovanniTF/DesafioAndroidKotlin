package br.com.giovanni.desafioandroidkotlinapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navHost: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.main_fragment_id) as NavHostFragment
    }
    private val navController get() = navHost.navController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_id)

        appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar_id.setupWithNavController(navController, appBarConfiguration)
    }
}

