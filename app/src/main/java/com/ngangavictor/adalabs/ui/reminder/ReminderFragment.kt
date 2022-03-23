package com.ngangavictor.adalabs.ui.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ngangavictor.adalabs.R

class ReminderFragment : Fragment() {

    private lateinit var reminderViewModel: ReminderViewModel

    lateinit var textSlideshow:TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        reminderViewModel =
            ViewModelProvider(this).get(ReminderViewModel::class.java)

        return inflater.inflate(R.layout.fragment_reminder, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textSlideshow=view.findViewById(R.id.text_slideshow)

        reminderViewModel.text.observe(viewLifecycleOwner, Observer {
            textSlideshow.text = it
        })
    }
}