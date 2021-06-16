package com.medipol.zahitcankaraca.cimricom.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.medipol.zahitcankaraca.cimricom.R
import com.medipol.zahitcankaraca.cimricom.databinding.ActivityLoginBinding
import com.medipol.zahitcankaraca.cimricom.ui.categoryListing.CategoryListingActivity

class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    fun init(){
        auth = FirebaseAuth.getInstance()
        binding.btnLogin.setOnClickListener {
            if(binding.edtEmail.text.trim().toString().isNotEmpty() && binding.edtPassword.text.trim().toString().isNotEmpty()){
                loginUser(binding.edtEmail.text.trim().toString(),binding.edtPassword.text.trim().toString())
            }else{
                Toast.makeText(this,getString(R.string.not_Null),Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginUser(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful){
                    Toast.makeText(this,getString(R.string.login),Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, CategoryListingActivity::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this,getString(R.string.not_login),Toast.LENGTH_SHORT).show()
                }
            }
    }
}