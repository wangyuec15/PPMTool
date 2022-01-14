package wangyuec15.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wangyuec15.ppmtool.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
