package boss.portal.repository;

import boss.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/** 
* @author  yang
* @E-mail: 
* @date 创建时间：2018年2月6日 下午6:08:22 
* @version 1.0   
*/
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    
    User saveAndFlush(User user);

}
