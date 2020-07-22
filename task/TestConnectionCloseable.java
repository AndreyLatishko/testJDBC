package task;

import java.sql.*;
import java.util.Properties;

public class TestConnectionCloseable {

    private static Connection connection;
    private static PreparedStatement statement;

    public static void main(String[] args) throws SQLException {
        queryFromNameLesson("History");
    }

    public TestConnectionCloseable() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        Properties props = new Properties();
        props.setProperty("password", "1234");
        props.setProperty("user", "root");
        props.setProperty("serverTimezone", "UTC");
        connection = DriverManager.getConnection(url, props);
    }

    private static void queryFromNameLesson(String value) throws SQLException {
        statement = connection.prepareStatement("SELECT *  FROM test.lesson JOIN test.exercise " +
                "ON lesson.id = exercise.idlesson WHERE lesson.name = ?");
        statement.setString(1,value);
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


    private static String queryMinHoursLesson(String value) {
        return "SELECT name, MIN(lesson.quantityhours) AS MIN_quantityhours FROM test.lesson " +
                "WHERE quantityhours <= 60";
    }

    private static String queryGroupSUM(String value) {
        return "SELECT group.curse, SUM(lesson.quantityhours) AS SUM_quantityhours FROM test.group " +
                "JOIN test.exercise ON group.id = exercise.idgroup JOIN test.lesson ON lesson.id = exercise.idlesson " +
                "GROUP BY group.curse ORDER BY SUM_quantityhours DESC LIMIT 1 ";
    }

}
