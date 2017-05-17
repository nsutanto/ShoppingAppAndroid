package nsutanto.model;

/**
 * Created by Nico on 5/14/17.
 */

public class RegisterInfo {


    public String Email;
    public String Password;
    public String ConfirmPassword;

    public RegisterInfo(String email)
    {
        Email = email;
        Password = "TestPassword1!";
        ConfirmPassword = "TestPassword1!";
    }
}
