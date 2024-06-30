package br.edu.utfpr.appcombustivel

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.appcombustivel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btLogin.setOnClickListener(){
            btOnClick()
        }
    }

    private fun btOnClick() {
       val intent = Intent(this,TelaCombustivelActivity::class.java)
        startActivity(intent)
        finish()
    }

}