package daos;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class UserDao {
    private final SessionFactory sf;

    private UserDao(SessionFactory sf) {
        this.sf = sf;
    }

    private static final class Holder {
        private static final UserDao INSTANCE = new UserDao(SessionFactoryUtils.getInstance());
    }

    public static UserDao instOf() {
        return UserDao.Holder.INSTANCE;
    }

    public User save(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public Optional<User> findUserByEmail(String email) {
        Session session = sf.openSession();
        session.beginTransaction();
        String hql = "Select user from User user where user.email=:userEmail";
        Query query = session.createQuery(hql);
        query.setParameter("userEmail", email);
        List<User> result = query.list();
        session.getTransaction().commit();
        session.close();
        if (result.size() != 0) {
            return Optional.of(result.get(0));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        UserDao userDao = UserDao.instOf();
//        userDao.save(new User("DIma", "@EMAIL", "pASS"));
        System.out.println(userDao.findUserByEmail("@EMAL"));
    }
}
