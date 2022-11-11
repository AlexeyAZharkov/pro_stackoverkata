package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.IgnoredTagDto;
import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagDtoServiceImpl implements TagDtoService {
    private final TagDtoDao tagDtoDao;

    public TagDtoServiceImpl(TagDtoDao tagDtoDao) {
        this.tagDtoDao = tagDtoDao;
    }

    @Override
    public List<TagDto> getTop3TagsByUserId(Long id) {
        return tagDtoDao.getTop3TagsByUserId(id);
    }

    @Override
    public List<IgnoredTagDto> getIgnoredTagByAuthUser(Long id) {
        return tagDtoDao.getIgnoredTagByAuthUser(id);
    }

    @Override
    public List<RelatedTagDto> getTop10RelatedTags(Long tagId) {
        return tagDtoDao.getTop10RelatedTags(tagId);
    }
}
