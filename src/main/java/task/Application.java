package task;

import java.io.*;
import java.util.Properties;

public class Application extends Thread {
    private final String fileName;

    public Application(String fileName) {
        this.fileName = fileName;
    }

    DbProperties dbProperties = new DbProperties();

    @Override
    public void run() {
        boolean returnValue = fileName.isEmpty();
        if (!returnValue) {
            File fileArgumentMain = new File(fileName);
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArgumentMain))) {
                Properties propertiesFile = new Properties();
                propertiesFile.load(bufferedReader);
                dbProperties.setUrl(propertiesFile.getProperty("url"));
                dbProperties.setPassword(propertiesFile.getProperty("password"));
                dbProperties.setUser(propertiesFile.getProperty("user"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            InputStream input = null;
            try {
                input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Invalid file path");
            }
            Properties properties = new Properties();
            try {
                properties.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            dbProperties.setUrl(properties.getProperty("url"));
            dbProperties.setPassword(properties.getProperty("password"));
            dbProperties.setUser(properties.getProperty("user"));
        }
    }
}
