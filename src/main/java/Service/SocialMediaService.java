package Service;

import Model.Account;
import DAO.SocialMediaDAO;

public class SocialMediaService {
    SocialMediaDAO accountDAO;

    public SocialMediaService(){
        accountDAO = new SocialMediaDAO();
    }
    public SocialMediaService(SocialMediaDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        Account mock = accountDAO.insertFlight(account);
        return mock;
    }

    public Account login (Account account){
        Account mock = accountDAO.loginAccount(account);
       
            return mock;
    }
}
