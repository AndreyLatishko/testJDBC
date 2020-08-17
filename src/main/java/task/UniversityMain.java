package task;


import java.io.*;
import java.util.List;
import java.util.Properties;

public class UniversityMain {
    public static void main(String[] args)  {

        InfoProperties infoProperties = new InfoProperties();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите путь к файлу:");
            String fileName = bufferedReader.readLine();
            if (fileName.equals("C://Projects//testJDBC//src//main//resources//config.properties")) {
                infoProperties.setUrl(bufferedReader.readLine());
                infoProperties.setPassword(Integer.parseInt(bufferedReader.readLine()));
                infoProperties.setUser(bufferedReader.readLine());

            } else {
                InputStream input = new FileInputStream("C://Projects//testJDBC//src//main//resources//config.properties");
                File file = new File(String.valueOf(input));
                if(file.isFile()) {
                    Properties properties = new Properties();
                    properties.load(input);
                    infoProperties.setUrl(properties.getProperty("url"));
                    infoProperties.setPassword(Integer.parseInt(properties.getProperty("password")));
                    infoProperties.setUser(properties.getProperty("user"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try(UniversityDataManager universityDataManager = new UniversityDataManager(infoProperties.getUrl(),
                infoProperties.getPassword(),infoProperties.getUser())){
            List<Exercise> exercises = universityDataManager.getLessonByName("History");
            universityDataManager.printLessonByName(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
