package task;


import java.util.List;

public class UniversityMain {
    public static void main(String[] args)  {
        try(UniversityDataManager universityDataManager = new UniversityDataManager()){
            List<Exercise> exercises = universityDataManager.getInfoNameLesson("History");
            universityDataManager.printInfoNameLesson(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
