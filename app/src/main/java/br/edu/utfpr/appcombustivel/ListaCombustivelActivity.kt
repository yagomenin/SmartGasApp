package br.edu.utfpr.appcombustivel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.appcombustivel.databinding.ActivityListaCombustivelBinding

class ListaCombustivelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaCombustivelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaCombustivelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btInsertGasolina.setOnClickListener(){
            btInsertGasolinaOnClick()
        }
        binding.btInsertEtanol.setOnClickListener(){
            btInsertEtanolOnClick()
        }
        binding.btInsertDiesel.setOnClickListener(){
            btInsertDieselOnClick()
        }
        binding.btInsertGas.setOnClickListener(){
            btInsertGasOnClick()
        }
    }

    private fun btInsertGasOnClick() {
        intent.putExtra( "gasChoose", 7)
        intent.putExtra("hint", "Gas")
        setResult( RESULT_OK, intent )
        finish()
    }

    private fun btInsertDieselOnClick() {
        intent.putExtra( "gasChoose", 10)
        intent.putExtra("hint", "Diesel")
        setResult( RESULT_OK, intent )
        finish()
    }

    private fun btInsertEtanolOnClick() {
        intent.putExtra( "gasChoose", 9)
        intent.putExtra("hint", "Etanol")
        setResult( RESULT_OK, intent )
        finish()
    }

    private fun btInsertGasolinaOnClick() {
        intent.putExtra( "gasChoose", 12)
        intent.putExtra("hint", "Gasolina")
        setResult( RESULT_OK, intent )
        finish()
    }
}