package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<User> query = session.createQuery("select u from User u", User.class);
        List<User> resultList = query.getResultList();
        transaction.commit();
        return resultList;
    }
}
