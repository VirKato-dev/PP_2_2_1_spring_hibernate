package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    List<User> listUsers();

    /***
     * Искать пользователя по Car.id из таблицы cars
     * @param car серия и модель
     * @return user
     */
    User get(Car car);
}
