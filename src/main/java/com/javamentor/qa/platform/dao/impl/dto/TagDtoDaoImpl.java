package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagDto> getTop3TagsByUserId(Long id) {
        return entityManager.createQuery("""
                SELECT new com.javamentor.qa.platform.models.dto.TagDto(
                t.id,
                t.name,
                t.description)from Tag t join t.questions as q where q.user.id = :id""", TagDto.class).setParameter("id", id)
                .setMaxResults(3).getResultList();
    }
}
