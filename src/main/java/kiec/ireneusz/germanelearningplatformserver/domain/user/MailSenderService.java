package kiec.ireneusz.germanelearningplatformserver.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private JavaMailSender javaMailSender;

    private String senderMail = "develop@nanocoder.pl";
    private String successMessage = "Mail sent successfully";
    private String failMessage = "Error mail sending: ";

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    String sendMessage(String recipient, String subject, String message){
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(senderMail);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
        };
        try {
            javaMailSender.send(messagePreparator);
            return successMessage;
        } catch (MailException e) {
            return failMessage + e;
        }
    }

}
