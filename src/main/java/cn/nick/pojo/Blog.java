package cn.nick.pojo;

import lombok.*;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Long id;    //博客id
    private String title;   //标题
    private String content;     //内容
    private String firstPhoto;     //首图
    private String flag;    //标记
    private Integer views;     //浏览次数
    private boolean shareStatement; //转载声明开启问题
    private boolean commentabled;    //评论是否开启
    private boolean published;  //是否是草稿
    private boolean recommend;      //是否推荐
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private String description; //描述


    //为了新增时设置的属性
    private Long typeId;
    private String tagIds;
    private Long userId;

    private Type type;
    private List<Tag> tags = new ArrayList<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
}
