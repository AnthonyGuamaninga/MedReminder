package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentMedListBinding
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG
import com.grupo4.recordatoriosmedicamentos.logic.usercases.fda.FdaGetResultDrugsUserCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.DrugsAdapterDiffUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MedListFragment : Fragment() {
    private lateinit var binding: FragmentMedListBinding
    private var usersList : MutableList<FullInfoDrugsLG> = ArrayList()
    private var userDiffAdapter = DrugsAdapterDiffUtil( {selectDrug(it)})
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=
        FragmentMedListBinding.bind(inflater.inflate(R.layout.fragment_med_list, container, false))
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
            binding.animationView.visibility = View.VISIBLE

            val resp = withContext(Dispatchers.IO){
                FdaGetResultDrugsUserCase().invoke()
            }
            resp.onSuccess {listDrug ->
                //usersList.addAll(listAnime)
                //insertUsersDiff(usersList)
                insertDrugsDiff(listDrug)
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

            binding.animationView.visibility = View.GONE
        }
    }

    private fun insertDrugsDiff(drugs: List<FullInfoDrugsLG>){
        usersList.addAll(drugs)
        userDiffAdapter.submitList(usersList.toList())

    }

    private fun selectDrug(drug: FullInfoDrugsLG){
        findNavController()
            .navigate(
                MedListFragmentDirections.actionMedListFragmentToDetailFragment2(idDrug = drug.spl_id)
            )
    }

}