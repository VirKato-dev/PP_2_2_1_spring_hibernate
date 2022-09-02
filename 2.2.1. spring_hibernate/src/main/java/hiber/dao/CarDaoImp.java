package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Car> listCars() {
        Query<Car> query = sessionFactory.getCurrentSession().createQuery("select c from Car c", Car.class);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers(Car car) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<User> query = session.createQuery("select c.user from Car c where c.series=?1 and c.model=?2", User.class);
        query.setParameter(1, car.getSeries());
        query.setParameter(2, car.getModel());
        List<User> users = query.getResultList();
        transaction.commit();
        return users;
    }

}
