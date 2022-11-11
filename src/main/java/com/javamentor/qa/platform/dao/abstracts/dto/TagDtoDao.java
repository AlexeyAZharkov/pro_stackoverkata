package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.IgnoredTagDto;
import com.javamentor.qa.platform.models.dto.RelatedTagDto;
import com.javamentor.qa.platform.models.dto.TagDto;

import java.util.List;

public interface TagDtoDao {
    List<TagDto> getTop3TagsByUserId(Long id);

    List<IgnoredTagDto> getIgnoredTagByAuthUser(Long id);

    List<RelatedTagDto> getTop10RelatedTags(Long tagId);
}
