package com.clock.project.blog.service;

import com.clock.project.blog.domain.Tag;
import com.clock.project.blog.domain.TagMapping;

import java.util.List;

/**
 * @className: TagService
 * @description:
 * @author: Clock
 * 11/22/19
 */
public interface TagService {
    /**
     * 获取Tag列表
     *
     * @param tag 带查询条件的Tag
     * @return tagList
     */
    List<Tag> selectTagList(Tag tag);

    /**
     * 新增Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int insertTag(Tag tag);

    /**
     * 根据Id查询tag
     *
     * @param id id
     * @return tag实体
     */
    Tag selectTagById(Long id);

    /**
     * 更新Tag
     *
     * @param tag tag实体
     * @return 受影响的行数
     */
    int updateTag(Tag tag);

    /**
     * 删除Tag
     *
     * @param ids tag的id
     * @return 受影响的行数
     */
    int deleteTagByIds(String ids);

    /**
     * 删除Tag的mapping,里面设置的有哪个字段的值,便以那个值作为条件进行删除
     *
     * @param tagMapping TagMapping
     * @return 受影响的行数
     */
    int deleteTagMapping(TagMapping tagMapping);

    /**
     * 根据Tag的title 和 type搜索Tag
     *
     * @param title Tag的title
     * @return Tag
     */
    Tag selectTagByTitle(String title);

    /**
     * 新增Tag Mapping映射关系
     *
     * @param tagMapping 映射关系
     * @return 受影响的行数
     */
    int insertTagMapping(TagMapping tagMapping);

    /**
     * 更新TagMapping
     *
     * @param id           id
     * @param tagTitleList list
     */
    void updateTagMapping( Long id, List<String> tagTitleList);

    /**
     * 根据Tag的type和Id获取该Id下的所有Tag
     *
     * @param id id
     * @return Tag list
     */
    List<Tag> selectTagListByBlogId(Long id);

}
