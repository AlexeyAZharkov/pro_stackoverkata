package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TagConverter {
    public abstract List<Tag> tagDtoListToTagList(List<TagDto> tagDto);
    public abstract List<TagDto> tagListToTagDtoList(List<Tag> tag);
}