package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Component
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @Transactional(readOnly = true) //без него Could not obtain transaction-sinchronized Session for current thread
   public Optional<Set<User>> getUserByCar(String model, int series) {
      Session session;
      try {
         session = sessionFactory.getCurrentSession();
      } catch (HibernateException e) {
         session = sessionFactory.openSession();
      }
      Set<User> usersSet = new HashSet<>(sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u JOIN u.userCar c WHERE c.model = :model AND c.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList());
      return usersSet.isEmpty() ? Optional.empty() : Optional.of(usersSet);
      //N+1
      //SQL: A LEFT JOIN B -> Результирующая объединенная таблица
   }

}
