package hiber.service;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface CarService {
    void add(Car car);
    List<Car> listCars();

    /***
     * Получить пользователя непосредственно из записи о машине
     * @param car по серии и модели
     * @return пользователь
     */
    User getUser(Car car);
}
