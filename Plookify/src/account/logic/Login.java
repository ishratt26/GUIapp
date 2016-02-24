package account.logic;

import account.gui.MainStage;
import account.gui.login.LoginScene;
import common.main;
import java.io.IOException;

public class Login {

    public Login() {

    }

    public void login(String username, String password) throws IOException {
            System.out.println("SUCCESS!");
    }

    public boolean loginValidation(String username, String password) {
        if (username.equals("Alen") & password.equals("password"))
            return true;
        else
            return false;

    }
}
