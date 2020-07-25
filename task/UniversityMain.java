package task;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UniversityMain {
    public static void main(String[] args) throws SQLException {
        try(UniversityData universityData = new UniversityData()){
            List<Exercise> exercises = universityData.getExercises("History");
            universityData.printExercises(exercises);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
