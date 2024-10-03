package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService= new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::postRegisterHandler);
        app.post("/login", this::PostLoginHandler);
        app.post("/messages", this::PostMessageHandler);
        app.get("/messages", this::GetAlMessageHandler);
        app.get("/messages/{message_id}", this::GetMessageByIDHandler);
        app.delete("/messages/{message_id}", this::DeleteMessageByIDHandler);
        app.patch("/messages/{message_id}", this::UpdateMessageByIDHandler);
        app.get("/accounts/{account_id}/messages", this::GetAllMessagesByUserIDHandler);

        return app;
    }
    
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void postRegisterHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account addAccount = accountService.addAccount(account);
        if(addAccount==null){
            ctx.status(400);
        }else{
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(addAccount));
        }
    }
    private void PostLoginHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account authorize = accountService.login(account);
        if(authorize == null){
            ctx.status(401);
        }else{
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(authorize));
        }

    }
    private void PostMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message post = messageService.newMessage(message);
        if(post == null){
            ctx.status(400);
        }else{
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(post));
        }
    }
    private void GetAlMessageHandler(Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.getAllMessages();
        ctx.json(messages);
    }
    private void GetMessageByIDHandler(Context ctx) throws JsonProcessingException{
        Message message = messageService.getMessageByID(ctx.pathParam("message_id"));
        if(message == null){
            ctx.status(200);
        }else{
            ctx.json(message);
        }
    }
    private void DeleteMessageByIDHandler(Context ctx) throws JsonProcessingException{
        Message message = messageService.DelMessageByID(ctx.pathParam("message_id"));
        if(message == null){
            ctx.status(200);
        }else{
            ctx.json(message);
        }
        
    }
    private void UpdateMessageByIDHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        String msg_id = ctx.pathParam("message_id");
        Message updateMessage = messageService.UpdateMessageByID(msg_id, message);
        if(updateMessage == null){
            ctx.status(400);
        }else{
            ctx.status(200);
            ctx.json(mapper.writeValueAsString(updateMessage));
        }
    }
    private void GetAllMessagesByUserIDHandler(Context ctx) throws JsonProcessingException{
        List<Message> messages = messageService.GetAllMessageByUserID(ctx.pathParam("account_id"));
        ctx.json(messages);
        
    }
}