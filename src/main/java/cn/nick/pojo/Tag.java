package cn.nick.pojo;

import cn.nick.dto.IndexBlog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
    //用于标签页的展示
    private List<IndexBlog> indexBlogs = new ArrayList<>();
}
