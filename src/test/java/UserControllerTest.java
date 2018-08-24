import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class UserControllerTest {

    @Before
    public void setup() {
//        new DataBase().execute("delete from user;");
    }

    @Test
    public void create_user() {
        Fixture[] fixtures = {
                new Fixture("Johny", 32),
                new Fixture("Onojun", 25),
        };
        for (Fixture fixture : fixtures) {
            new DataBase().execute("delete from user;");
            //given
            String name = fixture.name;
            Integer age = fixture.age;
            Map<String, String> params = get_map(name, age);
            //when
            new UserController().create(params);

            //then
            List<Map> list = new DataBase().find("select * from user;");
            assertEquals(1, list.size());
            Map user = list.get(0);
            assertEquals(name, user.get("name"));
            assertEquals(age, user.get("age"));
        }
    }

    @Test
    public void search_by_age() {
        //given
        new DataBase().execute("delete from user;");
        String name = "onojun";
        Integer age = 32;
        Map<String, String> params = get_map(name, age);
        new UserController().create(params);

        //when
        List<Map> list = new UserController().searchByAge(age);

        //then
        assertEquals(1, list.size());
        Map user = list.get(0);
        assertEquals(name, user.get("name"));
        assertEquals(age, user.get("age"));
    }

    @Test
    public void search_by_age_2() {
        //given
        new DataBase().execute("delete from user;");
        String name = "Itamae";
        Integer age = 22;
        Map<String, String> params = get_map(name, age);
        new UserController().create(params);

        //when
        List<Map> list = new UserController().searchByAge(age);

        //then
        assertEquals(1, list.size());
        Map user = list.get(0);
        assertEquals(name, user.get("name"));
        assertEquals(age, user.get("age"));
    }

    @Test
    public void search_by_name() {
        //given
        new DataBase().execute("delete from user;");
        String name = "onojun";
        Integer age = 32;
        Map<String, String> params = get_map(name, age);
        new UserController().create(params);

        //when
        List<Map> list = new UserController().searchByName(name);

        //then
        assertEquals(1, list.size());
        Map user = list.get(0);
        assertEquals(name, user.get("name"));
        assertEquals(age, user.get("age"));
    }

    @Test
    public void search_by_age_and_name() {
        //given
        new DataBase().execute("delete from user;");
        String name = "Itamae";
        Integer age = 25;
        Map<String, String> params = get_map(name, age);
        new UserController().create(params);

        //when
        List<Map> list = new UserController().searchByAgeAndName(age, name);

        //then
        assertEquals(1, list.size());
        Map user = list.get(0);
        assertEquals(name, user.get("name"));
        assertEquals(age, user.get("age"));
    }

    private Map<String, String> get_map(String name, Integer age) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("age", age.toString());
        return params;
    }

    private class Fixture {
        private String name;
        private int age;

        public Fixture(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
