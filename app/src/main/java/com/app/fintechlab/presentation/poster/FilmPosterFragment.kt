package com.app.fintechlab.presentation.poster

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil.load
import com.app.fintechlab.FilmsApp
import com.app.fintechlab.databinding.FragmentFilmPosterBinding
import com.app.fintechlab.presentation.FilmsViewModel
import com.app.fintechlab.presentation.MainActivity
import com.app.fintechlab.presentation.entities.Status
import javax.inject.Inject

class FilmPosterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FilmsViewModel>({ activity as MainActivity }) { viewModelFactory }
    private var _binding: FragmentFilmPosterBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FilmsApp)
            .appComponent.filmPosterComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("cr", "onCreate")
        _binding = FragmentFilmPosterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.filmPoster.observe(viewLifecycleOwner) {
            binding.filmPoster.load(it.posterUrl.toUri().buildUpon().scheme("https").build())
            binding.filmName.text = it.name
            binding.filmDescription.text = it.description
            binding.filmGenres.text = it.genres
            binding.filmCountries.text = it.countries
        }
        viewModel.posterStatus.observe(viewLifecycleOwner) {
            if (it == Status.ERROR) {
                Toast.makeText(context, "Проверьте подключение к сети", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backButton.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearFilmPoster()
        _binding = null
    }

}