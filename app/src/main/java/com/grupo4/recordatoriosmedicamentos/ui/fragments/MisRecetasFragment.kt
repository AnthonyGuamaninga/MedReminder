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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentMisRecetasBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.RecetaUseCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.RecetaAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MisRecetasFragment : Fragment() {
    private lateinit var binding: FragmentMisRecetasBinding
    private var recetaList: MutableList<Receta> = ArrayList()
    private var recetaAdapter = RecetaAdapter()
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMisRecetasBinding.bind(inflater.inflate(R.layout.fragment_mis_recetas, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        initRecyclerView()
        initlisteners()
    }

    private fun initlisteners() {
        binding.button.setOnClickListener {
            var user = auth.currentUser?.uid
            if (user!=null){
                findNavController().navigate(MisRecetasFragmentDirections.actionMisRecetasFragmentToNuevaRecetaFragment(userInfo = user.toString()))
            }else{
                Snackbar.make(requireContext(),it,"Error al cargar los datos del ususario", Snackbar.LENGTH_LONG).show()
            }

        }
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
            var rec = RecetaUseCase().getAll()
            if (rec != null) {
                insertReceta(rec)
            }else{
                Log.d("uce", rec?.toString().toString())
            }
        }
    }

    private fun insertReceta(receta: List<Receta>){
        recetaList.addAll(receta)
        recetaAdapter.submitList(recetaList.toList())

    }

}