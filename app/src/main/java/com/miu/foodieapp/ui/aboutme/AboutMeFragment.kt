package com.miu.foodieapp.ui.aboutme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.miu.foodieapp.R
import com.miu.foodieapp.databinding.FragmentAboutMeBinding

class AboutMeFragment : Fragment() {

    private lateinit var binding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutMeBinding.inflate(layoutInflater)

        val aboutMeContent = """ Hey, Welcome to the flavorful journey curated by Md Shahab Uddin, your go-to food enthusiast and blogger! As a culinary explorer, I embark on a delectable adventure to unravel the world of gastronomy, one bite at a time. My passion for food is not merely a journey through taste; it's an odyssey through culture, 
            |tradition, and the sheer joy that comes from savoring a well-crafted dish. """.trimMargin()

        binding.aboutMeContent.text = aboutMeContent

        return binding.root
    }
}