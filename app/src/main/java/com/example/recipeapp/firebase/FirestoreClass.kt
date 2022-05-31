package com.example.recipeapp.firebase

import android.util.Log
import com.example.recipeapp.activities.MainActivity
import com.example.recipeapp.activities.SignInActivity
import com.example.recipeapp.activities.SignUpActivity
import com.example.recipeapp.model.FoodData
import com.example.recipeapp.model.User
import com.example.recipeapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {


    private val mFireStore = FirebaseFirestore.getInstance()

    //A function to make an entry of the registered user in the firestore database.

    fun registerUser(activity: SignUpActivity, userInfo: User) {

        mFireStore.collection(Constants.USERS)

            .document(getCurrentUserID())

            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {

                // Here call a function of base activity for transferring the result to it.
                activity.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Error writing document", e)
            }
    }

    //  Creates a function to SignIn using firebase and get the user details from Firestore Database.)

    fun signInUser(activity: SignInActivity) {

        // Here we pass the collection name from which we wants the data.
        mFireStore.collection(Constants.USERS)
            // The document id to get the Fields of user.
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                if (loggedInUser != null)
                    activity.signInSuccess(loggedInUser)
            }.addOnFailureListener{
                e->
                Log.e("SignInUser","Error writing document",e)


            }

    }

    // function for getting the user id of current logged user.

    fun getCurrentUserID(): String {
        var currentUser=FirebaseAuth.getInstance().currentUser
        var currentUserID=""
        if(currentUser !=null){
            currentUserID=currentUser.uid
        }
        return currentUserID
    }
    fun getFoodList(activity:MainActivity){
        mFireStore.collection(Constants.FOOD_DATA)
            .get()
            .addOnSuccessListener {
                val foodList = ArrayList<FoodData>()
                for (i in it.documents) {
                    val food = i.toObject(FoodData::class.java)!!
                    foodList.add(food)
                }
                activity.onGettingFoodList(foodList)
            }
            .addOnFailureListener {
                Log.d("Romes", "cannot get boards")
            }
    }

}