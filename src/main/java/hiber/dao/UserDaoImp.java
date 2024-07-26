package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

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
   @Transactional() //без него Could not obtain transaction-synchronized Session for current thread
   public List<User> getUserByCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("from User WHERE userCar.model = :model and userCar.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
   }

}
