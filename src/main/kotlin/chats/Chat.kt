package chats

data class Chat(val id:Long,
                val users:List<Long>,
                val messages: MutableList<Message>
                )
