package cn.timebusker.web;

import cn.timebusker.model.UserEntity;
import cn.timebusker.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class indexController {

    private final static Logger logger = LoggerFactory.getLogger(indexController.class);

    @Autowired
    private UserService service;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public Object list() {
        List<UserEntity> list = new ArrayList<>();
        list = service.getList();
        Collections.sort(list);
        return list;
    }

    @RequestMapping(value = {"/add/{id}"}, method = RequestMethod.GET)
    public Object add(@PathVariable("id") long id) {
        UserEntity user = new UserEntity(id, id + "", Integer.valueOf(id + ""), new Date());
        List<UserEntity> list = new ArrayList<>();
        list = service.addUser(user);
        Collections.sort(list);
        return list;
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public Object update(@PathVariable("id") long id) {
        UserEntity user = new UserEntity(id, id + "", Integer.valueOf(id + ""), new Date());
        List<UserEntity> list = new ArrayList<>();
        list = service.updateUser(user);
        Collections.sort(list);
        return list;
    }

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public Object delete(@PathVariable("id") long id) {
        UserEntity user = new UserEntity(id, id + "", Integer.valueOf(id + ""), new Date());
        List<UserEntity> list = new ArrayList<>();
        list = service.deleteUser(user);
        Collections.sort(list);
        return list;
    }
}
