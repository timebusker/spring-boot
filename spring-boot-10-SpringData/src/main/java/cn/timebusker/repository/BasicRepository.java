package cn.timebusker.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 使用同一接口继承需要标记为@NoRepositoryBean，避免被spring-data-jpa识别为repository接口
 * <p>
 * 在做项目时创建对象的功能会交给Spring去管理在扫描Repository层包时会扫描到BaseRepository接口 ;
 * 所有对象类接口都会继承此接口 为了告诉JPA不要创建对应接口的bean对象 就在类上加注解@NoRepositoryBean
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BasicRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

}