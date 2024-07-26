package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService service = context.getBean(UserService.class);

//        User user1 = new User("Петя", "Придурков", "петя@mail.com");
//        Car car1 = new Car("Девятка", 8);
//        user1.setUserCar(car1);
//        User user2 = new User("Саня", "Лодочник", "саня@mail.com");
//        Car car2 = new Car("десятка", 9);
//        user2.setUserCar(car2);
//        User user3 = new User("Василиса", "Премудрая", "вумница@mail.com");
//        Car car3 = new Car("одинадцадка", 145);
//        user3.setUserCar(car3);
//        User user4 = new User("Мамкин", "Программист", "ХеллоВорлд@mail.com");
//
//        service.add(user1);
//        service.add(user2);
//        service.add(user3);
//        service.add(user4);
//      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
//      Car car4 = new Car("model4", 4);
//      user1.setUserCar(car4);
//      userService.add(user1);
//
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//
//      User user4 = new User("User3", "Lastname3", "user3@mail.ru");
//      User user3 = new User("User4", "Lastname4", "user4@mail.ru");
//
//      Car car3 = new Car("model3", 3);
//      user4.setUserCar(car3);
//      Car car2 = new Car("model2", 2);
//      user3.setUserCar(car2);
//      userService.add(user3);
//      userService.add(user4);

      List<User> userslist = service.listUsers();
      for (User useroflist : userslist) {
         System.out.println(useroflist.toString());
      }

        System.out.println(service.getUserByCar("одинадцатка", 145));
        context.close();
    }
}
