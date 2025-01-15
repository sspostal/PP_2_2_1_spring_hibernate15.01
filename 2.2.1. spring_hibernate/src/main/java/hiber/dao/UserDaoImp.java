package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

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
      String hql = "SELECT user FROM User user JOIN FETCH user.car";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      return query.getResultList();
   }


   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
      String hql = "SELECT user FROM User user JOIN FETCH user.car WHERE user.car.model = :model AND user.car.series = :series";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("model", model).setParameter("series", series);
      return query.setMaxResults(1).getSingleResult();
   }

}
