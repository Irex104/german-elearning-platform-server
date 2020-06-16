package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.utils.AbstractModel;
import lombok.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(schema = "public", name = "login")
@SequenceGenerator(schema = "public", name = "login_seq_id", allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "login_seq_id")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "mail", nullable = false)
    @NotBlank
    private String mail;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "people_id", nullable = false)
    private Person person;

    @NonNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "active", nullable = false)
    @NotNull
    private boolean active = true;

    @Column(name = "activate_token")
    private String activateToken = RandomString.make(10);

    @Column(name = "password_token")
    private String passwordToken;

    Login(String mail, String password, Role role, Person person) {
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.person = person;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
        return authList;
    }

    public void updatePasswordToken(String newPasswordToken){
        this.passwordToken = newPasswordToken;
    }

    public void updatePassword(String newPassword){
        this.password = newPassword;
    }

    public void giveRole(Role role) {
        this.role = role;
    }

//    void activate() {
//        this.active = true;
//        this.activateToken = null;
//    }
//
//    void remindPassword() {
//        this.passwordToken = RandomString.make(10);
//    }
//
//    void resetPassword(String newPasswordEncode) {
//        this.password = newPasswordEncode;
//        this.passwordToken = null;
//    }

//    public void update(String password) {
//        this.mail = password;
//    }
}
