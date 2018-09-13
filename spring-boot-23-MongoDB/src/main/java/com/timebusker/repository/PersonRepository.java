package com.timebusker.repository;

import com.timebusker.entity.PersonEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @DESC:PersonRepository
 * @author:timebusker
 * @date:2018/9/6
 */
public interface PersonRepository extends AbstractBasicRepository, MongoRepository<PersonEntity, Integer> {

}
