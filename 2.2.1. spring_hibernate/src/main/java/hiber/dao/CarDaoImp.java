package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public List<User> listUsers(Car car) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        TypedQuery<Car> query = session.createQuery("select c from Car c where c.series=?1 and c.model=?2", Car.class);
        query.setParameter(1, car.getSeries());
        query.setParameter(2, car.getModel());
        List<User> users = query.getResultList().stream().map(Car::getUser).collect(Collectors.toList());
        transaction.commit();
        return users;
    }

}
