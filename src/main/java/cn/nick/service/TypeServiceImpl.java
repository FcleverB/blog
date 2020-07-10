package cn.nick.service;

import cn.nick.mapper.TypeMapper;
import cn.nick.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int addType(Type type) {
        return typeMapper.addType(type);
    }
    @Transactional
    @Override
    public Type queryTypeById(Long id) {
        return typeMapper.queryTypeById(id);
    }

    @Override
    public Type queryTypeByName(String name) {
        return typeMapper.queryTypeByName(name);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeMapper.listType();
    }
    @Transactional
    @Override
    public int updType(Type type) {
        return typeMapper.updType(type);
    }
    @Transactional
    @Override
    public void delType(Long id) {
        typeMapper.delType(id);
    }

    @Override
    public List<Type> queryTypeAndBlog(){
        return typeMapper.queryTypeAndBlog();
    }

    @Override
    public List<Type> sortType(List<Type> types) {

        Collections.sort(types, new Comparator<Type>() {
            @Override
            public int compare(Type o1, Type o2) {
                int hits0 = o1.getBlogs().size();
                int hits1 = o2.getBlogs().size();
                if (hits1 > hits0) {
                    return 1;
                } else if (hits1 == hits0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        return types;
    }

    //获取前5条
    public List<Type> selectFive(List<Type> types){
        List<Type> types1 = new ArrayList<>();
        //获取前5条
        for (int i = 0; i < 5; i++) {
            types1.add(types.get(i));
        }
        return types1;
    }
    @Override
    public List<Type> queryAllTypeBlog(Long id) {
        return typeMapper.queryAllTypeBlog(id);
    }
}
