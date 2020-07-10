package cn.nick.service;

import cn.nick.dto.SearchBlog;
import cn.nick.mapper.TagMapper;
import cn.nick.pojo.Tag;
import com.mysql.cj.xdevapi.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public int addTag(Tag tag) {
        return tagMapper.addTag(tag);
    }
    @Transactional
    @Override
    public Tag queryTagByName(String name) {
        return tagMapper.queryTagByName(name);
    }
    @Transactional
    @Override
    public Tag queryTagById(Long id) {
        return tagMapper.queryTagById(id);
    }
    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagMapper.listTag();
    }
    @Transactional
    @Override
    public int updTag(Tag tag) {
        return tagMapper.updTag(tag);
    }
    @Transactional
    @Override
    public void delTag(Long id) {
        tagMapper.delTag(id);
    }

    //获得tagIds后将其转换成tag的List
    public List<Tag> changeTagsToList(String tagIds){
        List<Long> longs = new ArrayList<>();
        if (!"".equals(tagIds) && tagIds != null) {
            String[] idarray = tagIds.split(",");
            for (int i=0; i < idarray.length;i++) {
                longs.add(new Long(idarray[i]));
            }
        }
        List<Tag> list = new ArrayList<>();
        for (Long aLong : longs) {
            list.add(tagMapper.queryTagById(aLong));
        }
        return list;
    }

    @Override
    public List<Tag> queryTagAndBlog() {
        return tagMapper.queryTagAndBlog();
    }


    @Override
    public List<Tag> sortTag(List<Tag> tags) {

        Collections.sort(tags, new Comparator<Tag>() {
            @Override
            public int compare(Tag o1, Tag o2) {
                int hits0 = o1.getBlogs().size();
                int hits1 = o2.getBlogs().size();
                if(hits1>hits0){
                    return 1;
                }else if(hits1 == hits0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        List<Tag> tags1 = new ArrayList<>();
        //获取前5条
        for(int i=0;i<5;i++){
            tags1.add(tags.get(i));
        }
        return tags;
    }

    @Override
    public List<Tag> queryTagAndBlogById(SearchBlog searchBlog) {
        return null;
    }

    @Override
    public List<Tag> selectFive(List<Tag> tags) {
        List<Tag> tags1 = new ArrayList<>();
        //获取前5条
        for(int i=0;i<5;i++){
            tags1.add(tags.get(i));
        }
        return tags1;
    }
}
