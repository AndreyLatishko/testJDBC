package task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UniversityDataManager implements AutoCloseable {

    private Connection connection;
    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();

    public UniversityDataManager() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        Properties props = new Properties();
        props.setProperty("password", "1234");
        props.setProperty("user", "root");
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(url, props);
    }

    private void infoNameLesson(String value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT *  FROM test.lesson JOIN test.exercise " +
                "ON lesson.id = exercise.idlesson WHERE lesson.name = ?");
        statement.setString(1, value);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int quantityhours = resultSet.getInt("quantityhours");
            String form = resultSet.getString("form");
            int idE = resultSet.getInt("id");
            int idgroup = resultSet.getInt("idgroup");
            int idlesson = resultSet.getInt("idlesson");
            String topic = resultSet.getString("topic");
            System.out.printf("Id lesson - %d, name - %s, count quantityhours = %d, form - %s, Id exercise - %d," +
                    "idgroup - %d, idlesson - %d, topic - %s.%n", id, name, quantityhours, form, idE, idgroup, idlesson, topic);
        }
    }

    private void minHoursLesson(int value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT name, MIN(lesson.quantityhours) " +
                "AS MIN_quantityhours FROM test.lesson WHERE quantityhours <= ?");
        statement.setInt(1, value);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int quantityhours = resultSet.getInt("quantityhours");
            System.out.printf("Name - %s, count quantityhours = %d.", name, quantityhours);
        }
    }

    private void groupMaxHours(String value) throws SQLException {
        String query = "SELECT group.curse, SUM(lesson.quantityhours) " +
                "AS SUM_quantityhours FROM test.group JOIN test.exercise ON group.id = exercise.idgroup " +
                "JOIN test.lesson ON lesson.id = exercise.idlesson GROUP BY group.curse ORDER " +
                "BY SUM_quantityhours DESC LIMIT 1 ";
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String groupcurse = resultSet.getString("group_curse");
            int quantityhours = resultSet.getInt("quantityhours");
            System.out.printf("group_curse  - %s, count quantityhours = %d.", groupcurse, quantityhours);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<Exercise> getInfoNameLesson(String history) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT *  FROM test.lesson JOIN test.exercise " +
                "ON lesson.id = exercise.idlesson WHERE lesson.name = ?");
        statement.setString(1, history);
        Exercise exercise = new Exercise();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            exercise.setId(resultSet.getInt("id"));
            exercise.setName(resultSet.getString("name"));
            exercise.setQuantityhours(resultSet.getInt("quantityhours"));
            exercise.setForm(resultSet.getString("form"));
            exercise.setId(resultSet.getInt("id"));
            exercise.setIdgroup(resultSet.getInt("idgroup"));
            exercise.setIdlesson(resultSet.getInt("idlesson"));
            exercise.setForm(resultSet.getString("topic"));
        }
        exerciseArrayList.add(exercise);
        return exerciseArrayList;
    }

    public void printInfoNameLesson(List<Exercise> exercises) {
        exercises.addAll(exerciseArrayList);
        System.out.println(exercises);
    }

    public List<Exercise> getMinHoursLesson(int value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT name, MIN(lesson.quantityhours) " +
                "AS MIN_quantityhours FROM test.lesson WHERE quantityhours <= ?");
        statement.setInt(1, value);
        Exercise exercise = new Exercise();
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            exercise.setName(resultSet.getString("name"));
            exercise.setQuantityhours(resultSet.getInt("quantityhours"));
        }
        exerciseArrayList.add(exercise);
        return exerciseArrayList;
    }

    public void printMinHoursLesson(List<Exercise> exercises) {
        // todo
    }

    public List<Exercise> getGroupMaxHours() throws SQLException {
        String query = "SELECT group.curse, SUM(lesson.quantityhours) " +
                "AS SUM_quantityhours FROM test.group JOIN test.exercise ON group.id = exercise.idgroup " +
                "JOIN test.lesson ON lesson.id = exercise.idlesson GROUP BY group.curse ORDER " +
                "BY SUM_quantityhours DESC LIMIT 1 ";
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        Exercise exercise = new Exercise();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            exercise.setIdgroup(resultSet.getInt("idgroup"));
            exercise.setQuantityhours(resultSet.getInt("quantityhours"));
        }
        exerciseArrayList.add(exercise);
        return exerciseArrayList;
    }

    public void printGroupMaxHours(List<Exercise> exercises) {
        // todo
    }
}
