package task;


import java.io.*;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    public static void main(String[] args) {
        DbProperties dbProperties = new DbProperties();
        String fileName = args[0];
        File fileArgumentMain = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArgumentMain))) {
            if (fileArgumentMain.exists()) {
                dbProperties.setUrl(bufferedReader.readLine());
                dbProperties.setPassword((bufferedReader.read()));
                dbProperties.setUser(bufferedReader.readLine());
            } else {
                InputStream input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config.properties");
                File file = new File(String.valueOf(input));
                if (file.exists()) {
                    Properties properties = new Properties();
                    properties.load(input);
                    dbProperties.setUrl(properties.getProperty("url"));
                    dbProperties.setPassword(Integer.parseInt(properties.getProperty("password")));
                    dbProperties.setUser(properties.getProperty("user"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (UniversityDataManager universityDataManager = new UniversityDataManager(dbProperties)) {
            List<Exercise> exercises = universityDataManager.getLessonByName("History");
            universityDataManager.printLessonByName(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
