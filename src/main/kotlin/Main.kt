import chats.ChatService

fun main() {
   val service = ChatService()
   service.sendMessage(1,2,"hello world")
   service.sendMessage(1,2,"hello world2")
   service.sendMessage(2,1,"hello world2")
   service.sendMessage(2,3,"hello world2")
   println(service.getChats())
   println(service.getMessageInChat(1,1,4))
   println(service.getChats())
   println(service.getUnreadChatsCount())
   println (service.deleteMessage(4,2))
   println(service.getChats())
}