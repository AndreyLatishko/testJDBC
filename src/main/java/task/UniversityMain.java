package task;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    public static void main(String[] args) throws IOException {
        try(InputStream inputStream = new FileInputStream("C:/Projects/testJDBC/src/config.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            try (UniversityDataManager universityDataManager = new UniversityDataManager()) {
                List<Exercise> exercises = universityDataManager.getLessonByName("History");
                universityDataManager.printLessonByName(exercises);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
