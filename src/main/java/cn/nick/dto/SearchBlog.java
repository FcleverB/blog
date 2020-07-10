package cn.nick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private Long typeId;
    private String title;
    private Long tagId;
    private String recommend;
    private int recommend2;
}
