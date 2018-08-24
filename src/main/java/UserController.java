import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    public void create(Map<String, String> params) {
        // params から 値を受け取る
        // 値を反映させた SQL を作成する
        // execute メソッドに作成した SQL を渡して実行させる
        String name = params.get("name");
        String age = params.get("age");
        String sql = String.format("insert into user (name, age) values ('%s', %s)", name, age);
        new DataBase().execute(sql);
    }

    public List<Map> searchByAge(Integer age) {
        String query = String.format("select * from user where age = %d;", age);
        return new DataBase().find(query);
    }

    public List<Map> searchByName(String name) {
        ArrayList<Map> list = new ArrayList<>();

        Map<String, Object> user = new HashMap<>();
        user.put("name", "onojun");
        user.put("age", 32);

        list.add(user);

        return list;
    }

    public List<Map> searchByAgeAndName(Integer age, String name) {
        ArrayList<Map> list = new ArrayList<>();

        Map<String, Object> user = new HashMap<>();
        user.put("name", "Itamae");
        user.put("age", 25);

        list.add(user);

        return list;
    }
}
