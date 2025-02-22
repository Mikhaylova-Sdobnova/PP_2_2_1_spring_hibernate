package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

       userService.addCar(new Car("Mitsubishi", 49565));
       userService.addCar(new Car("Haval", 75452));
       userService.addCar(new Car("Cherry", 75485));
       userService.addCar(new Car("Porsche", 77777));

       List<Car> cars = userService.listCars();

      userService.add(new User("Petya", "Lastname1", "user1@mail.ru", cars.get(0)));
      userService.add(new User("Masha", "Lastname2", "user2@mail.ru", cars.get(1)));
      userService.add(new User("Vova", "Lastname3", "user3@mail.ru", cars.get(2)));
      userService.add(new User("Vanya", "Lastname4", "user4@mail.ru", cars.get(3)));


      for (User user : userService.listUsers()) {
          System.out.println("Id = " +user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
          System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserByCar("Porsche", 77777));

       System.out.println(userService.getUserByCar("Audi", 85423));

      context.close();
   }
}
