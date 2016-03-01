package account.logic;

import common.Database;
import java.io.IOException;

public class Login {

    public Login() {

    }

    public void login(String username, String password) throws IOException {
        
    }

    public boolean loginValidation(String username, String password) {
        Database db = new Database();
        return db.checkUser(username, password);
    }
}
