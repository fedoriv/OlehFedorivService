package web.fault;


public enum FaultMessage {
    EMAIL_NOT_FOUND("There is no mail with given parameter: [%s]"),
    NOT_ENOUGH_BOOKS_OF_AUTHOR("There are only [%d] books of [%s], [%d] have bean required"),
    MAIL_ALREADY_EXIST("Mail with id: [%s] already exist");

    private String messageExpression;

    private FaultMessage(String message) {
        this.messageExpression = message;
    }

    public String getMessageExpression() {
        return this.messageExpression;
    }
}
