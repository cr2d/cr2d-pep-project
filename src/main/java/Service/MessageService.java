package Service;

import Model.Message;   
import DAO.MessageDAO;
import java.util.List;
public class MessageService {
    MessageDAO messageDao;
    public MessageService(){
        messageDao = new MessageDAO();
    }
    public MessageService(MessageDAO messageDao){
        this.messageDao = messageDao;
    }
    public List<Message> getAllMessages() {
        return messageDao.getAllMessage();
    }

    public Message newMessage(Message message){
        int poster = message.getPosted_by();
       // if(smDAO.getBookByIsbn(isbn_new) != null){
         //   return null;
        //}
        Message m = messageDao.postMessage(message);
        return messageDao.postMessage(message);
    }
}
