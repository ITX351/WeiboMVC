package hit.weibo.util;

/**
 * Created by ITX351 on 2016/10/12.
 */
public class LoginStatus {
    private boolean logged;
    private String username;
    private Integer user_id;

    public LoginStatus() {
        this.logged = false;
        this.username = null;
        this.user_id = null;
    }

    public LoginStatus(boolean logged) {
        this.logged = logged;
        this.username = null;
        this.user_id = null;
    }

    public LoginStatus(boolean logged, String username, Integer user_id) {
        this.logged = logged;
        this.username = username;
        this.user_id = user_id;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static boolean isLogged(LoginStatus loginStatus) {
        return loginStatus != null && loginStatus.getLogged();
    }
}
