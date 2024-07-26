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

        UserService service = context.getBean(UserService.class);

//      User user = new User("dfg", "ghj", "@mail.com");
//      Car car = new Car("mod1", 1);
//      user.setUserCar(car);

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

//      List<User> users = service.listUsers();
//      for (User useroflist : users) {
//         System.out.println(user.toString());
//      }

        System.out.println(service.getUserByCar("mod1", 1));
        context.close();
    }
}
