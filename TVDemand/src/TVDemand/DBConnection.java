package TVDemand;

import java.sql.*;
public class DBConnection {
private static final String DB_URL = "jdbc:mariadb://localhost:3306/tvondemand";

    public Connection con;

public Connection createConnection() throws SQLException
{
    con = DriverManager.getConnection(DB_URL,"root", "");
    return con;
}
}
