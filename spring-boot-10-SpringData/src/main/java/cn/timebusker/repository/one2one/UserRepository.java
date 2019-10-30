package cn.timebusker.repository.one2one;

import cn.timebusker.model.one2one.User;
import cn.timebusker.repository.BasicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BasicRepository<User, Long> {

//    @Query("select userId,userName from t_user where userId >=?1")
//    Page<User> queryWithPageByParams(Integer userId, Pageable pageable);


    Page<User> queryByUserIdGreaterThan(Integer userId, Pageable pageable);

    Page<User> findAll(Pageable pageable);
}
