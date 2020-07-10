package cn.nick.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;    //id
    private String nickname;    //昵称
    private String email;   //邮箱
    private String content;     //内容
    private String avarat;  //头像地址
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;    //创建时间

    private Blog blog;
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
}
