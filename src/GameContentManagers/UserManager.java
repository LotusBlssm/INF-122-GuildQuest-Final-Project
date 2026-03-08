package GameContentManagers;

import GameContents.User;
import java.util.List;
import java.util.ArrayList;

public class UserManager {
    private List<User> users;
    private static UserManager userManager = null;

    private UserManager() {
        users = new ArrayList<>();
    }

    // singleton design pattern
    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    // add new user
    public boolean addUser(User newUser) {
        if (users.contains(newUser)) {
            return false;
        }
        users.add(newUser);
        return true;
    }

    // remove user
    public boolean removeUser(String name) {
        for (User user : users) {
            if (user.checkName(name)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }

    // update user name
    public boolean updateUserName(String oldName, String newName) {
        for (User user : users) {
            if (user.checkName(oldName)) {
                user.setName(newName);
                return true;
            }
        }
        return false;
    }
}
