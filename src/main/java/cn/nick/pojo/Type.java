package cn.nick.pojo;

import cn.nick.dto.IndexBlog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type {
    private Long id;    //id
    private String name;    //名称

    private List<IndexBlog> blogs = new ArrayList<>();
}
