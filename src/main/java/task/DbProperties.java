package task;

import java.util.Objects;

public class DbProperties {
    private final String url, user, password;

    public DbProperties(String url, String user, String password) {
        Objects.requireNonNull(url);
        Objects.requireNonNull(user);
        Objects.requireNonNull(password);
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}