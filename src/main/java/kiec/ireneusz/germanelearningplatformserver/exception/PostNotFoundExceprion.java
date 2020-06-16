package kiec.ireneusz.germanelearningplatformserver.exception;

public class PostNotFoundExceprion extends AppException {
    private static String MAIL_NOT_FOUND = "Post not found";
    private static String MAIL_NOT_FOUND_ID = "Post not found: ";

    public PostNotFoundExceprion(){
        super(MAIL_NOT_FOUND);
    }
    public PostNotFoundExceprion(Long id){
        super(MAIL_NOT_FOUND_ID + id);
    }

    public PostNotFoundExceprion(String message){
        super(message);
    }
}
