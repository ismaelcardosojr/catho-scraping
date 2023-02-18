package br.com.ismaelcardosojr.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

    private final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));

    public void await() throws IOException {
        IN.readLine();
    }

    public boolean readChoice() throws IOException {
        System.out.println("1 - Yes :)");
        System.out.println("2 - No :(");

        while (true) {
            System.out.print("\nType your answer: ");
            String text = IN.readLine().trim();

            char answer = ' ';

            if (!text.isBlank()) {
                answer = text.charAt(0);
            }

            if (answer == '1') return true;
            else if (answer == '2') return false;
        }
    }

    public String readEmail() throws IOException {
        System.out.print("Could you enter the recipient's email? ");

        while (true) {
            System.out.print("Here: ");
            String email = IN.readLine().trim();

            boolean isValid = validateEmail(email);

            if (isValid) {
                return email;
            }

            StringBuilder whyItsNotValid = new StringBuilder("\nEmail must contain: \n");

            whyItsNotValid.append("- Just 1 '@' \n");
            whyItsNotValid.append("- A '.com' end \n");
            whyItsNotValid.append("- Only letters, digits and '.' \n");
            whyItsNotValid.append("- Notice: 'outlook' emails can contain '_' \n");

            System.out.println(whyItsNotValid);
        }
    }

    public String readUsername() throws IOException {
        while (true) {
            System.out.print("Your name is... ");
            String username = IN.readLine().trim();

            boolean isValid = validateUsername(username);

            if (isValid) {
                return capitalize(username);
            }

            System.out.println("\nName must contain only letters and not be blank. \n");
        }
    }

    private boolean validateEmail(String email) {
        if (email.isBlank()) {
            return false;
        }

        int totalAts = 0;

        for (int i = 0; i < email.length(); i++) {
            char currentChar = email.charAt(i);

            if (email.contains("@outlook") && currentChar == '_') {
                continue;
            }

            if (!Character.isLetterOrDigit(currentChar) && currentChar != '.' && currentChar != '@') {
                return false;
            }

            if (currentChar == '@') {
                totalAts++;
            }
        }

        if (totalAts != 1) {
            return false;
        }

        if (email.startsWith("@")) {
            return false;
        }

        if (!email.endsWith(".com")) {
            return false;
        }

        int atIndex = email.indexOf("@");
        int dotComIndex = email.indexOf(".com");

        for (int i = atIndex + 1; i < dotComIndex; i++) {
            char currentChar = email.charAt(i);

            if (!Character.isLetter(currentChar)) {
                return false;
            }
        }

        return true;
    }

    private boolean validateUsername(String username) {
        if (username.isBlank()) {
            return false;
        }

        for (int i = 0; i < username.length(); i++) {
            char thisChar = username.charAt(i);

            if (!Character.isLetter(thisChar)) {
                return false;
            }
        }

        return true;
    }

    private String capitalize(String username) {
        char[] usernameChars = username.toCharArray();

        for (int i = 0; i < usernameChars.length; i++) {
            if (i == 0) {
                usernameChars[i] = Character.toUpperCase(usernameChars[i]);
            } else {
                usernameChars[i] = Character.toLowerCase(usernameChars[i]);
            }
        }

        StringBuilder formattedName = new StringBuilder();

        for (char currentChar : usernameChars) {
            formattedName.append(currentChar);
        }

        return formattedName.toString();
    }

    public String readString(String request) throws IOException {
        while (true) {
            System.out.print(request);
            String text = IN.readLine().trim();

            if (!text.isBlank()) {
                return text;
            }

            System.out.println("\nValue can't be blank. \n");
        }
    }
    
}