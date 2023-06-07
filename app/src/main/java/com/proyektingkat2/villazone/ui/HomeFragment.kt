package com.proyektingkat2.villazone.ui

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.proyektingkat2.villazone.ImageSliderAdapter
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.databinding.FragmentHomeBinding
import com.proyektingkat2.villazone.model.ImageData


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}