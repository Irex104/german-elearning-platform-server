package kiec.ireneusz.germanelearningplatformserver.domain.user;

import kiec.ireneusz.germanelearningplatformserver.domain.user.dto.*;
import kiec.ireneusz.germanelearningplatformserver.exception.NotFoundException;
import kiec.ireneusz.germanelearningplatformserver.utils.Mapper;
import kiec.ireneusz.germanelearningplatformserver.utils.PageableRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacade {

    private final LoginService loginService;
    private final PeopleService peopleService;
    private  PersonService personService;
    private MailSenderService mailSenderService;

    @Autowired
    public UserFacade(
            LoginService loginService,
            PeopleService peopleService,
            PersonService personService,
            MailSenderService mailSenderService
    ) {
        this.loginService = loginService;
        this.peopleService = peopleService;
        this.personService = personService;
        this.mailSenderService = mailSenderService;
    }

    //region ADMIN
    public void register(RegisterApi api){
        Person person = peopleService.create(api);
        Login login = loginService.register(api, person);
        UserDTO userDTO = Mapper.toUserDTO(login);
    }

    public void giveRole(RoleApi api) throws NotFoundException {
        Login login = loginService.getByMail(api.getMail());
        personService.giveRole(login.getPerson().getId(), api.getRole());
        loginService.giveRole(login, api.getRole());
        UserDTO userDTO = Mapper.toUserDTO(login);
    }
    //endregion

    //region AUTH

    public String remindPassword(MailApi api) throws NotFoundException {
        String newPasswordToken = loginService.remindPassword(api.getRecipient());
        return mailSenderService.sendMessage(api.getRecipient(), api.getSubject(), api.getMessage() + newPasswordToken);
    }

    public String newPassword(PasswordResetApi api, String passwordToken) throws NotFoundException {
        System.out.println(passwordToken);
        return loginService.updatePassword(passwordToken, api.getNewPassword(), api.getRepeatPassword());
    }
//    public void activateUser(String token) {
//        loginService.activate(token);
//    }
//
//    public void remindUserPassword(String mail) {

//        Login login = loginService.remindPassword(mail);
//        UserDTO userDTO = Mapper.toUserDTO(login);
//    }
//    public void resetUserPassword(String token, PasswordResetApi api) {

//        loginService.resetPassword(token, api);

//    }

    //endregion

    //region PERSON

    public List<PersonDTO> getAll(PageableRequest pageableRequest) {
        return personService.getAll(pageableRequest);
    }

    public PersonDTO getPersonDTO(Long personId) throws NotFoundException {
        Person person = personService.getOne(personId);
        return new PersonDTO(person);
    }

    public Person getPerson(Long personId) throws NotFoundException {
        return personService.getOne(personId);
    }

    public List<PersonDTO> getByMail(String userMail, PageableRequest pageableRequest){
        List<PersonDTO> personDTOs = personService.getByMail(userMail, pageableRequest).stream()
                .map(Mapper::toPersonDTO).collect(Collectors.toList());
        return personDTOs;
    }

    public PersonDTO create(RegisterApi api) {
        Person person = peopleService.create(api);
        return new PersonDTO(person);
    }

    public PersonDTO update(Long personId, UpdateApi api) throws NotFoundException {
        Person person = personService.update(personId, api);
        return new PersonDTO(person);
    }

    public void updateData(UpdateApi api, UserDTO userDTO) throws NotFoundException {
        Login login = loginService.getByMail(userDTO.getMail());
        personService.update(login.getPerson().getId(), api);
        loginService.updateData(login, api);
    }

    public void delete(String mail) throws NotFoundException {
        Login login = loginService.getByMail(mail);
        personService.delete(login.getPerson().getId());
        loginService.delete(login.getId());
    }

    //endregion

}
