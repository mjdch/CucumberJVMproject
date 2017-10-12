package com.wh;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AccountReader {
    /**
     * AccountReader properties from file in main directory "account.properties" fill up tags with valid username & login
     * I created this class because I don't want to share my testing account in public repository.
     */
    private Properties prop;

    public AccountReader() {
        loadPropertiesFromFile();
    }

    private void loadPropertiesFromFile() {

        prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("account.properties");
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

}