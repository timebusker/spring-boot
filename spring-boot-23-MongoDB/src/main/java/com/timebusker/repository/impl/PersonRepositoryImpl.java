package com.timebusker.repository.impl;

import com.timebusker.entity.PersonEntity;
import com.timebusker.repository.AbstractBasicRepository;
import com.timebusker.repository.PersonRepository;
import com.timebusker.utils.MongoDBPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @DESC:AbstractBasicRepositoryImpl
 * @author:timebusker
 * @date:2018/9/6
 */
@Repository("personRepository")
public class PersonRepositoryImpl implements AbstractBasicRepository<PersonEntity> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PersonEntity findOneByParams(Map params) {
        Query query = new Query();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).is(params.get(key)));
        }
        PersonEntity personEntity = mongoTemplate.findOne(query, PersonEntity.class);
        return personEntity;
    }

    @Override
    public List<PersonEntity> findByParams(Map params) {
        Query query = new Query();
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).lt(params.get(key)));
        }
        List<PersonEntity> list = mongoTemplate.find(query, PersonEntity.class);
        return list;
    }

    @Override
    public List<PersonEntity> findWithPageByParams(Map params) {
        Query query = new Query();
        MongoDBPageable pageable = new MongoDBPageable(2);
        Iterator<String> iterator = params.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            query.addCriteria(Criteria.where(key).lt(params.get(key)));
        }
        Long allcount = mongoTemplate.count(query, PersonEntity.class);
        List<PersonEntity> list = mongoTemplate.find(query.with(pageable), PersonEntity.class);
        Page<PersonEntity> page = new PageImpl<PersonEntity>(list, pageable, allcount);
        return page.getContent();
    }
}
