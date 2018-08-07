import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MailTest {

    public class FakeMail implements MailInterface {
        public int callCount;
        public String to;
        public void send(String to){
            callCount++;
            this.to = to;
        }
    }

    @Test
    public void send(){
        FakeMail fakeMail = new FakeMail();
        boolean actual = new UserMail(fakeMail).send("d.kanai@odd-e.com");
        assertEquals(true, actual);
        assertEquals(fakeMail.callCount, 1);
        assertEquals(fakeMail.to, "d.kanai@odd-e.com");
    }
}
