package com.ngangavictor.adalabs.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ngangavictor.adalabs.R
import com.ngangavictor.adalabs.adapters.NoteAdapter
import com.ngangavictor.adalabs.databinding.FragmentHomeBinding
import com.ngangavictor.adalabs.models.NoteModel


class HomeFragment : Fragment(), NoteAdapter.NoteItemClickListener {

    private lateinit var homeViewModel: HomeViewModel

    lateinit var textHome: TextView

    lateinit var binding: FragmentHomeBinding

    lateinit var noteAdapter: NoteAdapter

    lateinit var noteList: ArrayList<NoteModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        noteList = ArrayList()

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        textHome=view.findViewById(R.id.text_home)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textHome.text = it
        })

        noteList.add(NoteModel("Title", "Body"))
        noteList.add(NoteModel("Title", "Body"))
        noteList.add(NoteModel("Title", "Body"))
        noteList.add(NoteModel("Title", "Body"))
        noteList.add(NoteModel("Title", "Body"))

        Toast.makeText(requireContext(), noteList.size.toString(), Toast.LENGTH_LONG).show()
        binding.rvNotes.apply {
            noteAdapter = NoteAdapter(noteList, this@HomeFragment)
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = noteAdapter
        }
    }

    override fun itemClicked(view: View, noteModel: NoteModel) {
    }
}