package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.UserDtoDao;
import com.javamentor.qa.platform.dao.impl.repository.ReadWriteDaoImpl;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl extends ReadWriteDaoImpl<UserDto, Long> implements UserDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserDto> getById(Long id) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("""
                SELECT new com.javamentor.qa.platform.models.dto.UserDto(
                u.id,
                u.email,
                u.fullName,
                u.imageLink,
                u.city,
                (SELECT coalesce(sum (r.count), 0) from Reputation r where r.author.id = u.id),
                u.persistDateTime,
                (SELECT count (distinct va) + count (distinct vq) from VoteAnswer va, VoteQuestion  vq
                where va.user.id = u.id and vq.user.id = u.id))
                from User u where u.id=:id""", UserDto.class).setParameter("id", id));
    }
}
