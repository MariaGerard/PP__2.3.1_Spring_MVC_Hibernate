package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User showById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUser(int id, User user) {
        User userToBeUpdated = showById(id);

        userToBeUpdated.setName(user.getName());
        userToBeUpdated.setLastName(user.getLastName());
        userToBeUpdated.setAge(user.getAge());
        entityManager.merge(userToBeUpdated);
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        return entityManager.createQuery("select u from User u",
                User.class).getResultList();
    }
}
