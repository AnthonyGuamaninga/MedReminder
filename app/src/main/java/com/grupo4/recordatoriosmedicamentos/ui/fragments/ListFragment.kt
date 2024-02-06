package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentListBinding
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG
import com.grupo4.recordatoriosmedicamentos.logic.usercases.fda.FdaGetResultDrugsUserCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.DrugsAdapterDiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var usersList : MutableList<FullInfoDrugsLG> = ArrayList()
    private var userDiffAdapter = DrugsAdapterDiffUtil( {selectDrug(it)})

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
    }


    private fun initRecyclerView(){
        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager=
            LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.VERTICAL,
                false
            )
        loadDataRecyclerView()

    }

    private fun loadDataRecyclerView(){
        lifecycleScope.launch (Dispatchers.Main) {
            binding.progresBar.visibility = View.VISIBLE

            val resp = withContext(Dispatchers.IO){
                FdaGetResultDrugsUserCase().invoke()
            }
            resp.onSuccess {listAnime ->
                //usersList.addAll(listAnime)
                //insertUsersDiff(usersList)
                insertUsersDiff(listAnime)
            }
            resp.onFailure {ex ->
                Snackbar.make(
                    requireActivity(),
                    binding.rvUsers,
                    ex.message.toString(),
                    Snackbar.LENGTH_LONG
                )
                    .show()
            }

            binding.progresBar.visibility = View.GONE
        }
    }

    private fun insertUsersDiff(animes: List<FullInfoDrugsLG>){
        usersList.addAll(animes)
        userDiffAdapter.submitList(usersList.toList())

    }

    private fun selectDrug(drug: FullInfoDrugsLG){
        findNavController()
            .navigate(
                ListFragmentDirections.actionListFragmentToDetailFragment(idDrug = drug.spl_id)
            )
    }


}