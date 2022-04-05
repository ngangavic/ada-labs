package com.ngangavictor.adalabs.ui.add

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.ngangavictor.adalabs.R
import com.ngangavictor.adalabs.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    lateinit var binding: FragmentAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.inputDate.setOnFocusChangeListener { view, b ->

            if (view.isFocused ||b) {
                val datePicker =
                    MaterialDatePicker.Builder.datePicker()
                        .setTitleText("Select date")
                        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                        .build()
                datePicker.addOnPositiveButtonClickListener {
                    binding.inputDate.setText(datePicker.headerText.toString())
                }
                datePicker.show(parentFragmentManager, "")
            }
        }
        binding.inputTime.setOnFocusChangeListener { view, b ->
            if (view.isFocused ||b) {
            val picker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText("Select time")
                    .build()
            picker.addOnPositiveButtonClickListener {
                binding.inputTime.setText("""${picker.hour}:${picker.minute}""")
            }
            picker.show(parentFragmentManager, "")
        }
        }
    }
}