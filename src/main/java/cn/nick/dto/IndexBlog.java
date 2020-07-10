package cn.nick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexBlog {
    private Long id;
    private String title;
    private String firstPhoto;
    private String avatar;
    private Date createTime;
    private String description;
    private String typeName;
    private String nickname;
    private Integer views;
    private Long typeId;
}
