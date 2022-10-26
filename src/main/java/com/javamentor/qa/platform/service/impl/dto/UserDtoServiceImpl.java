package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.abstracts.repository.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.TagDto;
import com.javamentor.qa.platform.models.dto.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.UserDtoService;
import com.javamentor.qa.platform.service.impl.repository.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDtoServiceImpl implements UserDtoService {
    private final TagDtoDao tagDtoDao;
    private final UserDtoDao userDtoDao;

    public UserDtoServiceImpl(TagDtoDao tagDtoDao, UserDtoDao userDtoDao) {
        this.tagDtoDao = tagDtoDao;
        this.userDtoDao = userDtoDao;
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        Optional<UserDto> userDto = userDtoDao.getById(id);
        userDto.ifPresent(dto -> dto.setListTop3TagDto(tagDtoDao.getTop3TagsByUserId(id)));
        return userDto;
    }
}