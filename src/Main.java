import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String viewItaStudents = "CREATE VIEW italian_students AS SELECT name, surname " +
                "FROM students WHERE NATIONALITY = 'Italian'";
        String viewGerStudents = "CREATE VIEW italian_students AS SELECT name, surname " +
                "FROM students WHERE NATIONALITY = 'German'";
        String selectItaStudents ="SELECT name, surname FROM italian_students";
        String selectGerStudents ="SELECT name, surname FROM german_students";

        try {
            String url = "jdbc:mysql://localhost:3306/newdb";
            String user = "developer";
            String password = "passwordhere";

            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();
            statement.executeUpdate(viewItaStudents);
            statement.executeUpdate(viewGerStudents);

            //add IT students into array
            ArrayList<Student> italianStudents = new ArrayList<>();
            resultSet = statement.executeQuery(selectItaStudents);
            while(resultSet.next()){
                String itaName = resultSet.getString("name");
                String itaSurname = resultSet.getString("surname");
                Student italianStudent = new Student(itaName, itaSurname);
                italianStudents.add(italianStudent);
            }

            //add DE students into array
            ArrayList<Student> germanStudents = new ArrayList<>();
            resultSet = statement.executeQuery(selectGerStudents);
            while (resultSet.next()){
                String gerName = resultSet.getString("name");
                String gerSurname = resultSet.getString("surname");
                Student germanStudent = new Student(gerName,gerSurname);
                germanStudents.add(germanStudent);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
