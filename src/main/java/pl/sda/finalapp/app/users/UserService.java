package pl.sda.finalapp.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDTO dto) {
        userRepository.findByEMail(dto.geteMail())
                .ifPresent(e -> {
                    throw new EmailAlreadyExistsException("Email " + dto.geteMail() + " already in use.");
                });
        final String passwordHash = passwordEncoder.encode(dto.getPassword());
        userRepository.save(User.applyDTO(dto, passwordHash));
    }

}
