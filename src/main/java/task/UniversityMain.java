package task;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    //C:\Projects\testJDBC\src\main\resources\config.properties
    public static void main(String[] args) {
        String fileName ="";
        try {
             fileName = args[0];
        }catch (ArrayIndexOutOfBoundsException e){
            e.getStackTrace();
        }
        DbProperties dbProperties = new DbProperties();
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
        try (UniversityDataManager universityDataManager = new UniversityDataManager(dbProperties)) {
            List<Exercise> exercises = universityDataManager.getLessonByName("History");
            List<Lesson> lessons = universityDataManager.getMinHoursWithLesson(60);
            List<Group> groups = universityDataManager.getMaxHoursForGroup();
            System.out.println("PrintLessonByName:");
            universityDataManager.printLessonByName(exercises);
            System.out.println("PrintMinHoursWithLesson:");
            universityDataManager.printMinHoursWithLesson(lessons);
            System.out.println("PrintMaxHoursForGroup:");
            universityDataManager.printMaxHoursForGroup(groups);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
