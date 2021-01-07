package pl.merdala.memorynote.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import pl.merdala.core.data.Note
import pl.merdala.memorynote.R
import pl.merdala.memorynote.databinding.FragmentNoteBinding
import pl.merdala.memorynote.framework.NoteViewModel

/**
 * A simple [Fragment] subclass.
 */
class NoteFragment : AbstractBindingFragment<FragmentNoteBinding>() {

    private var noteId = 0L
    private lateinit var noteViewModel: NoteViewModel
    private var currentNote = Note("", "", 0, 0)

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNoteBinding {
        return FragmentNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareNoteViewModel()
        loadArgumentsToVariables()
        loadNoteIfPresent()
        prepareCheckButtonListener()
        observeViewModel()
    }

    private fun loadNoteIfPresent() {
        if (noteId != 0L) {
            noteViewModel.getNote(noteId)
        }
    }

    private fun prepareCheckButtonListener() {
        binding.checkButton.setOnClickListener {
            if (isDataComplete()) {
                putDataIntoCurrentNoteFromView()
                noteViewModel.saveNote(currentNote)
            } else {
                navigateBackAndHideKeyboard(it)
            }
        }
    }

    private fun prepareNoteViewModel() {
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
    }

    private fun loadArgumentsToVariables() {
        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }
    }

    private fun navigateBackAndHideKeyboard(view: View) {
        hideKeyboard()
        navigateBack(view)
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.titleView.windowToken, 0)
    }

    private fun navigateBack(view: View) {
        Navigation.findNavController(view).popBackStack()
    }

    private fun observeViewModel() {
        observeSavedViewModel()
        observeCurrentNoteViewModel()
    }

    private fun observeSavedViewModel() {
        noteViewModel.saved.observe(viewLifecycleOwner, {
            if (it) {
                showToast(R.string.done)
                navigateBackAndHideKeyboard(binding.titleView)
            } else {
                showToast(R.string.note_save_somethig_went_wrong)
            }
        })
    }

    private fun observeCurrentNoteViewModel() {
        noteViewModel.currentNote.observe(viewLifecycleOwner, {
            it?.let {
                putDateIntoCurrentNoteFromNote(it)
                fillViewFromCurrentNote()
            }
        })
    }

    private fun fillViewFromCurrentNote() {
        binding.titleView.setText(currentNote.title, TextView.BufferType.EDITABLE)
        binding.contentView.setText(currentNote.content, TextView.BufferType.EDITABLE)
    }

    private fun putDateIntoCurrentNoteFromNote(note: Note) {
        currentNote = note
    }

    private fun showToast(@StringRes resId: Int) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show()
    }

    private fun putDataIntoCurrentNoteFromView() {
        val time: Long = System.currentTimeMillis()
        currentNote.title = binding.titleView.text.toString()
        currentNote.content = binding.contentView.text.toString()
        currentNote.updateDate = time
        if (currentNote.id == 0L) {
            currentNote.creationDate = time
        }
    }

    private fun isDataComplete(): Boolean {
        return "" != binding.titleView.text.toString().trim()
                || "" != binding.contentView.text.toString().trim()
    }
}