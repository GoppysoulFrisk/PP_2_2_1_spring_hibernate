package hiber.dao;

import hiber.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
   Optional<Set<User>> getUserByCar(String model, int series);
}
