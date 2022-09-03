package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private final SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.openSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.openSession().createQuery("select c from Car c", Car.class);
        return query.getResultList();
    }

    @Override
    public List<User> listUsers(Car car) {
        Session session = sessionFactory.openSession();
        TypedQuery<User> query = session.createQuery("select c.user from Car c where c.series=?1 and c.model=?2", User.class);
        query.setParameter(1, car.getSeries());
        query.setParameter(2, car.getModel());
        return query.getResultList();
    }
}
