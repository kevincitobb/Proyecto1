package com.kevs.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.kevs.proyecto1.databinding.ActivityResultBinding

class Result : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        val res = bundle?.getDoubleArray("res")
        val formula = bundle?.getString("formula")
        val spinner = resources.getStringArray(R.array.formulas_array)
        var arrayInputs = arrayOf(binding.tvR,binding.tvV)

        when(formula){
            spinner[1].toString() ->{
                binding.tvcmr.setText(R.string.cm3)
                binding.ivR.setImageResource(R.drawable.prisma)
            }
            spinner[2].toString() ->{
                binding.tvcmr.setText(R.string.cm3)
                binding.ivR.setImageResource(R.drawable.cilindro)
            }
            spinner[3].toString() ->{
                binding.tvcmr.setText(R.string.cm3)
                binding.ivR.setImageResource(R.drawable.cono)
            }
            spinner[4].toString() ->{
                binding.tvV.setText(R.string.Area)
                binding.tvcmr.setText(R.string.cm2)
                binding.ivR.setImageResource(R.drawable.trapezoide)
            }
            spinner[5].toString() ->{
                binding.tvV.setText(R.string.Area)
                binding.tvV.setText(R.string.cm2)
                binding.ivR.setImageResource(R.drawable.rectangulo)
            }
            spinner[6].toString() ->{
                binding.tvV.setText(R.string.Area)
                binding.tvcmr.setText(R.string.cm2)
                binding.ivR.setImageResource(R.drawable.triangulo)
            }
            else ->{
                binding.tvcmr.setText(R.string.cm3)
            }
        }
        if (res != null) {
            for (i in 0 .. res.size-1){
                arrayInputs[i].text = res[i].toString()
            }
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_toolbar, menu)
        if(menu!=null){
            val item = menu.findItem(R.id.opc_darkmode)
            val mySwitch = item.actionView.findViewById<SwitchMaterial>(R.id.switch_darkmode)

            //bindingSwitch.switchDarkmode es el mismo objeto que mySwitch

            //Si el SO ya está en modo oscuro, cambiamos el switch y la imagen. Si no, solamente la imagen
            if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
                mySwitch.toggle()
                //bindingSwitch.switchDarkmode.toggle()
                binding.cl2.setBackgroundResource(R.drawable.bgd)
            }else{
                binding.cl2.setBackgroundResource(R.drawable.bgn)
            }

            /*bindingSwitch.switchDarkmode.setOnCheckedChangeListener { buttonView, isChecked ->

            }*/

            mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }

        }
        return super.onCreateOptionsMenu(menu)
    }
    fun regresar(view: View) {
        // Se genera un intent para poder regresar a la vista principal con el fin de reiniciar
        // la app
        val intent = Intent(this, MainActivity::class.java)

        // Se borra el stack de intents y se reinicia la ap
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}