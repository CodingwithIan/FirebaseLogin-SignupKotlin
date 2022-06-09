package com.ian.transportbiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ian.transportbiz.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    auth= FirebaseAuth.getInstance()
        binding.registerHere.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        binding.loinBtn.setOnClickListener {
            val arafa=binding.companyEmail.text.toString()
            val siri=binding.companyPasscode.text.toString()
            if (arafa.isEmpty()&&siri.isEmpty()){
              Toast.makeText(this,"Jaza Mapengo",Toast.LENGTH_LONG).show()
            }
            else{
              auth.signInWithEmailAndPassword(arafa,siri).addOnCompleteListener {
                  if (it.isSuccessful){
                      startActivity(Intent(this,DashboardActivity::class.java))
                      Toast.makeText(this,"Karibu",Toast.LENGTH_LONG).show()
                  }
                  else{
                      Toast.makeText(this,it.exception.toString(),Toast.LENGTH_LONG).show()
                  }
              }
            }
        }
    }
}