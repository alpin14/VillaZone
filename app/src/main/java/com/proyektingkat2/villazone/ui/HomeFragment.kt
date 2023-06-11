package com.proyektingkat2.villazone.ui

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.proyektingkat2.villazone.R
import com.proyektingkat2.villazone.adapter.ImageSliderAdapter
import com.proyektingkat2.villazone.databinding.FragmentHomeBinding
import com.proyektingkat2.villazone.model.ImageData


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            var index = 0
            override fun run() {
                if (index == list.size)
                    index = 0
                Log.e("Runnable,", "$index")
                binding.viewPager.currentItem = index
                index++
                handler.postDelayed(this, 2100)
            }
        }

        list.add(
            ImageData(
                "https://4.bp.blogspot.com/_V28uUqMfp68/S3d-j8D33vI/AAAAAAAAAA4/H6bvSA92akM/w1200-h630-p-k-no-nu/VILLAZONE.JPG"
            )
        )
        list.add(
            ImageData(
                "https://assets.ayobandung.com/crop/0x0:0x0/750x500/webp/photo/2022/10/12/3479783477.jpg"
            )
        )
        list.add(
            ImageData(
                "https://cdn-2.tstatic.net/kaltim/foto/bank/images/ilustrasi-kost-kostan_20180315_102338.jpg"
            )
        )
        list.add(
            ImageData(
                "https://blog.sewakost.com/wp-content/uploads/2015/03/Cara-Mendesain-Kamar-Mandi-Kosan.jpg"
            )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size)
            if (i == position)
                dots[i].setTextColor(
                    ContextCompat.getColor(requireActivity(),
                    R.color.design_default_color_primary
                ))
            else
                dots[i].setTextColor(
                    ContextCompat.getColor(requireActivity(),
                    R.color.design_default_color_secondary
                ))

    }

    private fun setIndicator() {
        for (i in 0 until list.size){
            dots.add(TextView(requireActivity()))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }

    override fun onStart() {
        super.onStart()
        handler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
}