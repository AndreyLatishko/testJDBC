package task;

import java.sql.*;
import java.util.*;

public class UniversityDataManager implements AutoCloseable {

    private Connection connection;
    private Exercise exercise;
    private Lesson lessons;
    private Group groups;
    ArrayList<Exercise> exerciseArrayList = new ArrayList<>();
    ArrayList<Lesson> lessonArrayList = new ArrayList<>();
    ArrayList<Group> groupArrayList = new ArrayList<>();


    public UniversityDataManager(DbProperties dbProperties) throws SQLException {
        Properties props = new Properties();
        props.setProperty("password", dbProperties.getPassword());
        props.setProperty("user", dbProperties.getUser());
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(dbProperties.getUrl(), props);
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
            int idE = resultSet.getInt("exercise.id");
            int idgroup = resultSet.getInt("idgroup");
            int idlesson = resultSet.getInt("idlesson");
            String topic = resultSet.getString("topic");
            exercise = new Exercise(id, name, quantityhours, form, idE, idgroup, idlesson, topic);
            exerciseArrayList.add(exercise);
        }
        return exerciseArrayList;
    }

    public void printLessonByName(List<Exercise> exercises) {
        for (Exercise s : exercises) {
            System.out.printf("Id = %d, name - %s, quantityhours = %d, form - %s, idE = %d, idgroup = %d, idlesson = %d, topic - %s.%n",
                    s.getId(), s.getName(), s.getQuantityhours(), s.getForm(), s.getIdE(), s.getIdgroup(), s.getIdlesson(), s.getTopic());
        }
    }

    public List<Lesson> getMinHoursWithLesson(int value) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT name, MIN(lesson.quantityhours) " +
                "AS MIN_quantityhours FROM test.lesson WHERE quantityhours <= ?");
        statement.setInt(1, value);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int minquantityhours = resultSet.getInt("MIN_quantityhours");
            lessons = new Lesson(name, minquantityhours);
            lessonArrayList.add(lessons);
        }
        return lessonArrayList;
    }

    public void printMinHoursWithLesson(List<Lesson> lessons) {
        for (Lesson s : lessons) {
            System.out.printf("Name - %s, MINquantityhours = %d.%n", s.getName(), s.getMinquantityhours());
        }
    }

    public List<Group> getMaxHoursForGroup() throws SQLException {
        String query = "SELECT course, SUM(lesson.quantityhours) " +
                "AS SUM_quantityhours FROM test.group JOIN test.exercise ON group.id = exercise.idgroup " +
                "JOIN test.lesson ON lesson.id = exercise.idlesson GROUP BY group.course ORDER " +
                "BY SUM_quantityhours DESC LIMIT 1 ";
        Statement statement = connection.createStatement();
        statement.executeQuery(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String idgroup = resultSet.getString("group.course");
            int quantityhours = resultSet.getInt("SUM_quantityhours");
            groups = new Group(idgroup, quantityhours);
            groupArrayList.add(groups);
        }
        return groupArrayList;
    }

    public void printMaxHoursForGroup(List<Group> groups) {
        for (Group s : groups) {
            System.out.printf("Idgroup = %s, quantityhours = %d.%n", s.getIdgroup(), s.getQuantityhours());
        }
    }
}
