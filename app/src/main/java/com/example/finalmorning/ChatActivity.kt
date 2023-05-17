package com.example.finalmorning


// IMPORTING SERVICES AND PACKAGES
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalmorning.R.id
import com.example.finalmorning.R.layout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//START OF FUNCTIONALITY

//Create a class for the Chat Activity

class ChatActivity : AppCompatActivity() {

    //Initializing variables
    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var messageList: ArrayList<Message>
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var mDbRef: DatabaseReference

    private var receiverRoom: String? = null
    private var senderRoom: String? = null

// On Create Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_chat)
        val name = intent.getStringExtra("name")
        val receiverUid = intent.getStringExtra("uid")
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid

    //Calling Variables
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid
        supportActionBar?.title = name
        chatRecyclerView = findViewById(id.chat_Recycler_View)
        messageBox = findViewById(id.message_box)
        sendButton = findViewById(id.send_button)
        messageList = ArrayList()
        messageAdapter = MessageAdapter(this, messageList)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatRecyclerView.adapter = messageAdapter
        mDbRef = FirebaseDatabase.getInstance().getReference()

    //adding the message to the realtime database
        sendButton.setOnClickListener {
            val message = messageBox.text.toString()
            val messageObject = Message(message, senderUid)
            mDbRef.child("chats").child(senderRoom!!).child("Messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("Messages").push()
                        .setValue(messageObject)
                }
            messageBox.setText("")
        }

    // Logic for adding data to the recycler view
        mDbRef.child("chats").child(senderRoom!!).child("Message")
            .addValueEventListener(object: ValueEventListener{

                override fun onDataChange(snapshot: DataSnapshot) {

                    for(postSnapshot in snapshot.children){

                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()

                    messageList.clear()

                }

                // On cancelled Function
                override fun onCancelled(error: DatabaseError) {
                }
            }
        )
    }
}
                    //End Of CHAT ACTIVITY

