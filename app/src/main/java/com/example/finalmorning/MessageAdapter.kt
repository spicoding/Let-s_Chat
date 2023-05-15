package com.example.finalmorning

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, private val messageList: ArrayList<Message>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val ITEM_RECEIVED = 1
    val ITEM_SENT = 2




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if(viewType == 1){
            //inflate receive
            val view = LayoutInflater.from(context).inflate(R.layout.received, parent, false)
            ReceiveViewHolder(view)

        }else{
            //inflate sent
            val view = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            SentViewHolder(view)


        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)  {

        val currentMessage = messageList[position]

        if(holder.javaClass === SentViewHolder::class.java){
            // do functionality of the sent view holder

        val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message

    }else{
        val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message

        }

    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]

        return when {
            FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId) -> ITEM_SENT
            else -> ITEM_RECEIVED
        }
    }

    override fun getItemCount(): Int {

        return messageList.size

    }


    class SentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val sentMessage: TextView = itemView.findViewById(R.id.txt_sent)


    }

    class ReceiveViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val receiveMessage: TextView = itemView.findViewById(R.id.txt_received)


    }

}