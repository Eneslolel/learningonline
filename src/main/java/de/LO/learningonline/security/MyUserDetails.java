package de.LO.learningonline.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.model.Pruefer;
import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {
    private final String username, password;
    private final String role; // "STUDENT" oder "PRUEFER"

    public MyUserDetails(Student s) {
        this.username = s.getEmail();
        this.password = s.getPassword();
        this.role     = "STUDENT";
    }
    public MyUserDetails(Pruefer p) {
        this.username = p.getEmail();
        this.password = p.getPassword();
        this.role     = "PRUEFER";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }
    @Override public String getPassword()  { return password; }
    @Override public String getUsername()  { return username; }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }
}
