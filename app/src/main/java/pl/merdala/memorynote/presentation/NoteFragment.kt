package pl.merdala.memorynote.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

    private lateinit var noteViewModel: NoteViewModel
    private val currentNote = Note("", "", 0, 0)

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNoteBinding {
        return FragmentNoteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        binding.checkButton.setOnClickListener {
            if (isDataComplete()) {
                putDataIntoCurrentNote()
                noteViewModel.saveNote(currentNote)
            } else {
                navigateBackAndHideKeyboard(it)
            }
        }
        observeViewModel()
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
        noteViewModel.saved.observe(viewLifecycleOwner, {
            if (it) {
                showToast(R.string.done)
                navigateBackAndHideKeyboard(binding.titleView)
            } else {
                showToast(R.string.note_save_somethig_went_wrong)
            }
        })
    }

    private fun showToast(@StringRes resId: Int) {
        Toast.makeText(context, getString(resId), Toast.LENGTH_SHORT).show()
    }

    private fun putDataIntoCurrentNote() {
        val time: Long = System.currentTimeMillis()
        currentNote.title = binding.titleView.text.toString()
        currentNote.content = binding.contentView.text.toString()
        currentNote.updateDate = time
        if (currentNote.id == 0L) {
            currentNote.creationDate = time
        }
    }

    private fun isDataComplete(): Boolean {
        return !"".equals(binding.titleView.text.toString().trim())
                || !"".equals(binding.contentView.text.toString().trim())
    }
}