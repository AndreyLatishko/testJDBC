package task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UniversityDataManager implements AutoCloseable {

    private Connection connection;
    private Exercise exercise;
    private Lesson lessons;
    private Group groups;
    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
    ArrayList<Lesson> lessonArrayList = new ArrayList<>();
    ArrayList<Group> groupArrayList = new ArrayList<>();

    public UniversityDataManager() throws SQLException, FileNotFoundException {
        try(InputStream inputStream = new FileInputStream("C:/Projects/testJDBC/src/config.properties")) {
            Properties props = new Properties();
            props.setProperty("serverTimezone", "UTC");
            connection = DriverManager.getConnection(props.getProperty("url"), props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<Exercise> getLessonByName(String lessonName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT *  FROM test.lesson JOIN test.exercise " +
                "ON lesson.id = exercise.idlesson WHERE lesson.name = ?");
        statement.setString(1, lessonName);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int quantityhours = resultSet.getInt("quantityhours");
            String form = resultSet.getString("form");
            int idE = resultSet.getInt("idE");
            int ifgroup = resultSet.getInt("idgroup");
            int idlesson = resultSet.getInt("idlesson");
            String topic = resultSet.getString("topic");
            exercise = new Exercise(id, name, quantityhours, form, idE, ifgroup, idlesson, topic);
        }
        exerciseArrayList.add(exercise);
        return exerciseArrayList;
    }

    public void printLessonByName(List<Exercise> exercises) {
        exercises.addAll(exerciseArrayList);
        System.out.println(exercises);
    }

    public List<Lesson> getMinHoursWithLesson(int value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT name, MIN(lesson.quantityhours) " +
                "AS MIN_quantityhours FROM test.lesson WHERE quantityhours <= ?");
        statement.setInt(1, value);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int minquantityhours = resultSet.getInt("quantityhours");
            lessons = new Lesson(name, minquantityhours);
        }
        lessonArrayList.add(lessons);
        return lessonArrayList;
    }

    public void printMinHoursWithLesson(List<Lesson> lessons) {
        lessons.addAll(lessonArrayList);
        System.out.println(lessons);
    }

    public List<Group> getMaxHoursForGroup() throws SQLException {
        String query = "SELECT group.curse, SUM(lesson.quantityhours) " +
                "AS SUM_quantityhours FROM test.group JOIN test.exercise ON group.id = exercise.idgroup " +
                "JOIN test.lesson ON lesson.id = exercise.idlesson GROUP BY group.curse ORDER " +
                "BY SUM_quantityhours DESC LIMIT 1 ";
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int idgroup = resultSet.getInt("idgroup");
            int quantityhours = resultSet.getInt("quantityhours");
            groups = new Group(idgroup,quantityhours);
        }
        groupArrayList.add(groups);
        return groupArrayList;
    }

    public void printMaxHoursForGroup(List<Group> groups) {
        groups.addAll(groupArrayList);
        System.out.println(groups);
    }
}
