package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.save(user);
    }

    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.openSession();
        TypedQuery<User> query = session.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
}
