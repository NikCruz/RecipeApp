package com.example.recipeapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivitySignInBinding
import com.example.recipeapp.firebase.FirestoreClass
import com.example.recipeapp.model.User
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : BaseActivity() {
    private var binding: ActivitySignInBinding? = null
    private var context: SignInActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        context = this@SignInActivity

        //  Call the setup actionBar function
        setupActionBar()
        binding?.btnSignIn?.setOnClickListener {
            signInRegisteredUser()
        }
    }

    fun signInSuccess(user: User) {
        hideProgressDialog()
        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
        finish()

    }


    private fun setupActionBar() {

        setSupportActionBar(binding?.toolbarSignInActivity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }

        binding?.toolbarSignInActivity?.setNavigationOnClickListener { onBackPressed() }
    }


    private fun signInRegisteredUser() {
        // Here we get the text from editText and trim the space
        val email: String = binding?.etEmailSignin?.text.toString().trim { it <= ' ' }
        val password: String = binding?.etPasswordSignin?.text.toString().trim { it <= ' ' }

        if (validateForm(email, password)) {
            // Show the progress dialog.
            showProgressDialog(resources.getString(R.string.please_wait))
            // Sign-In using FirebaseAuth
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@SignInActivity,
                            "You have successfully signed in.",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("Testing", FirestoreClass().getCurrentUserID())
                        startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    } else {
                        showErrorSnackBar(task.exception!!.message+"")
                        Toast.makeText(
                            context,
                            task.exception!!.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
    // END

    // TODO (Step 3: A function to validate the entries of a user.)
    // START
    /**
     * A function to validate the entries of a user.
     */
    private fun validateForm(email: String, password: String): Boolean {
        return if (TextUtils.isEmpty(email)) {
            showErrorSnackBar("Please enter email.")
            false
        } else if (TextUtils.isEmpty(password)) {
            showErrorSnackBar("Please enter password.")
            false
        } else {
            true
        }
    }
}
