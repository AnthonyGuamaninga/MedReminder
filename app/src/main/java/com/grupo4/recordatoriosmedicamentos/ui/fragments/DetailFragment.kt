package com.grupo4.recordatoriosmedicamentos.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.FragmentDetailBinding
import com.grupo4.recordatoriosmedicamentos.ui.viewModels.DetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private val detailVM : DetailViewModel by viewModels ()
    private lateinit var binding: FragmentDetailBinding
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.bind(inflater.inflate(R.layout.fragment_detail, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        detailVM.loadInfoDrug(args.idDrug)
    }


    private fun initObservers(){
        detailVM.drug.observe(requireActivity()){drug ->
            binding.idDrug.text = drug.spl_id
            binding.txtBrandName.text = drug.brand_name
            binding.txtGenericName.text = drug.generic_name
            binding.txtManufacturer.text = drug.manufacturer_name
            binding.txtRoute.text = drug.route

            when(drug.route){
                "ORAL" -> binding.imgDrug.load(R.drawable.m_oral)
                "INJECTION" -> binding.imgDrug.load(R.drawable.m_injection)
                "INTRAVENOUS" -> binding.imgDrug.load(R.drawable.m_intravenous)
                "SUBCUTANEOUS" -> binding.imgDrug.load(R.drawable.m_subcutaneous)
                "OPHTHALMIC" -> binding.imgDrug.load(R.drawable.m_ophthalmic)
                "TOPICAL" -> binding.imgDrug.load(R.drawable.m_topical)
                "INTRAMUSCULAR" -> binding.imgDrug.load(R.drawable.m_intramuscular)
                else -> binding.imgDrug.load(R.drawable.logo_ma)
            }

            // demas binding

        }

        detailVM.error.observe(requireActivity()){errorMessage->
            Snackbar
                .make(requireActivity(), binding.btnAgregar, errorMessage.toString(), Snackbar.LENGTH_LONG)
                .show()

        }
    }

}