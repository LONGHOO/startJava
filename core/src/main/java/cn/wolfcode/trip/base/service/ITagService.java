package cn.wolfcode.trip.base.service;

import cn.wolfcode.trip.base.domain.Tag;
import cn.wolfcode.trip.base.query.QueryObject;

import java.util.List;

public interface ITagService {

    void saveOrUpdate(Tag tag);

    List<Tag> listAll();

    List<Tag> getHotTag(QueryObject qo);

    Tag getById(Long id);
}
