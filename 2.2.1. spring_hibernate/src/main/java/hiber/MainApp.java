package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Doctor", "Who", "doctors7thebest@mail.ru");
      User user2 = new User("Bruce", "Wayne", "imnotbatman@mail.ru");
      User user3 = new User("Tony", "Stark", "imrich@mail.ru");
      User user4 = new User("Han", "Solo", "iloveleia@mail.ru");

      Car car1 = new Car("telephone box", 10000);
      Car car2 = new Car("batcar", 1997);
      Car car3 = new Car("ironman", 13);
      Car car4 = new Car("millenium falcon", 1);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println("1. _____________________________________________");
      }

      System.out.println(userService.getUserByCar("telephone box", 10000));

      context.close();
   }
}
