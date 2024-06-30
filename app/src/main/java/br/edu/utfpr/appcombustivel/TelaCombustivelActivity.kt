package br.edu.utfpr.appcombustivel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.appcombustivel.databinding.ActivityTelaCombustivelBinding
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat

var clickBt = 0

class TelaCombustivelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTelaCombustivelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCombustivelBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btSearch.setOnClickListener(){
            btSearchOnClick()
        }

        binding.btSearchTwo.setOnClickListener(){
            btSearchTwoOnClick()
        }

        binding.btCalculate.setOnClickListener(){
            btCalculateOnClick()
        }
        binding.btClean.setOnClickListener(){
            btCleanOnClick()
        }
    }

    private fun btCleanOnClick() {
        binding.fuelOne.setText("")
        binding.priceOne.setText("")
        binding.edFuelOne.hint = "Km/L"
        binding.tvPriceOne.hint = "Insira um valor em reais"
        binding.fuelTwo.setText("")
        binding.priceTwo.setText("")
        binding.edFuelTwo.hint = "Km/L"
        binding.tvPriceTwo.hint = "Insira um valor em reais"
        binding.tvResultOne.text = ""
        binding.tvResultTwo.text = ""

    }

    private fun btCalculateOnClick() {
        if (binding.fuelOne.text.toString().isEmpty()) {
            binding.edFuelOne.error = getString(R.string.erro_fuel)
            binding.edFuelOne.requestFocus()
            return
        }
        if (binding.fuelTwo.text.toString().isEmpty()) {
            binding.edFuelTwo.error = getString(R.string.erro_fuel)
            binding.edFuelTwo.requestFocus()
            return
        }
        if (binding.priceOne.text.toString().isEmpty()) {
            binding.tvPriceOne.error = getString(R.string.erro_price)
            binding.tvPriceOne.requestFocus()
            return
        }
        if (binding.fuelTwo.text.toString().isEmpty()) {
            binding.tvPriceTwo.error = getString(R.string.erro_price)
            binding.tvPriceTwo.requestFocus()
            return
        } else {
            val fuelOne = binding.fuelOne.text.toString().toDouble()
            val priceOne = binding.priceOne.text.toString().toDouble()
            val fuelTwo = binding.fuelTwo.text.toString().toDouble()
            val priceTwo = binding.priceTwo.text.toString().toDouble()
            val resultOne = calculate(fuelOne, priceOne)
            val resultTwo = calculate(fuelTwo, priceTwo)
            consultResults(resultOne, resultTwo)
        }
    }



    @SuppressLint("SetTextI18n")
    private fun consultResults(resultOne: Double, resultTwo: Double) {
        val formattedResultOne = NumberFormat.getCurrencyInstance().format( resultOne )
        val formattedResultTwo = NumberFormat.getCurrencyInstance().format( resultTwo )
        binding.tvResultOne.text = "Valor por KM com ${binding.edFuelOne.hint.toString()}: ${formattedResultOne}"
        binding.tvResultTwo.text = "Valor por KM com ${binding.edFuelTwo.hint.toString()}: ${formattedResultTwo}"
        if (resultOne < resultTwo) {
            Snackbar.make(this.binding.root, "O combustivel mais barato é: ${binding.edFuelOne.hint.toString()}", Snackbar.LENGTH_LONG)
                .setAction("Aceitar") {}
                .show()
        } else {
            Snackbar.make(this.binding.root, "O combustivel mais barato é: ${binding.edFuelTwo.hint.toString()}", Snackbar.LENGTH_LONG)
                .setAction("Aceitar") {}
                .show()
        }
    }

    private fun calculate(fuel: Double, price: Double): Double {
        var result = price / fuel
        return result
    }

    private fun btSearchTwoOnClick() {
        val intent = Intent(this, ListaCombustivelActivity::class.java)
        btSeachResultTwo.launch(intent)
    }

    private fun btSearchOnClick() {
        val intent = Intent(this, ListaCombustivelActivity::class.java)
        btSeachResult.launch(intent)
    }



    private val btSeachResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {resultOne ->
        if (resultOne.resultCode == RESULT_OK) {
            val gas = resultOne.data?.getIntExtra("gasChoose", 0).toString()
            val hint = resultOne.data?.getStringExtra("hint").toString()
            binding.fuelOne.setText(gas)
            binding.edFuelOne.hint = hint
            binding.tvPriceOne.hint = "Valor Litro ${hint} R$"
        }
    }

    private val btSeachResultTwo = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result->
        if (result.resultCode == RESULT_OK) {
            val gas = result.data?.getIntExtra("gasChoose", 0).toString()
            val hint = result.data?.getStringExtra("hint").toString()
            binding.fuelTwo.setText(gas)
            binding.edFuelTwo.hint = hint
            binding.tvPriceTwo.hint = "Valor Litro ${hint} R$"
        }
    }
}
