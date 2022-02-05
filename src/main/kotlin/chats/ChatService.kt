package chats

class ChatService {

    private val chats = mutableListOf<Chat>()
    var messageId = 1L

    fun sendMessage(senderId:Long,recipientId:Long,message:String){
        val message = Message(
            id = messageId,
            ownerId = senderId,
            message = message,
            isRead = false
        )
        messageId++
        val chat =chats.firstOrNull { it.users.containsAll(listOf(senderId,recipientId))}?:Chat(
            id=(chats.size+1).toLong(),
            users = listOf(senderId,recipientId),
            messages = mutableListOf()
        )
        chat.messages.add(message)
        val indexChat=chats.indexOfFirst { it.id ==chat.id}
        if(indexChat!=-1){
            chats[indexChat]=chat
        }else {
            chats.add(chat)
        }
    }

    fun getUnreadChatsCount():Int{
        return chats.filter { chat->chat.messages.firstOrNull{message -> !message.isRead}!=null}.size
    }


    fun getMessageInChat(idChat:Long,idFirstMessage:Long,countMessage:Long):List<Message>{

        val readMessages = mutableListOf<Message>()
        try {
            val indexChat=chats.indexOf(chats.firstOrNull { it.id==idChat})
            val allMessageInChat = chats[indexChat].messages
            val messages =
                allMessageInChat.filter { it.id in idFirstMessage until countMessage + idFirstMessage }.toMutableList()

            messages.forEach { newMessage ->
                readMessages.add(newMessage.copy(isRead = true))
                val messageId = newMessage.id
                chats.first { it.id == idChat }.messages.forEach { message ->
                    if (message.id == messageId) {
                        val indexMessage = chats[indexChat].messages.indexOf(message)
                        chats[indexChat].messages[indexMessage] = newMessage.copy(isRead=true)
                    }
                }
            }
            return readMessages
        }catch (e:IndexOutOfBoundsException){
            println("Chat not found!")
            return emptyList()        }

    }

    fun getChats():List<Chat>{
        return chats
    }

    fun deleteChats(idChat: Long):Boolean{
        println("delete chat complete successful!")
        return chats.removeIf {it.id==idChat}
    }

    fun deleteMessage(idChat: Long,idMessage: Long):Boolean{

      try {
            val chat= chats.firstOrNull{ it.id==idChat }?:throw ChatNotFoundException("Chat not found!")
            val messages =chat.messages
            messages.removeIf {it.id==idMessage}
            println("delete messege complete successful!")
            if (messages.isEmpty()){deleteChats(idChat)}
            return true
      }
        catch (e:ChatNotFoundException){
            println("Chat not found!!")
            return false
        }
    }






}