package ancientblockplugin.ancientblock.database;

import org.bukkit.entity.Player;

import java.sql.*;

public class Database {

    private Connection connection;

    public Connection getConnection() throws SQLException {

        if(connection != null){
            return connection;
        }

        String url = "jdbc:mysql://localhost/minecraft_server_data_base";
        String user = "root";
        String password = "";

        this.connection = DriverManager.getConnection(url, user, password);

        System.out.println("Connected to the database");

        return this.connection;
    }

    public void initializeDatabase() throws SQLException{

        Statement statement = getConnection().createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS login(uuid varchar(36) primary key, password varchar(32), name varchar(16))";
        statement.execute(sql);

        statement.close();
        System.out.println("created login in database");
    }

    public void register(String password, Player player) throws  SQLException{
        Statement statement = getConnection().createStatement();
        String sql = "INSERT INTO `login` (`uuid`, `password`, `name`) VALUES ('" + player.getUniqueId() + "', MD5('" + password + "'), '"+ player.getName() +"');";
        statement.execute(sql);
        statement.close();
    }

    public boolean login(String password, Player player) throws SQLException{
        Statement statement = getConnection().createStatement();
        String sql = "SELECT * FROM `login` WHERE `password` = MD5('" + password + "') AND `uuid` = '" + player.getUniqueId() + "';";
        ResultSet results = statement.executeQuery(sql);

        return results!=null;
    }
}
