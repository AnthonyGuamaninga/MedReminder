package com.grupo4.recordatoriosmedicamentos.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.grupo4.recordatoriosmedicamentos.R
import com.grupo4.recordatoriosmedicamentos.databinding.ActivityVistaprincipalBinding

class VistaPrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVistaprincipalBinding
    lateinit var  actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVistaprincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = findViewById(binding.myDrawerPrincipal.id)

        actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout, R.string.nav_open, R.string.nav_close)
        // add a drawer listener into  drawer layout
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // show menu icon and back icon while drawer open
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        auth = Firebase.auth
        initListeners()
    }

    private fun initListeners() {
        binding.btnMedicamentos.setOnClickListener {
            findNavController(binding.navHostFragment.id).navigate(R.id.medListFragment)
        }
        binding.btmMapas.setOnClickListener {
            findNavController(binding.navHostFragment.id).navigate(R.id.mapsFragment)
        }
        binding.btnHorario.setOnClickListener {
            findNavController(binding.navHostFragment.id).navigate(R.id.recetasFragment)
        }
        binding.btnRegistroReceta.setOnClickListener {
            findNavController(binding.navHostFragment.id).navigate(R.id.misRecetasFragment)
        }
        binding.imgMenu.setOnClickListener{
            binding.myDrawerPrincipal.openDrawer(GravityCompat.START)
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.it_home ->{
                    findNavController(binding.navHostFragment.id).navigate(R.id.recetasFragment)
                    binding.myDrawerPrincipal.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.it_medicamento ->{
                    findNavController(binding.navHostFragment.id).navigate(R.id.medListFragment)
                    binding.myDrawerPrincipal.closeDrawer(GravityCompat.START)
                    true
                }
                R.id.it_cerar_sesion ->{
                    auth.signOut()
                    startActivity(Intent(this,LoginActivity::class.java))
                    true
                }
                else -> {false}
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // check conndition for drawer item with menu item
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)){
            true
        }else{
            super.onOptionsItemSelected(item)
        }

    }

}