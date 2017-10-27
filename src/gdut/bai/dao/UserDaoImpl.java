package gdut.bai.dao;

import gdut.bai.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.List;

/**
 * 数据访问对象 DAO
 * 作为用户数据的访问接口
 *
 * @author baishixian
 */
public class UserDaoImpl implements UserDao {

    private final Session session;

    // 提交数据的事务
    private Transaction transaction;

    public UserDaoImpl(Session session) {
        this.session = session;
    }

    @Override
    public String insertUser(UserEntity user) {
        String result;

        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            result = "用户：" + user.getUserName() + "注册成功！";
        } catch (Exception e) {
          //  showMessage("RegisterInfo error:" + e);
            e.printStackTrace();
            result = "注册失败：" + e;
        }

        return result;
    }

    @Override
    public String updateUser(UserEntity user) {
        String result;

        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            result = "用户：" + user.getUserName() + "信息更新成功！";
        } catch (Exception e) {
          //  showMessage("updateUser error:" + e);
            e.printStackTrace();
            result = "用户信息失败：" + e;
        }

        return result;
    }

    @Override
    public List<UserEntity> queryInfo(String type, Object value) {
        String sql = "from gdut.bai.entity.UserEntity as user where user." + type + "=?";

        System.out.println("queryInfo sql " + sql + " value = " + value);

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(sql);
            query.setParameter(0, value);
            List list = query.list();
            transaction.commit();
            return list;
        } catch (Exception e) {
            showMessage("queryInfo error:" + e);
            e.printStackTrace();
            return null;
        }
    }

    private void showMessage(String mess) {
        int type = JOptionPane.YES_NO_OPTION;
        String title = "提示信息";
        JOptionPane.showMessageDialog(null, mess, title, type);
    }
}
