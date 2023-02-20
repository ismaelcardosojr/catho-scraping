# Catho Scraping 💼

*This is an enhanced version of the original: https://github.com/ismaelcardosojr/Hackathon1000Devs*

<br>

Debuting on December 5th, the competition got approximately 50 participants (12 groups) divided into beginners and knowledgeable students. 
Our class was challenged to create a software that would search for Developer jobs on internet.
After 2 intensive days, my group devised the system that won this marathon on beginners' side. 

The program extracts data from Catho, a job vacancy site, and joins the acquired info in a CSV file. Moreover, it also offers an option to share this 
file via email.

<br>

**Some resources used in development:**
* Jsoup 
* Jakarta Mail
* FileWriter

<br>

**Notice:** To make the code work, you'll need to follow these steps: 
> create a Google account > go to account settings >
under security, scroll down to "2-Step Verification" and enable it > go back to Security and click on "App passwords" >
select "Mail" app and your device. Now, you must paste the generated password into Mail.defineSession.getPasswordAuthentication (line 55)
and the account's email into Mail (line 25).

If your device isn't in the list, select "Other" and enter "Mail on my {device name}".
