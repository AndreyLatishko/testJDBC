package task;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    //C:\Projects\testJDBC\src\main\resources\config.properties
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        DbProperties dbProperties = new DbProperties();
        String fileName = "";
        if (args.length >= 1) {
            fileName = args[0];
            File propertiesFile = new File(fileName);
            if (propertiesFile.exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(propertiesFile))) {
                    properties.load(bufferedReader);
                }
            } else {
                InputStream input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config3.properties");
                properties.load(input);
            }
        } else {
            InputStream input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config3.properties");
            properties.load(input);
        }
        dbProperties.setUrl(properties.getProperty("url"));
        dbProperties.setPassword(properties.getProperty("password"));
        dbProperties.setUser(properties.getProperty("user"));

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
