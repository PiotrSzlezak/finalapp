package pl.sda.finalapp.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(UserRegistrationDTO dto) {
        userRepository.findByEMail(dto.geteMail())
                .ifPresent(e -> {
                    throw new RuntimeException("Email " + dto.geteMail() + " already in use.");
                });
        userRepository.save(User.applyDTO(dto));
    }

}
