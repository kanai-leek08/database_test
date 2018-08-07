public class UserMail {

    private MailInterface mail;

    public UserMail(MailInterface mail) {
        this.mail = mail;
    }

    public boolean send(String to) {
        mail.send(to);
        return true;
    }
}
