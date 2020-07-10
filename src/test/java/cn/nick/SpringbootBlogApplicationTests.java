package cn.nick;

import cn.nick.dto.BlogQuery;
import cn.nick.dto.DateTime;
import cn.nick.dto.IndexBlog;
import cn.nick.dto.SearchBlog;
import cn.nick.mapper.BlogMapper;
import cn.nick.mapper.TagMapper;
import cn.nick.mapper.TypeMapper;
import cn.nick.mapper.UserMapper;
import cn.nick.pojo.Tag;
import cn.nick.pojo.Type;
import cn.nick.pojo.User;
import cn.nick.service.TagService;
import javafx.scene.control.TableFocusModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootBlogApplicationTests {
    @Autowired
    BlogMapper blogMapper;

    @Autowired
    TagMapper tagMapper;

    @Autowired
    TypeMapper typeMapper;

    @Autowired
    TagService tagService;

    @Test
    void contextLoads() {
//            List<IndexBlog> list =  blogMapper.queryIndexBlog();
//            IndexBlog blogQuery = list.get(0);
//        System.out.println(blogQuery.getTitle());

//        List<Tag> list = tagMapper.queryTagAndBlog();
//        Tag tag = list.get(0);
//        System.out.println(tag.getBlogs().get(0));

//        List<Type> list = typeMapper.queryTypeAndBlog();
////       Type type = list.get(0);
////       System.out.println(type.getBlogs().get(0));
       // List<IndexBlog> indexBlog = blogMapper.queryAllBlog("hex");
//    String tags="2,3,4";
//    List<Tag> list = tagService.changeTagsToList(tags);
//        System.out.println(list.get(1).getId());

        List<DateTime> list = blogMapper.queryAllDate();
        System.out.println(list.get(0).getYear());
   }

}
