package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

   private final CarDao carDao;

   @Autowired
   public CarServiceImp(CarDao carDao) {
      this.carDao = carDao;
   }

   @Override
   public void add(Car car) {
      carDao.add(car);
   }

   @Override
   public List<Car> listCars() {
      return carDao.listCars();
   }

   @Override
   public List<User> listUsers(Car car) {
      return carDao.listUsers(car);
   }
}
