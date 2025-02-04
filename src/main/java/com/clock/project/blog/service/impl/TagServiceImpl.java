package com.clock.project.blog.service.impl;

import com.clock.common.utils.ConvertUtils;
import com.clock.common.utils.ObjectUtils;
import com.clock.common.utils.SecurityUtils;
import com.clock.common.utils.StringUtils;
import com.clock.project.blog.service.TagService;
import com.clock.project.blog.domain.Tag;
import com.clock.project.blog.domain.TagMapping;
import com.clock.project.blog.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @className: TagServiceImpl
 * @description:
 * @author: Clock
 * 11/22/19
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public List<Tag> selectTagList(Tag tag) {
        return tagMapper.selectTagList(tag);
    }

    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    @Override
    public Tag selectTagById(Long id) {
        return tagMapper.selectTagById(id);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagByIds(String ids) {
        return tagMapper.deleteTagByIds(ConvertUtils.toLongArray(ids), SecurityUtils.getUsername());
    }

    @Override
    public int deleteTagMapping(TagMapping tagMapping) {
        return tagMapper.deleteTagMapping(tagMapping);
    }

    @Override
    public Tag selectTagByTitle(String title) {
        return tagMapper.selectTagByTitle(title);
    }

    @Override
    public int insertTagMapping(TagMapping tagMapping) {
        return tagMapper.insertTagMapping(tagMapping);
    }

    @Override
    public void updateTagMapping(Long id, List<String> tagTitleList) {
        //删除该Id下的所有关联
        TagMapping tagMapping = TagMapping.builder()
                .build();
        deleteTagMapping(tagMapping);

        if (ObjectUtils.isNotEmpty(tagTitleList)) {
            for (String title : tagTitleList) {
                //搜索所有的tag
                Tag tag = selectTagByTitle(title.trim());
                if (tag != null) {
                    tagMapping.setTagId(tag.getId());
                    insertTagMapping(tagMapping);
                } else {
                    Tag temp = new Tag(title.trim(), StringUtils.format("rgba({}, {}, {}, {})", getRandomNum(255), getRandomNum(255), getRandomNum(255), 1));
                    insertTag(temp);
                    tagMapping.setTagId(temp.getId());
                    insertTagMapping(tagMapping);
                }
            }
        }
    }

    @Override
    public List<Tag> selectTagListByBlogId(Long id) {
        return tagMapper.selectTagListByType(id);
    }

    /**
     * 获取随机数
     *
     * @param num 最大范围
     * @return 随机数
     */
    private int getRandomNum(int num) {
        Random random = new Random();
        return random.nextInt(num);
    }

}
