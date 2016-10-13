package hit.weibo.persistence.database;



import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by ITX351 on 2016/10/12.
 */
public class MySQLConnection {

    private static Connection conn;

    static {
        try {
            LoadProperties();
            conn = ConnectDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String driverClass;
    private static String url;
    private static String username;
    private static String password;

    private static void LoadProperties() throws IOException, ClassNotFoundException, SQLException {
        InputStream inputStream = MySQLConnection.class.getClassLoader().getResourceAsStream("db-connection.properities");
        Properties properties = new Properties();
        properties.load(inputStream);

        driverClass = properties.getProperty("driverClass");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }

    private static Connection ConnectDatabase() {
        try {
            Class.forName(driverClass);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet Query(String sql){
        try{
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            //statement.close();
            return resultSet;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void ExecuteSQL(String sql){
        try{
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
