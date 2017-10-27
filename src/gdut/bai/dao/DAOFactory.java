package gdut.bai.dao;

import org.hibernate.Session;

/**
 * DAO 工厂类
 * 单例模式
 *
 * @author baishixian
 */
public class DAOFactory {

    private static volatile DAOFactory mInstance;

    private DAOFactory() {
    }

    public static DAOFactory getInstance(){

        if (mInstance == null){

            synchronized (DAOFactory.class){

                if (mInstance == null){
                    mInstance = new DAOFactory();
                }
            }
        }

        return mInstance;
    }

    /**
     * 获取 UserDao
     * @return UserDao
     */
    public UserDao getUserDAO(Session session) {
        return new UserDaoImpl(session);
    }

}
