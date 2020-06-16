package kiec.ireneusz.germanelearningplatformserver.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.mail;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.active;
    }

}
