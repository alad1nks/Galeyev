package com.app.fintechlab.presentation.topfilms

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fintechlab.FilmsApp
import com.app.fintechlab.R
import com.app.fintechlab.databinding.RecyclerFilmsBinding
import com.app.fintechlab.presentation.FilmCardsAdapter
import com.app.fintechlab.presentation.FilmsViewModel
import com.app.fintechlab.presentation.MainActivity
import com.app.fintechlab.presentation.entities.Status
import javax.inject.Inject

class FilmsTopFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<FilmsViewModel>({ activity as MainActivity }) { viewModelFactory }
    private var _binding: RecyclerFilmsBinding? = null
    private val binding get() = _binding!!

    private val filmCardAdapter = FilmCardsAdapter(
        onItemClicked = {
            viewModel.refreshFilmPoster(it.id.toString())
            this.findNavController().navigate(R.id.action_filmsFragment_to_filmPosterFragment,
                null,
                null,
                null
            )
        },
        onItemLongClicked = {
            viewModel.addFavouriteFilm(it.id)
        }
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as FilmsApp)
            .appComponent.topFilmsComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerFilmsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFilms.adapter = filmCardAdapter
        binding.recyclerFilms.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        viewModel.filmCardsTop.observe(viewLifecycleOwner) {
            filmCardAdapter.submitList(it)
        }
        viewModel.status.observe(viewLifecycleOwner) {
            bind(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bind(status: Status) {
        when(status) {
            Status.DONE -> {
                binding.progressBarFilms.visibility = GONE
            }
            Status.LOADING -> {
                binding.progressBarFilms.visibility = VISIBLE
            }

            else -> {}
        }
    }
}