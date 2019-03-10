package web.fault;


public enum FaultMessage {
    EMPTY_MAIL_LIST("There is no mails on server"),
    MAIL_NOT_FOUND("There is no mail(s) with given parameter: [%s]"),
    MAIL_ALREADY_EXIST("Mail with id: [%s] already exist");

    private String messageExpression;

    private FaultMessage(String message) {
        this.messageExpression = message;
    }

    public String getMessageExpression() {
        return this.messageExpression;
    }
}
