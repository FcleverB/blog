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
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;    //id
    private String nickname;    //昵称
    private String username;    //用户名
    private String password;    //密码
    private String avatar;      //头像
    private Integer type;   //类型
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;    //更新时间

    private List<Blog> blogs = new ArrayList<>();

    //注册用的构造方法
    public User(String username, String password) {
        this.nickname = username;
        this.username = username;
        this.password = password;
        this.avatar = null;
        this.type = 1;
        this.createTime = new Date();
        this.updateTime = new Date();
        this.blogs = null;
    }
}
