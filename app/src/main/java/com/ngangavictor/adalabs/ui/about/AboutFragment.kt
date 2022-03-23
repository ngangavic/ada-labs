package com.ngangavictor.adalabs.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ngangavictor.adalabs.R

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel

    lateinit var textGallery:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
            ViewModelProvider(this).get(AboutViewModel::class.java)

        textGallery=container!!.findViewById(R.id.text_gallery)

        val textView: TextView = textGallery
        aboutViewModel.text.observe(viewLifecycleOwner, Observer {
            textGallery.text = it
        })
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}