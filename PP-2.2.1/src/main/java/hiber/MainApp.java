package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      CarService carService = context.getBean(CarService.class);

      /*userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("1234", 1234)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("5678", 5678)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("e456kt", 456)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("a563co", 563)));
      carService.add(new Car("1234", 1234));
      carService.add(new Car("5678", 5678));
      carService.add(new Car("e456kt", 456));
      carService.add(new Car("a563co", 563));*/

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar().getName() + ", " + user.getCar().getSeries());
      }

      List<Car> cars = carService.listCars();
      for(Car car : cars){
         System.out.println(carService.getUser(car.getName(), car.getSeries()).getFirstName());
         //System.out.println("Id = "+car.getId());
         //System.out.println("Name = "+car.getName());
         //System.out.println("Series = "+car.getSeries());
      }

      context.close();
   }
}
