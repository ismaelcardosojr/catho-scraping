package br.com.ismaelcardosojr.front;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import br.com.ismaelcardosojr.csv.CSV;
import br.com.ismaelcardosojr.mail.Mail;
import br.com.ismaelcardosojr.reader.Reader;
import br.com.ismaelcardosojr.scraper.Scraper;
import br.com.ismaelcardosojr.vacancy.Vacancy;
import jakarta.mail.MessagingException;

public class Front {

    private static final Reader READER = new Reader();

    public static String[] displayIntro() throws IOException {
        String[] userInfo = new String[2];

        cleanTerminal();

        System.out.println("(Enter to continue) \n");

        System.out.print("Hello! ");
        userInfo[0] = READER.readUsername();

        System.out.print("\nHow are you, " + userInfo[0] + "? Mine is Catho-scraping! ");
        System.out.print("I can generate a .csv file containing all Developer jobs on Catho. ");
        READER.await();
        
        System.out.println("\nAre you ready to proceed? \n");

        if (!READER.readChoice()) {
            System.out.print("\nOkay! Tell me when you're ready: ");
            READER.await();
        }

        System.out.print("\nGreat! Let's go... ");
        READER.await();

        userInfo[1] = requestUserDirectory();

        return userInfo;
    }

    public static String waitFileWriting(String userDirectory) throws IOException, InterruptedException {
        cleanTerminal();

        System.out.print("Scraping vacancies... ");

        Scraper scraper = new Scraper();
        List<Vacancy> allVacancies = scraper.obtainAllVacancies();

        System.out.print("\nWriting file... ");
        Thread.sleep(2500);

        System.out.println();
        
        while (true) {
            try {
                CSV writer = new CSV(userDirectory);
                writer.writeFile(allVacancies);

                break;
            } catch (FileNotFoundException e) {
                System.out.print("\nDirectory wasn't valid. Let's try one more time (Enter to continue) ");
                READER.await();

                userDirectory = requestUserDirectory();
            }
        }

        System.out.print("\nTask successfully done! (Enter to continue) ");
        READER.await();

        return userDirectory;
    }

    public static String displayMailOption() throws IOException {
        cleanTerminal();

        System.out.println("Do you wish to share this .csv via email? \n");

        if (READER.readChoice()) {
            System.out.print("\nAwesome! Here we go... ");
            READER.await();

            cleanTerminal();

            return READER.readEmail();
        }

        return null;
    }

    public static void waitEmailSending(String username, String recipient, String userDirectory) throws IOException, MessagingException {
        if (recipient == null) {
            return;
        }

        cleanTerminal();

        System.out.print("Sending email... ");

        Mail mail = new Mail(username, recipient, userDirectory);
        mail.sendEmail();

        System.out.print("\nEmail successfully sent! (Enter to continue) ");
        READER.await();
    }

    public static void displayGoodbye(String username) {
        cleanTerminal();

        System.out.println("That's all for today. I'm glad I met you, " + username + "!");
    }

    private static String requestUserDirectory() throws IOException {
        cleanTerminal();

        System.out.println("I need a directory to save the file. May you, please, insert one? \n");

        System.out.println("Ex. (Windows) - C:///Users/{your username}/Documents");
        System.out.println("Ex. (Linux) - /home/{your username}/Documents");

        System.out.println("\nPay attention to upper and lower case letters! All '/' matters too. \n");

        return READER.readString("Here: ");
    }

    private static void cleanTerminal() {
        System.out.print("\033[H\033[2J");
    }
    
}
