package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentRecetasBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.MedicamentosUseCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.MedicamentoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecetasFragment : Fragment() {
    private lateinit var binding: FragmentRecetasBinding
    private var recetaList: MutableList<MedInfo> = ArrayList()
    private var recetaAdapter = MedicamentoAdapter()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=
            FragmentRecetasBinding.bind(inflater.inflate(R.layout.fragment_recetas, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        auth = Firebase.auth
    }

    private fun initRecyclerView(){
        binding.rvUsers.adapter = recetaAdapter
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
            var rec = MedicamentosUseCase().getAllId(auth.currentUser?.uid.toString())
            if (rec != null) {
                insertReceta(rec)
            }else{
                Log.d("uce", rec?.toString().toString())
            }
        }
    }

    private fun insertReceta(medInfo: List<MedInfo>){
        recetaList.addAll(medInfo)
        recetaAdapter.submitList(recetaList.toList())

    }
}