package cn.nick.dto;

import cn.nick.pojo.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateTime {
    //获得归档的年份
    private String year;

    private List<Blog> blogs;
}
