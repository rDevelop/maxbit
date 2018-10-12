package us.rlit.web.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import us.rlit.MaxApplication;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * MaxUsers creates users for authentication. Secure passwords are stord in a ser file.
 */
//todo  * MaxUsers should be rewritten with a db source more secure way to create, save, and delete users.
@Component
public class MaxUsers implements Serializable {
    private static final long serialVersionUID = 779712085115101114L;
    private static transient Logger logger = LoggerFactory.getLogger(MaxApplication.class.getSimpleName());
    private final Map<String, MaxUser> users = new HashMap<>();

    public void addUser(MaxUser user) {
        users.put(user.getUsername(), user);
    }

    public MaxUser getUser(String user) {
        if (users.containsKey(user)) {
            return users.get(user);
        } else {
            logger.warn("User", user + "not found");
        }
        //todo throw exceptions
        return null;
    }

    public Map<String, MaxUser> getUsers() {
        return users;
    }

    /**
     *  createUsers will create a local ser file with encrypted passwords. This is not the most secure way to store admin data, but will work for
     *  dev environments.
     */
    public void createUsers() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        MaxUsers maxUsers = new MaxUsers();
        MaxUser user = new MaxUser(1, "user", passwordEncoder.encode("pass"), "ROLE_USER");
        maxUsers.addUser(user);
        user = new MaxUser(2, "admin", passwordEncoder.encode("pass"), "ROLE_ADMIN");
        maxUsers.addUser(user);
        user = new MaxUser(3, "guest", passwordEncoder.encode("guest"), "ROLE_GUEST");
        maxUsers.addUser(user);

        String userFile = "maxusers" + ".ser";
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(userFile);
                ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
            outputStream.writeObject(maxUsers);
        } catch (IOException e) {
            logger.error("IOException", e.getMessage());
        }
    }
}


