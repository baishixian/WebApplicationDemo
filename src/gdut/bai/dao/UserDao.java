package gdut.bai.dao;

import gdut.bai.entity.UserEntity;

import java.util.List;

/**
 * 数据访问对象 DAO
 * 作为用户数据的访问接口
 *
 * @author baishixian
 */
public interface UserDao {

    String insertUser(UserEntity user);

    String updateUser(UserEntity user);

    List<UserEntity> queryInfo(String type, Object value);

}
