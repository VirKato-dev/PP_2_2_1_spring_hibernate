package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car(100, "BMW")));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car(100, "BMW")));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car(200, "VW")));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car(10, "Lexus")));

        System.out.println("Все пользователи:\n");
        List<User> users = userService.listUsers();
        users.forEach(System.out::println);

        Car car = new Car(100, "BMW");
        System.out.println("Пользователи с машиной:\n" + car + "\n");
        carService.listUsers(car).forEach(System.out::println);

        context.close();
    }
}
