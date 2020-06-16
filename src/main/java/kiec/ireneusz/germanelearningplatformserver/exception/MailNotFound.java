package kiec.ireneusz.germanelearningplatformserver.exception;

public class MailNotFound extends RuntimeException {

    private static String MAIL_NOT_FOUND = "Mail not found";

    public MailNotFound(){
        super(MAIL_NOT_FOUND);
    }

    public MailNotFound(String message){
        super(message);
    }

}
