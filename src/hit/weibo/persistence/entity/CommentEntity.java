package hit.weibo.persistence.entity;

import java.sql.Timestamp;

/**
 * Created by ITX351 on 2016/10/13.
 */
public class CommentEntity {
    private Integer id;
    private Integer commenter;
    private String commenterName;
    private Integer weibo_id;
    private String content;
    private Timestamp createAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommenter() {
        return commenter;
    }

    public void setCommenter(Integer commenter) {
        this.commenter = commenter;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public Integer getWeibo_id() {
        return weibo_id;
    }

    public void setWeibo_id(Integer weibo_id) {
        this.weibo_id = weibo_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }
}
