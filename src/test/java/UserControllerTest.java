import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
public class UserControllerTest {

    public static class BaseBefore {
        protected  static DataBase con;
        @Before
        public void setUp() {
            this.con = new DataBase();
            con.execute("delete from user;");
        }
    }

    @RunWith(Theories.class)
    public static class SearchMethod extends BaseBefore {

        @Before
        public void setup() {
            con.execute("insert into user (name, age) values ('kanai', '28');");
            con.execute("insert into user (name, age) values ('daiki', '28');");
            con.execute("insert into user (name, age) values ('daiki', '29');");
        }

        @DataPoints
        public static String[][] patterns = {
            {"28", "kanai",    "1"},
            {"28", "no match", "0"},
            {"29", "daiki",    "2"},
            {"28", "daiki",    "1"},
            {"",   "",         "3"},
        };

        @Theory
        public void 検索パターン(String[] pattern) {
            //given
            Map<String, String> params = new HashMap<>();
            params.put("age", pattern[0]);
            params.put("name", pattern[1]);
            //when
            UserController userContorller = new UserController();
            userContorller.search(params);
            //then
            assertEquals("pattern: " + pattern[0]+pattern[1], Integer.parseInt(pattern[2]), userContorller.list.size());
        }
    }

    public static class CreateMethod extends BaseBefore {

        @Test
        public void create() {
            //given
            Map<String, String> params = new HashMap<String, String>();
            params.put("name", "kanai");
            params.put("age", "20");
            //when
            new UserController().create(params);
            //then
            List<Map> list = con.find("select * from user;");
            assertEquals(1, list.size());
            assertEquals("kanai", list.get(0).get("name"));
            assertEquals(20, list.get(0).get("age"));
        }

    }

}
