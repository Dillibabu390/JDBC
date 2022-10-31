
import java.sql.*;
import java.util.Scanner;

public class JdbcConnection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id:");
        int id = sc.nextInt();
        System.out.println("Enter name:");
        String name = sc.next();
      /*  System.out.println("Enter rollno:");
        double mark= sc.nextDouble();*/

        System.out.println("enter the deleted item: ");
        int delid =sc.nextInt();


        deleteQuery(delid);
        selectQuery();
        //selectQuery(id,name);
        updateQuery(id, name);
    }

    private static void updateQuery(int id, String name) {
        String url = "jdbc:mySql://localhost:3306/task";
        String userName = "root";
        String password = "1234";


        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            if (connection != null) {
                System.out.println("Connection   success");
                String query = "insert into users values (?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, id);
                ps.setString(2, name);
                // ps.setDouble(3, mark);
                int result = ps.executeUpdate();

                if (result == 1) {
                    System.out.println("SUCCESS");
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void selectQuery() {
        String url = "jdbc:mySql://localhost:3306/task";
        String user = "root";
        String password = "1234";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                String query = "select * from users";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    System.out.print("ID: " + rs.getInt(1));
                    System.out.print(" NAME: " + rs.getString("name"));

                }
                connection.close();


            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteQuery(int id ) {
        String url = "jdbc:mySql://localhost:3306/task";
        String user = "root";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                String query = "delete from users where id=" + id;
                PreparedStatement del = connection.prepareStatement(query);

                int result = del.executeUpdate();
                if (result == 1) {
                    System.out.println("Deleted SUCCESS");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}