package com.ngangavictor.adalabs.ui.reminder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.ngangavictor.adalabs.R
import com.ngangavictor.adalabs.databinding.CalendarDayLayoutBinding
import com.ngangavictor.adalabs.databinding.FragmentReminderBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.*

class ReminderFragment : Fragment() {

    private lateinit var reminderViewModel: ReminderViewModel

//    lateinit var calendarView:CalendarView

    lateinit var binding:FragmentReminderBinding

    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reminderViewModel =
            ViewModelProvider(this).get(ReminderViewModel::class.java)

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_reminder,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reminderViewModel.text.observe(viewLifecycleOwner, Observer {
//            textSlideshow.text = it
        })

        val daysOfWeek = daysOfWeekFromLocale()
        binding.legendLayout.legendLayout.children.forEachIndexed { index, view ->
            (view as TextView).apply {
                text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                    .uppercase(Locale.ENGLISH)
                view.setTextColor(resources.getColor(R.color.black))

            }
        }

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(10)
        val endMonth = currentMonth.plusMonths(10)
        binding.calendarView.setup(startMonth, endMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)

        class DayViewContainer(view: View) : ViewContainer(view) {
            val textView = view.findViewById<TextView>(R.id.calendarDayText)
            lateinit var day: CalendarDay // Will be set when this container is bound.
            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDates.contains(day.date)) {
                            selectedDates.remove(day.date)
                        } else {
                            selectedDates.add(day.date)
                        }
                        binding.calendarView.notifyDayChanged(day)
                    }
                }
            }
            }

        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            textView.setTextColor(requireActivity().resources.getColor(R.color.red))
//                            textView.setBackgroundResource(R.drawable.example_1_selected_bg)
                        }
                        today == day.date -> {
                            textView.setTextColor(requireActivity().resources.getColor(R.color.purpleDark))
//                            textView.setBackgroundResource(R.drawable.example_1_today_bg)
                        }
                        else -> {
                            textView.setTextColor(requireActivity().resources.getColor(R.color.black))
//                            textView.background = null
                        }
                    }
                } else {
                    textView.setTextColor(requireActivity().resources.getColor(R.color.black))
//                    textView.background = null
                }

            }
        }

        binding.calendarView.monthScrollListener = {
            if (binding.calendarView.maxRowCount == 6) {
                binding.tvMonth.text =
                    "${
                        it.yearMonth.month.name.lowercase(Locale.getDefault())
                            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }} ${it.year}"
            }
        }

    }

    private fun daysOfWeekFromLocale(): Array<DayOfWeek> {
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        var daysOfWeek = DayOfWeek.values()
        // Order `daysOfWeek` array so that firstDayOfWeek is at index 0.
        // Only necessary if firstDayOfWeek != DayOfWeek.MONDAY which has ordinal 0.
        if (firstDayOfWeek != DayOfWeek.MONDAY) {
            val rhs = daysOfWeek.sliceArray(firstDayOfWeek.ordinal..daysOfWeek.indices.last)
            val lhs = daysOfWeek.sliceArray(0 until firstDayOfWeek.ordinal)
            daysOfWeek = rhs + lhs
        }
        return daysOfWeek
    }
}