package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentNuevaRecetaBinding
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta.MedicamentosUseCase
import com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user.GetUserByIdUserCase
import com.grupo4.recordatoriosmedicamentos.ui.adapter.MedicamentoAdapter
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.RecetaRegistroViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevaRecetaFragment : Fragment() {

    private lateinit var binding: FragmentNuevaRecetaBinding
    private val recetaVM : RecetaRegistroViewModel by viewModels()
    private val args : NuevaRecetaFragmentArgs by navArgs()
    private var recetaAdapter = MedicamentoAdapter()
    private var recetaList: MutableList<MedInfo> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNuevaRecetaBinding.bind(
            inflater.inflate(
                R.layout.fragment_nueva_receta,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inintListeners()
        initRecyclerView()
    }

    private fun inintListeners() {
        binding.buttonSave.setOnClickListener {
            var user = recetaVM.getUser(args.userInfo)
            if (user != null) {
                recetaVM.nuevaReceta("User1Receta",true,null,user.id,"")
            }
        }

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(
                NuevaRecetaFragmentDirections.actionNuevaRecetaFragmentToNavigation3(userInfo = args.userInfo)
            )
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
            var rec = MedicamentosUseCase().getAll()
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