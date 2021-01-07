package pl.merdala.memorynote.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import pl.merdala.memorynote.databinding.FragmentListBinding
import pl.merdala.memorynote.framework.ListViewModel

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : AbstractBindingFragment<FragmentListBinding>() {

    private lateinit var listViewModel: ListViewModel

    private var notesListAdapter = NotesListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }
        binding.addNote.setOnClickListener { goToNoteDetails() }
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        observeViewModel()
    }

    private fun observeViewModel() {
        listViewModel.notes.observe(viewLifecycleOwner, {
            notesListAdapter.updateNotes(it.sortedByDescending { it.updateDate })
            binding.loadingProgressBar.visibility = View.GONE
        })
    }

    override fun onResume() {
        super.onResume()
        listViewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0) {
        val action: NavDirections = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(binding.notesListView).navigate(action)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }
}