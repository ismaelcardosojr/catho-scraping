package br.com.ismaelcardosojr;

import java.io.IOException;

import br.com.ismaelcardosojr.front.Front;
import jakarta.mail.MessagingException;

public class CathoScraping {

    public static void main(String[] args) throws IOException, MessagingException {
        String[] userInfo = Front.displayIntro();

        String userDirectory = userInfo[1];
        String username = userInfo[0];

        Front.waitFileGeneration(userDirectory);

        String recipient = Front.displayMailOption();
        Front.waitEmailSending(username, recipient, userDirectory);

        Front.displayGoodbye(username);
    }
    
}