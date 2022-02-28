package nccucs;

import nccucs.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertUsers() {
        User user1 = new User(4, 18, "Jessy");
        userRepository.save(user1);
        User user2 = new User(5, 18, "Sue");
        userRepository.save(user2);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser() {
        User user = userRepository.findById(1);
        user.setAge(19);
        userRepository.save(user);
        throw new RuntimeException();
    }

    public void printUsers(){
        log.info("Users found with findAll():");
        log.info("-------------------------------");
        for (User user : userRepository.findAll()) {
            log.info(user.toString());
        }
        log.info("");
    }
}
