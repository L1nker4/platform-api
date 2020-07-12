package wang.l1n.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import wang.l1n.api.entity.User;

/**
 * @author ：L1nker4
 * @date ： 创建于  2020/7/12 16:08
 * @description：
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
}
