package com.quadris.riskybusiness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.quadris.riskybusiness.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var btnRegister: Button? = null
        var etSignupEmail: EditText? = null
        var etSignupPassword: EditText? = null
        var tvLoginscreen: TextView? = null

        btnRegister = findViewById(R.id.btnRegister)
        etSignupEmail = findViewById(R.id.et_signup_email)
        etSignupPassword = findViewById(R.id.et_signup_password)
        tvLoginscreen = findViewById(R.id.tv_loginscreen)


        btnRegister.setOnClickListener { //knows when register button is clicked
            when { /*if the email slot is empty when register button is clicked it will bring up a
                 small notification asking for email */
                TextUtils.isEmpty(
                    etSignupEmail.text.toString()
                        .trim { it <= ' ' }) -> { //trim gets rid of any spaces if user
                    // accidentally adds them
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //if the email slot is empty when register button is clicked it will bring
                // up a small notification asking for email
                TextUtils.isEmpty(
                    etSignupPassword.text.toString()
                        .trim { it <= ' ' }) -> { //trim gets rid of any spaces if user
                    // accidentally adds them
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter password", //toast means a small in app notification
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                    //creates variable to hold inputted email
                    val email: String = etSignupEmail.text.toString().trim { it <= ' '}
                    //creates variable to hold inputted password
                    val password: String = etSignupPassword.text.toString().trim { it <= ' '}
                    register(email, password)


                }
            }
        }
        //move to login page using login text view
        tvLoginscreen.setOnClickListener {

            //move to login page
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    fun register(email: String, password: String){
        //creates an instance and creates a register a user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(
                OnCompleteListener<AuthResult> { task ->

                    //If the registration is successfully done
                    if (task.isSuccessful) {

                        //FireBas registered User
                        //create user from user inside task result
                        val firebaseUser: FirebaseUser = task.result!!.user!!

                        //makes notification to let user know their account was made
                        Toast.makeText(
                            this@RegisterActivity,
                            "You were registered successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        /*
                        Here the user is automatically signed in and moved to the
                        main screen if the sign up was successful :)
                         */
                        val intent =
                            Intent(this@RegisterActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        /*closes down activities running in the background if user
                         is switching between them */

                        intent.putExtra("user_id", firebaseUser.uid)
                        intent.putExtra("email_id", email)
                        startActivity(intent)
                        finish()
                    } else {
                        //if register isnt successful then error message
                        Toast.makeText(
                            this@RegisterActivity,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            )
    }
}