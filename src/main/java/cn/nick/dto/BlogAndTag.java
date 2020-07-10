package cn.nick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogAndTag {
    private Long id;
    private Long blogId;
    private Long tagId;
}
