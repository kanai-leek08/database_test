import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {

    private final Statement con;

    public DataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:sqlite.db";
            Connection conn = DriverManager.getConnection(url);
            this.con = conn.createStatement();
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private ResultSet query(String sql){
        try {
            return con.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Boolean execute(String sql) {
        try {
            return con.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List find(String sql) {
        ResultSet rs = query(sql);
        ResultSetMetaData md;
        ArrayList list = new ArrayList();
        try {
            md = rs.getMetaData();
            while (rs.next()){
                Map row = new HashMap();
                for(int i = 1; i <= md.getColumnCount(); ++i){
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

}

