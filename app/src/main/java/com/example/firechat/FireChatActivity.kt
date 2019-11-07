package com.example.firechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_fire_chat.*

class FireChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_chat)




    }

    override fun onResume() {
        super.onResume()
        postMessage() //invocking

    }

    fun postMessage(){
        btnSend.setOnClickListener {
            val database = FirebaseDatabase.getInstance()
            val dbRef = database.getReference("messages")

            var newMessageRef = dbRef.push()

            var message: String = etMessage.text.toString()       //obtaining message from edittext
            var sender = FirebaseAuth.getInstance().currentUser?.email //currentUser that we assigned in Reg activity

            var chat:Chat = Chat(sender as String,message)

            newMessageRef.setValue(chat)


        }
    }

    fun fetchMessagesFromFirebaseDb(){
        val dbReference = FirebaseDatabase.getInstance().getReference("messages")
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {


            }

            override fun onDataChange(p0: DataSnapshot) {
                var chatList: MutableList<Chat> = ArrayList<Chat>()
                for (chatItem in p0.children){
                   var chat = chatItem.getValue(Chat::class.java)
                    chatList.add(chat as Chat)
                }

            }

        })
    }
}
