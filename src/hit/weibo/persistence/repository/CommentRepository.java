package hit.weibo.persistence.repository;

import hit.weibo.persistence.database.MySQLConnection;
import hit.weibo.persistence.entity.CommentEntity;
import hit.weibo.persistence.entity.UserEntity;
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
public class CommentRepository {
    private static List<CommentEntity> ResultSetToList(ResultSet resultSet) throws SQLException {
        List<CommentEntity> commentEntities = new ArrayList<>();
        while (resultSet.next()) {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setId(resultSet.getInt("id"));
            commentEntity.setCommenter(resultSet.getInt("commenter"));
            commentEntity.setContent(resultSet.getString("content"));
            commentEntity.setWeibo_id(resultSet.getInt("weibo_id"));
            commentEntity.setCreateAt(resultSet.getTimestamp("createAt"));
            try { commentEntity.setCommenterName(resultSet.getString("commenterName")); } catch (SQLException ignored) { }
            commentEntities.add(commentEntity);
        }
        return commentEntities;
    }

    static List<CommentEntity> findByWeiboId(int weibo_id) throws SQLException {
        String sql = "select `comment`.`id`, `comment`.`commenter`, `user`.`name` commenterName, `comment`.`weibo_id`, " +
                " `comment`.`content`, `comment`.`createAt` from `comment` join `user` on " +
                "`comment`.`commenter` = `user`.`id` where `comment`.`weibo_id` = " + Helper.toString(weibo_id) +
                " order by `comment`.`createAt` asc";
        return ResultSetToList(MySQLConnection.Query(sql));
    }

    public static void deleteByWeiboId(int weibo_id) throws SQLException {
        String sql = "delete from `comment` where `weibo_id` = " + Helper.toString(weibo_id);
        MySQLConnection.ExecuteSQL(sql);
    }

    public static void insertComment(int commenter, String content, int weibo_id) {
        String sql = String.format("insert into `comment`(`id`, `commenter`, `weibo_id`, `content`, `createAt`) " +
                "values(%d, %d, %d, '%s', '%s')", Helper.getRandInt(), commenter, weibo_id, content, Helper.getCurDateTime());
        MySQLConnection.ExecuteSQL(sql);
    }

    public static void deleteComment(int comment_id) {
        String sql = "delete from `comment` where `comment`.`id` = " + Helper.toString(comment_id);
        MySQLConnection.ExecuteSQL(sql);
    }
}
