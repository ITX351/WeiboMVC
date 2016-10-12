package hit.weibo.persistence.repository;

import hit.weibo.persistence.database.MySQLConnection;
import hit.weibo.persistence.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ITX351 on 2016/10/12.
 */
public class UserRepository {
    private static List<UserEntity> ResultSetToList(ResultSet resultSet) throws SQLException {
        List<UserEntity> userEntities = new ArrayList<>();
        while (resultSet.next()) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(resultSet.getInt("id"));
            userEntity.setName(resultSet.getString("name"));
            userEntity.setPassword(resultSet.getString("password"));
            userEntities.add(userEntity);
        }
        return userEntities;
    }

    public static List<UserEntity> findByNameAndPassword(String name, String password) throws SQLException {
        String sql = "select * from `User` where `name` = '" + name + "' and `password` = '" + password + "'";
        return ResultSetToList(MySQLConnection.Query(sql));
    }
}
