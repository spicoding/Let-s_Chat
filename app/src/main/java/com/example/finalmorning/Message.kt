package com.example.finalmorning

// CREATE A MESSAGE MODEL CLASS
class Message {
    var message: String? = null
    var senderId: String? = null

    constructor(){}

    // Actual message model
    constructor(message: String?, senderId: String?){
        this.message = message
        this.senderId = senderId
    }
}