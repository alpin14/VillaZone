package com.proyektingkat2.villazone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyektingkat2.villazone.databinding.ActivityMainBinding
import com.proyektingkat2.villazone.ui.GroupFragment
import com.proyektingkat2.villazone.ui.HomeFragment
import com.proyektingkat2.villazone.ui.ProfileFragment
import com.proyektingkat2.villazone.ui.TagihanFragment

class MainActivity : AppCompatActivity() {
    val fragmentHome : Fragment = HomeFragment()
    val fragmentGroup : Fragment = GroupFragment()
    val fragmentTagihan : Fragment = TagihanFragment()
    val fragmentProfile : Fragment = ProfileFragment()
    val fm : FragmentManager = supportFragmentManager
    var active : Fragment = fragmentHome

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buttonNavigation()
    }

    private fun buttonNavigation() {
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentGroup).hide(fragmentGroup).commit()
        fm.beginTransaction().add(R.id.container, fragmentTagihan).hide(fragmentTagihan).commit()
        fm.beginTransaction().add(R.id.container, fragmentProfile).hide(fragmentProfile).commit()

        bottomNavigationView = binding.navView
        menu = bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_group -> {
                    callFragment(1, fragmentGroup)
                }
                R.id.navigation_tagihan -> {
                    callFragment(2, fragmentTagihan)
                }
                R.id.navigation_profile -> {
                    callFragment(3, fragmentProfile)
                }
            }
            false
        }
    }

    private fun callFragment(index : Int, fragment: Fragment) {
        menuItem = menu.getItem(index)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}