package sample;

import java.util.ArrayList;

/**
 * Created by DTG2 on 09/01/16.
 */
public class Users {

    ArrayList<String> users = new ArrayList<>();
    String userName;

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
