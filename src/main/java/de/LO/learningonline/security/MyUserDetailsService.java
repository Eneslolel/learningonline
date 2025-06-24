package de.LO.learningonline.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import de.LO.learningonline.repository.StudentRepository;
import de.LO.learningonline.repository.DozentRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired private StudentRepository studentRepo;
    @Autowired private DozentRepository dozentRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepo.findByEmail(username)
                .map(MyUserDetails::new)
                .or(() -> dozentRepo.findByEmail(username).map(MyUserDetails::new))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
