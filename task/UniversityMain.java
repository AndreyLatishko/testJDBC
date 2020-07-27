package task;


import java.util.List;

public class UniversityMain {
    public static void main(String[] args)  {
        try(UniversityData universityData = new UniversityData()){
            List<Exercise> exercises = universityData.getInfoNameLesson("History");
            universityData.printInfoNameLesson(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
