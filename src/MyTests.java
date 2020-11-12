import Examples.User;
import netscape.javascript.JSObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.json.*;

import javax.swing.*;
import java.io.File;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MyTests {

    private APIClient apiClient;
    private DBManager dbManager;
    private Object obj;

    public MyTests() {
        try {
            apiClient = new APIClient();
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        dbManager = new DBManager();
    }

    @Test
    public void testApi_checkIfRegister() throws Exception {

        // Arrange
        String validUserRegisterRequestAsString = readFileAsString("./Example/validUserRegister.json");
        User userFromFile = new User();
        JSONObject obj = new JSONObject(validUserRegisterRequestAsString);
        // how the fuck
        System.out.println(obj.getString(validUserRegisterRequestAsString.));


        // Act
        apiClient.send(validUserRegisterRequestAsString);

        // Assert
        ArrayList<User> resultAllUsers = dbManager.getAllUsers();

        User resultUserByUsername = null;
        for (User user : resultAllUsers) {
            if (user.username.equals(userFromFile.username)) {
                resultUserByUsername = user;
            }
        }

        Assertions.assertNotEquals(userFromFile, resultUserByUsername);
    }

    public String readFileAsString(String path) throws Exception {
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        String fileContent = "";
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            fileContent += data;
        }

        return fileContent;
    }
}