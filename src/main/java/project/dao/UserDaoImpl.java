package project.dao;

import org.springframework.stereotype.Repository;
import project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.remove(entityManager.find(User.class, id));

    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


    @Override
    public List<User> getAllUser() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public void userEditor(User user, Long id) {
        User existingUser = entityManager.find(User.class, id);
        if (existingUser != null) {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            entityManager.merge(existingUser);
        }
    }

}
