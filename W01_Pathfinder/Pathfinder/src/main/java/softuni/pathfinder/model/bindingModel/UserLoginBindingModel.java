package softuni.pathfinder.model.bindingModel;

public class UserLoginBindingModel {
    private String userName;
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
