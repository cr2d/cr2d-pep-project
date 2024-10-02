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
        return messageDao.postMessage(message);
    }
    public Message getMessageByID (String id){
        return messageDao.messageGetByID(id);
    }
    public Message DelMessageByID(String id){
        Message toBeDeleted = messageDao.messageGetByID(id);
        messageDao.messageDelByID(id);
        return toBeDeleted;
    }
    public Message UpdateMessageByID(String id, Message message){
        Message qualify = messageDao.messageGetByID(id);
        if(qualify != null){
            if(message.getMessage_text() != "" && message.getMessage_text().length() < 255){
                messageDao.UpdateMessage(id, message);
                return messageDao.messageGetByID(id);
            }
        }
        return null;
    }
}
