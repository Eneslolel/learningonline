package de.LO.learningonline.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import de.LO.learningonline.model.Student;
import de.LO.learningonline.model.Dozent;
import java.util.Collection;
import java.util.Collections;

public class MyUserDetails implements UserDetails {
    private final String username, passwort;
    private final String role; // "STUDENT" oder "DOZENT"

    public MyUserDetails(Student s) {
        this.username = s.getEmail();
        this.passwort = s.getPasswort();
        this.role     = "STUDENT";
    }
    public MyUserDetails(Dozent p) {
        this.username = p.getEmail();
        this.passwort = p.getPasswort();
        this.role     = "DOZENT";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
    }
    @Override public String getPassword()  { return passwort; }
    @Override public String getUsername()  { return username; }
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }
}
