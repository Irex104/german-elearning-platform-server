package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.utils.Mapper;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.RegisterApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UpdateApi;
import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.UserDTO;
import kiec.ireneusz.germanelearningplatformserver.exception.MailNotFound;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import lombok.SneakyThrows;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final LoginRepository repository;

    @Autowired
    public LoginService(
            PasswordEncoder passwordEncoder,
            LoginRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    @SneakyThrows
    @Override
    public UserDTO loadUserByUsername(String mail) {
        Optional<UserDTO> userDTO = repository.findByMailAndDeletedAtIsNull(mail)
                .map(Mapper::toUserDTO);

        return userDTO.orElseThrow(() -> new NotFoundException());
    }
    Login getByMail(String mail) throws NotFoundException {
        Optional<Login> login = repository.findByMailAndDeletedAtIsNull(mail);

        return login.orElseThrow(() -> new NotFoundException());
    }

    Login getByToken(String passwordToken) throws NotFoundException {
        return repository.findByPasswordTokenAndDeletedAtIsNull(passwordToken)
                .orElseThrow(() -> new NotFoundException());
    }

    Login register(RegisterApi api, Person person){
        checkMail(api.getMail());
        Role role = api.getRole();
        Login login = new Login(api.getMail(), passwordEncoder.encode(api.getPassword()), role, person);
        return repository.save(login);
    }

    private void checkMail(String mail) {
        boolean unavailable = repository.findByMailAndDeletedAtIsNull(mail).isPresent();
        if (unavailable)
            throw new MailNotFound();
    }

    String remindPassword(String mail) throws NotFoundException {
        String newPasswordToken = this.passwordGenerator();
        System.out.println(newPasswordToken);
        this.updatePasswordToken(mail, newPasswordToken);

//        Optional<Login> login = repository.findByMailAndDeletedAtIsNull(mail);
//        if (login.isPresent()) {
//            login.get().remindPassword();
//            return repository.save(login.get());
//        } else
//            throw new RuntimeException("exception");
        return newPasswordToken;
    }

    String updatePassword(String passwordToken, String newPassword, String repeatPassword) throws NotFoundException {
        Login login = getByToken(passwordToken);
        if(newPassword.equals(repeatPassword)){
            login.updatePassword(passwordEncoder.encode(newPassword));
            repository.save(login);
            return "Password changed successfully";
        }else{
            return "Wrong repeat password";
        }
    }

    private void updatePasswordToken(String mail, String newPasswordToken) throws NotFoundException {
        Login login = getByMail(mail);
        login.updatePasswordToken(newPasswordToken);
        repository.save(login);
    }

    private String passwordGenerator(){
        return RandomString.make(12);
    }

    void giveRole(Login login, Role role) {
        login.giveRole(role);
        repository.save(login);
    }

    void updateData(Login login, UpdateApi api){
        login.updatePassword(passwordEncoder.encode(api.getPassword()));
        login.setMail(api.getMail());
        repository.save(login);
    }

    void delete(Long loginId) throws NotFoundException {
        Login login = repository.findByIdAndDeletedAtIsNull(loginId)
                .orElseThrow(() -> new NotFoundException());
        login.delete();
        repository.save(login);
    }

//    void activate(String token) {
//        Optional<Login> login = repository.findByActivateTokenAndDeletedAtIsNull(token);
//        if (login.isPresent()) {
//            login.get().activate();
//            repository.save(login.get());

//        } else

//            throw new RuntimeException("exception");
//    }
//    void resetPassword(String token, PasswordResetApi api) {
//        checkNewPassword(api);
//        Optional<Login> login = repository.findByPasswordTokenAndDeletedAtIsNull(token);
//        if (login.isPresent()) {
//            login.get().resetPassword(passwordEncoder.encode(api.getNewPassword()));
//            repository.save(login.get());
//        } else

//            throw new RuntimeException("exception");
//    }
//    private void checkNewPassword(PasswordResetApi api) {
//        if (!api.getNewPassword().equals(api.getRepeatedPassword()))

//            throw new RuntimeException("exception");

//    }
}
