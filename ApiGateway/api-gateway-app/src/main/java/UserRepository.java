import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import java.io.Serializable;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastname(String lastname);
    User findByEmailAddress(String emailAddress);
    User saveAndFlush(User entity);

    @Query("SELECT u FROM User u WHERE u.firstname LIKE %?1")
    List<User> findByFirstnameEndsWith(String firstname);
}

private class Page<T> {
}

private interface JpaRepository<T, ID>{

}

private class ceva implements UserRepository<Integer, String> {

}

private class ceva2 {
    void cevaa(){
        ceva a = new ceva();
        a.findByEmailAddress(new SpringDataWebProperties.Sort());
        a.findByLastname(new SpringDataWebProperties.Pageable());
        a.saveAndFlush(new da());
        a.findByFirstnameEndsWith()
    }
}


