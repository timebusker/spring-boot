package cn.timebusker.test;

import cn.timebusker.App;
import cn.timebusker.model.one2one.Address;
import cn.timebusker.model.one2one.User;
import cn.timebusker.repository.one2one.AddressRepository;
import cn.timebusker.repository.one2one.UserRepository;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@ActiveProfiles("dev")
public class One2oneTest {

    @Resource
    AddressRepository addressRepository;

    @Resource
    UserRepository userRepository;

    /**
     * 单独保存user
     * success()
     */
    @Test
    public void test1() throws Exception {
        for (int i = 0; i <= 27; i++) {
            User user = new User("a_" + System.currentTimeMillis());
            userRepository.save(user);
        }
    }

    /**
     * 分页测试
     */
    @Test
    public void test11() {
        Pageable pageable = new PageRequest(0, 15, new Sort(Sort.Direction.DESC, "userId"));
        Page<User> page = userRepository.findAll(pageable);
        System.out.println(JSON.toJSONString(page));
    }

    @Test
    public void test12() {
        Pageable pageable = new PageRequest(0, 15, new Sort(Sort.Direction.DESC, "userId"));
        Page<User> page = userRepository.queryByUserIdGreaterThan(453, pageable);
        System.err.println(JSON.toJSONString(page));
    }

    @Test
    public void test13() {
        Pageable pageable = new PageRequest(1, 15, new Sort(Sort.Direction.DESC, "userId"));
        Page<User> page = userRepository.findAll(pageable);
        System.err.println(JSON.toJSONString(page));
    }


    /**
     * 单独保存address
     * failure: attempted to assign id from null one-to-one property [cn.timebusker.model.one2one.Address.user]
     */
    @Test
    public void test2() {
        Address address = new Address("一二一大街", "昆明市");
        addressRepository.save(address);
    }
}
