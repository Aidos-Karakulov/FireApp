package com.example.fireapp.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fireapp.databinding.ActivitySignUpBinding
import com.example.fireapp.view.MainActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding:ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }
private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.signButton.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

binding.registerButton.setOnClickListener{
    val email:String = binding.email.text.toString()
    val username:String = binding.userName.toString()
    val password:String = binding.password.toString()
    val repeatePassword:String = binding.repeatPassword.toString()

    if(email.isEmpty()|| username.isEmpty()|| password.isEmpty()|| repeatePassword.isEmpty()){
        Toast.makeText(this,"Please Fill all details",Toast.LENGTH_SHORT).show()
    }else if(password!=repeatePassword){
        Toast.makeText(this,"Your password not same bro ",Toast.LENGTH_SHORT).show()

    }else{
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task->
                if(task.isSuccessful){
                    Toast.makeText(this,"Registration is successfull ", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this,"Registration failed: ${task.exception?.message}",Toast.LENGTH_SHORT).show()
                }

            }
            }
    }

}
        }



