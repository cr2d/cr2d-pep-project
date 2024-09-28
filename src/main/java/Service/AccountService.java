package Service;

import Model.Account;
//import Model.Message;   
import DAO.AccountDAO;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }
    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }

    public Account addAccount(Account account){
        Account mock = accountDAO.insertAccount(account);
        return mock;
    }

    public Account login (Account account){
        Account mock = accountDAO.loginAccount(account);
       
            return mock;
    }
}
