package cn.timebusker.repository;

import cn.timebusker.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * User Repository 接口
 */
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    void deleteById(Long id);

}
