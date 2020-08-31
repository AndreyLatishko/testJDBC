package task;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    //C:\Projects\testJDBC\src\main\resources\config.properties
    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 1) {
            fileName = args[0];
        }
        DbProperties dbProperties = new DbProperties();
        boolean fileNameEmpty = fileName.isEmpty();
        if (!fileNameEmpty) {
            File propertiesFile = new File(fileName);
            if(propertiesFile.exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(propertiesFile))) {
                    Properties properties = new Properties();
                    properties.load(bufferedReader);
                    dbProperties.setUrl(properties.getProperty("url"));
                    dbProperties.setPassword(properties.getProperty("password"));
                    dbProperties.setUser(properties.getProperty("user"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else{
                System.out.println("Invalid propertiesFile");
            }
        } else {
            InputStream input;
            Properties properties = new Properties();
            try {
                input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config.properties");
                properties.load(input);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Invalid file path");
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
