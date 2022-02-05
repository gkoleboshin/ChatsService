package chats

import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun sendMessageInChat_add_new_message() {
        val service = ChatService()
        service.sendMessage(1,2,"Hi friend")
        val result = service.getChats().first { chat->chat.id==1L}.messages.first { message->message.id==1L}
        val excpected = Message(1,1,"Hi friend",false)
        assertEquals(excpected,result)
    }

    @Test
    fun getUnreadChatsCount_count_doing() {
        val service = ChatService()
        service.sendMessage(1,2,"Hi friend")
        service.sendMessage(2,1,"Hi friend")
        service.sendMessage(3,2,"Hi friend")
        service.getMessageInChat(2,3,1)
        val resault = service.getUnreadChatsCount()
        assertEquals(1,resault)
    }

    @Test
    fun getMessageInChat_message_is_read_true() {
        val service = ChatService()
        service.sendMessage(1,2,"Hi friend")
        service.sendMessage(2,1,"Hi friend")
        service.sendMessage(3,2,"Hi friend")
        val resault=service.getMessageInChat(2,3,1).first().isRead
        assertTrue(resault)

    }
}