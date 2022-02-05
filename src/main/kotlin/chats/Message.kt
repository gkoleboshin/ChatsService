package chats

data class Message(val id:Long,
                   val ownerId:Long,
                   val message: String,
                   val isRead:Boolean,
                   )
