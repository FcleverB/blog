package cn.nick.dto;

import cn.nick.pojo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用于查询显示博客列表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private int recommend;
    private Long typeId;
    private Date createTime;
    private Date updateTime;
    private Type type;
    private int published;

}
