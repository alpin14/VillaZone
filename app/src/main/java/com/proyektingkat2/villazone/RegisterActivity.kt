package com.proyektingkat2.villazone

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyektingkat2.villazone.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.loginMv.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRgstr.setOnClickListener {
            val email = binding.editEmailRegister.text.toString()
            val password = binding.editPasswordRegister.text.toString()

            if (email.isEmpty()){
                binding.editEmailRegister.error = "Email Tidak Boleh Kosong!"
                binding.editEmailRegister.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editEmailRegister.error = "Email Tidak Valid!"
                binding.editEmailRegister.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.editPasswordRegister.error = "Password Tidak Boleh Kosong!"
                binding.editPasswordRegister.requestFocus()
                return@setOnClickListener
            }

            if (password.length < 8){
                binding.editPasswordRegister.error = "Password Minimal 8 Karakter!"
                binding.editPasswordRegister.requestFocus()
                return@setOnClickListener
            }

            registerFirebase(email, password)
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun onBackPressed() {
        val exitIntent = Intent(Intent.ACTION_MAIN)
        exitIntent.addCategory(Intent.CATEGORY_HOME)
        exitIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(exitIntent)
    }
}