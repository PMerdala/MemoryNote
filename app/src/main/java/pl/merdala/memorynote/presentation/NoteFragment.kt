package pl.merdala.memorynote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import pl.merdala.memorynote.databinding.FragmentNoteBinding

/**
 * A simple [Fragment] subclass.
 */
class NoteFragment : AbstractBindingFragment<FragmentNoteBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNoteBinding {
        return FragmentNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkButton.setOnClickListener { Navigation.findNavController(it).popBackStack() }
    }
}