import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private final Statement con;

    public DataBase() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:sqlite.db";
        Connection conn = DriverManager.getConnection(url);
        this.con = conn.createStatement();

    }

    private ResultSet query(String sql) throws SQLException {
        return con.executeQuery(sql);
    }

    public Boolean execute(String sql) throws SQLException {
        return con.execute(sql);
    }

    public List find(String sql) throws SQLException{
        ResultSet rs = query(sql);
        ResultSetMetaData md = rs.getMetaData();
        ArrayList list = new ArrayList();
        while (rs.next()){
            Map row = new HashMap();
            for(int i = 1; i <= md.getColumnCount(); ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }

}

