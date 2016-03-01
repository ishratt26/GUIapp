package account.logic;

import common.Database;

public class Register {
    
    public void createUser(String username, String firstName, String lastName, String password, String email, String address, int phoneNumber, String date) {
        Database db = new Database();
        db.createUser(username, firstName, lastName, password, email, address, phoneNumber, date);
    }
}
