package hit.weibo.persistence.repository;

import hit.weibo.persistence.database.MySQLConnection;
import hit.weibo.persistence.entity.UserEntity;
import hit.weibo.persistence.entity.WeiboEntity;
import hit.weibo.util.Helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ITX351 on 2016/10/13.
 */
public class WeiboRepository {
    private static List<WeiboEntity> ResultSetToList(ResultSet resultSet) throws SQLException {
        List<WeiboEntity> weiboEntities = new ArrayList<>();
        while (resultSet.next()) {
            WeiboEntity weiboEntity = new WeiboEntity();
            weiboEntity.setId(resultSet.getInt("id"));
            weiboEntity.setCreator(resultSet.getInt("creator"));
            weiboEntity.setContent(resultSet.getString("content"));
            weiboEntity.setCreateAt(resultSet.getTimestamp("createAt"));
            try { weiboEntity.setCreatorName(resultSet.getString("creatorName")); } catch (SQLException ignored) { }
            weiboEntity.setComments(CommentRepository.findByWeiboId(weiboEntity.getId()));
            weiboEntities.add(weiboEntity);
        }
        return weiboEntities;
    }

    public static List<WeiboEntity> showAll() throws SQLException {
        String sql = "select `weibo`.`id`, `weibo`.`creator`, `user`.`name` creatorName, `weibo`.`content`," +
                " `weibo`.`createAt` from `weibo` join `user` on `weibo`.`creator` = `user`.`id`" +
                " order by `weibo`.`createAt` desc";
        return ResultSetToList(MySQLConnection.Query(sql));
    }

    public static void insertWeibo(int creator, String content) {
        SimpleDateFormat time= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String curDateTime = time.format(new Date());
        System.out.println("curDate = " + curDateTime);
        String sql = String.format("insert into `weibo`(`id`, `creator`, `content`, `createAt`) " +
                "values(NULL, %d, '%s', '%s')", creator, content, curDateTime);
        System.out.println("sql = " + sql);
        MySQLConnection.ExecuteSQL(sql);
    }

    public static void deleteWeibo(int weibo_id) {
        String sql = "delete from `weibo` where `weibo`.`id` = " + Helper.toString(weibo_id);
        MySQLConnection.ExecuteSQL(sql);
    }
}
