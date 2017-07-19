package cn.timebusker.repository.one2one;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.timebusker.model.one2one.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
