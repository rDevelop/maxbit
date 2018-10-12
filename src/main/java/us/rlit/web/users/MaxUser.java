package us.rlit.web.users;

import java.io.Serializable;

/**
 * Created by rob on 12/20/16.
 */

public class MaxUser implements Serializable {
    private int id;
    private String username;
    private String key;
    private String role;
    private transient Long sessionId;

    public MaxUser() {
    }

    public MaxUser(int id, String username, String key, String role) {
        this.id = id;
        this.username = username;
        this.key = key;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

//    private void writeObject(ObjectOutputStream oos) throws ClassNotFoundException, IOException {
//        System.out.println("Wrote cart at " + LocalDateTime.now());
//        oos.defaultWriteObject();
//        oos.writeObject(new Date());
//    }
//
//    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
//        ois.defaultReadObject();
//        Date date = (Date) ois.readObject();
//        System.out.println("Restored cart from " + DateFormat.getDateInstance().format(date));
//    }

    @Override
    public String toString() {
        return "[" + id + ", " + username + ", " + role + ", " + key + "]";
    }


}
