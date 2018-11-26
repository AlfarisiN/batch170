package bootcamp.com.batch170.models;

/**
 * Created by Eric on 25/10/2018.
 */

public class LoginModel {
    private String message;
    private String generatedToken;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGeneratedToken() {
        return generatedToken;
    }

    public void setGeneratedToken(String generatedToken) {
        this.generatedToken = generatedToken;
    }
}
