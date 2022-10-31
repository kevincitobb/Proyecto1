package com.kevs.proyecto1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.switchmaterial.SwitchMaterial
import com.kevs.proyecto1.databinding.ActivityMainBinding
import com.kevs.proyecto1.databinding.SwitchItemBinding

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var formula = ""
    val π = 3.1416

    private lateinit var bindingSwitch: SwitchItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingSwitch = SwitchItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val spinner: Spinner = binding.spFormulas
        spinner.onItemSelectedListener = this
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.formulas_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }


    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        formula = parent.getItemAtPosition(pos).toString()

        when(formula){
            parent.getItemAtPosition(0).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.logo)
            }

            parent.getItemAtPosition(1).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.prisma)
                binding.tv3.visibility = View.VISIBLE
                binding.et3.visibility = View.VISIBLE
                binding.tv1.setText(R.string.largo)
                binding.tv2.setText(R.string.ancho)
                binding.tv3.setText(R.string.altura)
            }
            parent.getItemAtPosition(2).toString() ->{
                binding.ivFormula.setImageResource(R.drawable.cilindro)
                binding.tv3.visibility = View.GONE
                binding.et3.visibility = View.GONE
                binding.tv1.setText(R.string.radio)
                binding.tv2.setText(R.string.altura)
            }
            parent.getItemAtPosition(3).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.cono)
                binding.tv3.visibility = View.GONE
                binding.et3.visibility = View.GONE
                binding.tv1.setText(R.string.radio)
                binding.tv2.setText(R.string.altura)
            }
            parent.getItemAtPosition(4).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.trapezoide)
                binding.tv3.visibility = View.VISIBLE
                binding.et3.visibility = View.VISIBLE
                binding.tv1.setText(R.string.basemayor)
                binding.tv2.setText(R.string.basemenor)
                binding.tv3.setText(R.string.altura)
            }
            parent.getItemAtPosition(5).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.rectangulo)
                binding.tv3.visibility = View.GONE
                binding.et3.visibility = View.GONE
                binding.tv1.setText(R.string.largo)
                binding.tv2.setText(R.string.ancho)
            }
            parent.getItemAtPosition(6).toString() -> {
                binding.ivFormula.setImageResource(R.drawable.triangulo)
                binding.tv3.visibility = View.GONE
                binding.et3.visibility = View.GONE
                binding.tv1.setText(R.string.basemenor)
                binding.tv2.setText(R.string.altura)
            }
            else ->{
                binding.tv1.visibility = View.GONE
                binding.et1.visibility = View.GONE

                binding.tv2.visibility = View.GONE
                binding.et2.visibility = View.GONE

                binding.tv3.visibility = View.GONE
                binding.et3.visibility = View.GONE
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
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
                binding.cl.setBackgroundResource(R.drawable.backd)
            }else{
                binding.cl.setBackgroundResource(R.drawable.backg)
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
    fun prisma(xv: EditText, yv:EditText, zv:EditText) {
        var error = false
        val array = arrayOf(xv, yv, zv)
        val inputs = arrayOf(binding.et1, binding.et2, binding.et3)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()
        val z = zv.text.toString().toDouble()

        val x1 = x*y*z
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
    fun cilindro(xv: EditText, yv:EditText) {
        var error = false
        val array = arrayOf(xv, yv)
        val inputs = arrayOf(binding.et1, binding.et2)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()

        val x1 =(π*x*y)
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
    fun cono(xv: EditText, yv:EditText) {
        var error = false
        val array = arrayOf(xv, yv)
        val inputs = arrayOf(binding.et1, binding.et2)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()

        val x1 =(π*x*y)/3
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
    fun trapezoide(xv: EditText, yv:EditText, zv:EditText) {
        var error = false
        val array = arrayOf(xv, yv, zv)
        val inputs = arrayOf(binding.et1, binding.et2, binding.et3)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()
        val z = zv.text.toString().toDouble()

        val x1 =((x+y)*z)/2
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

    fun rectangulo(xv: EditText, yv:EditText) {
        var error = false
        val array = arrayOf(xv, yv)
        val inputs = arrayOf(binding.et1, binding.et2)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()

        val x1 =(x*y)
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

    fun triangulo(xv: EditText, yv:EditText) {
        var error = false
        val array = arrayOf(xv, yv)
        val inputs = arrayOf(binding.et1, binding.et2)

        for (i in 0..array.size - 1) {
            if (array[i].text.isEmpty()) {
                inputs[i].error = getString(R.string.Vacio)
                error = true
            }
        }
        if (error) {
            return
        }
        val x = xv.text.toString().toDouble()
        val y = yv.text.toString().toDouble()

        val x1 =(x*y)/2
        val res = arrayOf(x1).toDoubleArray()
        val parametros = Bundle()

        parametros.apply{
            putString("formula",formula)
            putDoubleArray("res",res)
        }

        val intent = Intent(this, Result::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }

    fun calcular(view: View): Double {
        var resultado = 0.0
        val x=binding.et1
        val y=binding.et2
        val z=binding.et3

        when(formula){
            binding.spFormulas.getItemAtPosition(1).toString() ->{
                prisma(x,y,z)
                return resultado
            }
            binding.spFormulas.getItemAtPosition(2).toString() ->{
                cilindro(x,y)
                return resultado
            }
            binding.spFormulas.getItemAtPosition(3).toString() ->{
                cono(x,y)
                return resultado
            }
            binding.spFormulas.getItemAtPosition(4).toString() ->{
                trapezoide(x,y,z)
                return resultado
            }
            binding.spFormulas.getItemAtPosition(5).toString() ->{
                rectangulo(x,y)
                return resultado
            }
            binding.spFormulas.getItemAtPosition(6).toString() ->{
                triangulo(x,y)
                return resultado
            }
            else ->{
                return resultado
            }
        }
    }



}