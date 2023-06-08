package com.proyektingkat2.villazone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyektingkat2.villazone.databinding.ActivityMainBinding
import com.proyektingkat2.villazone.db.AppDatabase
import com.proyektingkat2.villazone.repository.PenghuniRepository
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModel
import com.proyektingkat2.villazone.ui.penghuni.daftarpenghuni.DaftarPenghuniViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    lateinit var daftarPenghuniViewModel: DaftarPenghuniViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpViewModel()
        setUpNavigation()
    }

    private fun setUpViewModel() {
        val noteRepository = PenghuniRepository(
            AppDatabase(this)
        )

        val viewModelProviderFactory =
            DaftarPenghuniViewModelFactory(
                application, noteRepository
            )

        daftarPenghuniViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        ).get(DaftarPenghuniViewModel::class.java)
    }

    private fun setUpNavigation() {
        val navView: BottomNavigationView = binding.navView
        navController = findNavController(R.id.fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.daftarPenghuniFragment, R.id.tagihanFragment, R.id.profileFragment
            )
        )
        NavigationUI.setupActionBarWithNavController(this, navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}

