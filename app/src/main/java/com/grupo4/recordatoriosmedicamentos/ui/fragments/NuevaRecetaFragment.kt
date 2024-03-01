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
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentNuevaRecetaBinding
import com.grupo4.recordatoriosmedicamentos.logic.entities.RecetaSingleton
import com.grupo4.recordatoriosmedicamentos.ui.adapter.MedicamentoAdapter
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.RecetaRegistroViewModel
import com.squareup.okhttp.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import kotlin.math.log

class NuevaRecetaFragment : Fragment() {

    private lateinit var binding: FragmentNuevaRecetaBinding
    private val recetaVM : RecetaRegistroViewModel by viewModels()
    private val args : NuevaRecetaFragmentArgs by navArgs()
    private var recetaAdapter = MedicamentoAdapter()
    private val recetaSingleton= RecetaSingleton.instance
    private val fecha= LocalDateTime.now()

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
            lifecycleScope.launch(Dispatchers.IO){
                val size= recetaVM.getUser(args.userInfo)?.recetasId?.size
                var idReceta:String
                if(size==null){
                    idReceta=args.userInfo+"-0"
                }else{
                    idReceta=args.userInfo+"-"+size
                }
                recetaVM.nuevaReceta(idReceta,true,recetaVM.obtenerListIdMed(recetaSingleton.listaMed),args.userInfo,fecha.toString())
                val temp=recetaVM.getUser(args.userInfo)
                if(temp!=null){
                    Log.d("User","temp no es null")
                    val lista : MutableList<String> = ArrayList()
                    lista.addAll(temp.recetasId!!)
                    lista.add(idReceta)
                    val user = UserDB(temp.id,temp.email,temp.name,temp.lastname,temp.edad,lista.toList())
                    recetaVM.updateUser(user)
                }
            }

            findNavController().navigate(
                NuevaRecetaFragmentDirections.actionNuevaRecetaFragmentToMisRecetasFragment(userInfo = "")
            )
        }

        binding.buttonAdd.setOnClickListener {

            recetaSingleton.temporal.id=args.userInfo.toString()+"-x"
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
        insertReceta(recetaSingleton.listaMed.toList())
    }

    private fun insertReceta(medInfo: List<MedInfo>){
        recetaAdapter.submitList(medInfo)
    }

}