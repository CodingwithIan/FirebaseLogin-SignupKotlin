package com.ian.transportbiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ian.transportbiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        binding.login.setOnClickListener {
    startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.registerBtn.setOnClickListener {
            val jina=binding.companyName.text.toString()
            val arafa=binding.companyEmail.text.toString()
            val simu=binding.companyPhone.text.toString()
            val siri=binding.companyPasscode.text.toString()

            if (jina.isEmpty()&&arafa.isEmpty()&&simu.isEmpty()&&siri.isEmpty()){
                Toast.makeText(this,"Jaza Mapengo",Toast.LENGTH_LONG).show()
            }
            else{
                auth.createUserWithEmailAndPassword(arafa,siri).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this,LoginActivity::class.java))
                        Toast.makeText(this,"Success $jina",Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}