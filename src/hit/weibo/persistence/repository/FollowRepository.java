package hit.weibo.persistence.repository;

import hit.weibo.persistence.database.MySQLConnection;

import java.sql.SQLException;

/**
 * Created by ITX351 on 2016/10/14.
 */
public class FollowRepository {
    public static boolean findByFromAndTo(int from, int to) throws SQLException {
        String sql = String.format("select * from `follow` where `from` = %d and `to` = %d", from, to);
        return MySQLConnection.Query(sql).next();
    }

    public static void insertFollow(int from, int to) {
        String sql = String.format("insert into `follow`(`from`, `to`) values(%d, %d)", from, to);
        MySQLConnection.ExecuteSQL(sql);
    }

    public static void deleteFollow(int from, int to) {
        String sql = String.format("delete from `follow` where `from` = %d and `to` = %d", from, to);
        MySQLConnection.ExecuteSQL(sql);
    }
}
