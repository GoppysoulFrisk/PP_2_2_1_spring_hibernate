package hiber.dao;

import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Component
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userCar", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<Set<User>> getUserByCar(String model, int series) {
        Set<User> usersSet = new HashSet<>();

        usersSet.addAll(sessionFactory.getCurrentSession().createQuery(
                        "SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userCar c WHERE c.model = :model AND c.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList());

        return usersSet.isEmpty() ? Optional.empty() : Optional.of(usersSet);

        //N+1
        //SQL: A LEFT JOIN B -> Результирующая объединенная таблица
    }

}
