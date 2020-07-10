package cn.nick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {
    private Long id;
    private String title;
    private boolean recommend;
    private Date createTime;
}
