package pl.merdala.memorynote.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import pl.merdala.memorynote.databinding.FragmentListBinding

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : AbstractBindingFragment<FragmentListBinding>() {


    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addNote.setOnClickListener { goToNoteDetails() }
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