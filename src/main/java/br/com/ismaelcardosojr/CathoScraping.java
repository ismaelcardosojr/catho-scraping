package br.com.ismaelcardosojr;

import java.io.IOException;

import br.com.ismaelcardosojr.front.Front;
import jakarta.mail.MessagingException;

public class CathoScraping {

    public static void main(String[] args) throws IOException, MessagingException, InterruptedException {
        String[] userInfo = Front.displayIntro();
        
        String username = userInfo[0];
        String userDirectory = userInfo[1];

        userDirectory = Front.waitFileWriting(userDirectory);

        String recipient = Front.displayMailOption();
        Front.waitEmailSending(username, recipient, userDirectory);

        Front.displayGoodbye(username);
    }
    
}
