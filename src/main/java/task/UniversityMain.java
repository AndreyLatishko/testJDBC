package task;


import java.util.List;

public class UniversityMain {
    public static void main(String[] args)  {
        try(UniversityDataManager universityDataManager = new UniversityDataManager()){
            List<Exercise> exercises = universityDataManager.getLessonByName("History");
            universityDataManager.printLessonByName(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
