package task;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    public static String url;
    public static int password;
    public static String user;

    public static void main(String[] args)  {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите данные url, password, user");
            url = bufferedReader.readLine();
            if (url.equals("jdbc:mysql://localhost:3306/test")) {
                password = Integer.parseInt(bufferedReader.readLine());
                user = bufferedReader.readLine();
            } else {
                System.out.println(1);
                InputStream input = new FileInputStream("C:/Projects/testJDBC/src/main/resources/config.properties");
                Properties properties = new Properties();
                properties.load(input);
                url = properties.getProperty("url");
                password = Integer.parseInt(properties.getProperty("password"));
                user = properties.getProperty("user");
            }
            UniversityDataManager universityDataManager = new UniversityDataManager();
            List<Exercise> exercises = universityDataManager.getLessonByName("History");
            universityDataManager.printLessonByName(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
