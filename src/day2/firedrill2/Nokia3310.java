package day2.firedrill2;

import java.util.Scanner;

public class Nokia3310 {
    public static void main(String[] args) {
        Scanner inputCollector = new Scanner(System.in);
        String mainPrompt = """
                         Nokia 3310 Phone
              Choose a function (1-14):
1. Phone book
2. Messages
3. Chat
4. Call register
5. Tones
6. Settings
7. Call divert
8. Games
9. Calculator
10. Reminders
11. Clock
12. Profiles
13. SIM services
14. Exit
Enter your choice:
""";

        while (true) {
                        int mainChoice = 0;

            while (mainChoice < 1 || mainChoice > 14) {
                System.out.println(mainPrompt);
                mainChoice = inputCollector.nextInt();
                if (mainChoice < 1 || mainChoice > 14) {
                    System.out.println(" Invalid main menu choice.");
                }
            }

            if (mainChoice == 14) {
                System.out.println("Power off / End session");
                break;  // Only way the infinite loop ends
            }
            
            switch (mainChoice) {
                case 1 -> {
                    String pbPrompt = """
Phone book:
1. Search
2. Service Nos.
3. Add name
4. Erase
5. Edit
6. Assign tone
7. Send b'card
8. Options
9. Speed dials
10. Voice tags
Enter your choice:
""";
                    int pbChoice = 0;
                    while (pbChoice < 1 || pbChoice > 10) {
                        System.out.println(pbPrompt);
                        pbChoice = inputCollector.nextInt();
                        if (pbChoice < 1 || pbChoice > 10) {
                            System.out.println("Invalid choice!");
                        }
                    }
                    switch (pbChoice) {
                        case 1 -> {
                            System.out.println(" Searching the phone book...");
                        }
                        case 2 -> {
                            System.out.println(" Service Nos. displayed.");
                        }
                        case 3 -> {
                            System.out.println(" Add name to contact .");
                        }
                        case 4 -> {
                            System.out.println(" Erase contact");
                        }
                        case 5 -> {
                            System.out.println(" Edit contact ");
                        }
                        case 6 -> {
                            System.out.println(">>> Assign tone for contact");
                        }
                        case 7 -> {
                            System.out.println(" Send b'card");
                        }
                        case 8 -> {
                            String optPrompt = """
Options:
1. Type of view
2. Memory status
Enter your choice:
""";
                            int optChoice = 0;
                            while (optChoice < 1 || optChoice > 2) {
                                System.out.println(optPrompt);
                                optChoice = inputCollector.nextInt();
                                if (optChoice < 1 || optChoice > 2) {
                                    System.out.println("Invalid option!");
                                }
                            }
                            switch (optChoice) {
                                case 1 -> {
                                    System.out.println(" Name or Number view selected.");
                                }
                                case 2 -> {
                                    System.out.println(" Memory status selected");
                                }
                                default -> System.out.println("Invalid option!");
                            }
                        }
                        case 9 -> {
                            System.out.println(" Speed dials.");
                        }
                        case 10 -> {
                            System.out.println(" Voice tags.");
                        }
                        default -> System.out.println("Invalid choice!");
                    }
                }
                case 2 -> {
                    String msgPrompt = """
Messages:
1. Write messages
2. Inbox
3. Outbox
4. Picture messages
5. Templates
6. Smileys
7. Message settings
8. Info service
9. Voice mailbox number
10. Service command editor
Enter your choice:
""";
                    int msgChoice = 0;
                    while (msgChoice < 1 || msgChoice > 10) {
                        System.out.println(msgPrompt);
                        msgChoice = inputCollector.nextInt();
                        if (msgChoice < 1 || msgChoice > 10) {
                            System.out.println("Invalid choice in Messages!");
                        }
                    }
                    switch (msgChoice) {
                        case 1 -> {
                            System.out.println(" Write messages ");
                        }
                        case 2 -> {
                            System.out.println(" Opening Inbox ");
                        }
                        case 3 -> {
                            System.out.println(" Opening Outbox ");
                        }
                        case 4 -> {
                            System.out.println(" Picture messages opened ");
                        }
                        case 5 -> {
                            System.out.println(" Templates ready ");
                        }
                        case 6 -> {
                            System.out.println(" Smileys list displayed ");
                        }
                        case 7 -> {
                            String settingsPrompt = """
Message settings:
1. Set 1
2. Common
Enter your choice:
""";
                            int settingsChoice = 0;
                            while (settingsChoice < 1 || settingsChoice > 2) {
                                System.out.println(settingsPrompt);
                                settingsChoice = inputCollector.nextInt();
                                if (settingsChoice < 1 || settingsChoice > 2) {
                                    System.out.println("Invalid in Message settings!");
                                }
                            }
                            switch (settingsChoice) {
                                case 1 -> {
                                    String set1Prompt = """
Set 1:
1. Message centre number
2. Messages sent as
3. Message validity
Enter your choice:
""";
                                    int set1Choice = 0;
                                    while (set1Choice < 1 || set1Choice > 3) {
                                        System.out.println(set1Prompt);
                                        set1Choice = inputCollector.nextInt();
                                        if (set1Choice < 1 || set1Choice > 3) {
                                            System.out.println("Invalid in Set 1!");
                                        }
                                    }
                                    switch (set1Choice) {
                                        case 1 -> {
                                            System.out.println(" Message centre number set.");
                                        }
                                        case 2 -> {
                                            System.out.println(" Messages sent as Text ");
                                        }
                                        case 3 -> {
                                            System.out.println(" Message validity 1 day ");
                                        }
                                        default -> System.out.println("Invalid in Set 1!");
                                    }
                                }
                                case 2 -> {
                                    String commonPrompt = """
Common:
1. Delivery reports
2. Reply via same centre
3. Character support
Enter your choice:
""";
                                    int commonChoice = 0;
                                    while (commonChoice < 1 || commonChoice > 3) {
                                        System.out.println(commonPrompt);
                                        commonChoice = inputCollector.nextInt();
                                        if (commonChoice < 1 || commonChoice > 3) {
                                            System.out.println("Invalid in Common!");
                                        }
                                    }
                                    switch (commonChoice) {
                                        case 1 -> {
                                            System.out.println(" Delivery reports: ON.");
                                        }
                                        case 2 -> {
                                            System.out.println(" Reply via same centre");
                                        }
                                        case 3 -> {
                                            System.out.println(" Character support");
                                        }
                                        default -> System.out.println("Invalid in Common!");
                                    }
                                }
                                default -> System.out.println("Invalid in Message settings!");
                            }
                        }
                        case 8 -> {
                            System.out.println(" Info service activated");
                        }
                        case 9 -> {
                            System.out.println(" Voice mailbox number: +234 123 456 7890 (you can change it).");
                        }
                        case 10 -> {
                            System.out.println(" Service command editor opened ");
                        }
                        default -> System.out.println("Invalid choice in Messages!");
                    }
                }
                case 3 -> {
                    System.out.println(" Opening Chat...");
                }
                case 4 -> {
                    String crPrompt = """
Call register:
1. Missed calls
2. Received calls
3. Dialled numbers
4. Erase recent call lists
5. Show call duration
6. Show call costs
7. Call cost settings
8. Prepaid credit
Enter your choice:
""";
                    int crChoice = 0;
                    while (crChoice < 1 || crChoice > 8) {
                        System.out.println(crPrompt);
                        crChoice = inputCollector.nextInt();
                        if (crChoice < 1 || crChoice > 8) {
                            System.out.println("Invalid choice in Call register!");
                        }
                    }
                    switch (crChoice) {
                        case 1 -> {
                            System.out.println(" Missed calls list displayed.");
                        }
                        case 2 -> {
                            System.out.println(" Received calls list displayed.");
                        }
                        case 3 -> {
                            System.out.println(" Dialled numbers list displayed.");
                        }
                        case 4 -> {
                            System.out.println(" Recent call lists erased.");
                        }
                        case 5 -> {
                            String durationPrompt = """
Show call duration:
1. Last call duration
2. All calls' duration
3. Received calls' duration
4. Dialled calls' duration
5. Clear timers
Enter your choice:
""";
                            int durChoice = 0;
                            while (durChoice < 1 || durChoice > 5) {
                                System.out.println(durationPrompt);
                                durChoice = inputCollector.nextInt();
                                if (durChoice < 1 || durChoice > 5) {
                                    System.out.println("Invalid in call duration!");
                                }
                            }
                            switch (durChoice) {
                                case 1 -> {
                                    System.out.println(" Last call duration: 1 hour");
                                }
                                case 2 -> {
                                    System.out.println(" All calls' duration shown.");
                                }
                                case 3 -> {
                                    System.out.println(" Received calls' duration shown.");
                                }
                                case 4 -> {
                                    System.out.println(" Dialled calls' duration shown.");
                                }
                                case 5 -> {
                                    System.out.println(" Timers cleared.");
                                }
                                default -> System.out.println("Invalid in call duration!");
                            }
                        }
                        case 6 -> {
                            String costPrompt = """
Show call costs:
1. Last call cost
2. All calls' cost
3. Clear counters
Enter your choice:
""";
                            int costChoice = 0;
                            while (costChoice < 1 || costChoice > 3) {
                                System.out.println(costPrompt);
                                costChoice = inputCollector.nextInt();
                                if (costChoice < 1 || costChoice > 3) {
                                    System.out.println("Invalid in call costs!");
                                }
                            }
                            switch (costChoice) {
                                case 1 -> {
                                    System.out.println(" Last call cost: 50 naira");
                                }
                                case 2 -> {
                                    System.out.println(" All calls' cost shown.");
                                }
                                case 3 -> {
                                    System.out.println(" Counters cleared.");
                                }
                                default -> System.out.println("Invalid in call costs!");
                            }
                        }
                        case 7 -> {
                            String ccsPrompt = """
Call cost settings:
1. Call cost limit
2. Show costs in
Enter your choice:
""";
                            int ccsChoice = 0;
                            while (ccsChoice < 1 || ccsChoice > 2) {
                                System.out.println(ccsPrompt);
                                ccsChoice = inputCollector.nextInt();
                                if (ccsChoice < 1 || ccsChoice > 2) {
                                    System.out.println("Invalid in call cost settings!");
                                }
                            }
                            switch (ccsChoice) {
                                case 1 -> {
                                    System.out.println(" Call cost limit set to N500.");
                                }
                                case 2 -> {
                                    System.out.println(" Show costs in: Naira.");
                                }
                                default -> System.out.println("Invalid in call cost settings!");
                            }
                        }
                        case 8 -> {
                            System.out.println("Prepaid credit: 500 remaining");
                        }
                        default -> System.out.println("Invalid choice in Call register!");
                    }
                }
                case 5 -> {
                    String tonesPrompt = """
Tones:
1. Ringing tone
2. Ringing volume
3. Incoming call alert
4. Composer
5. Message alert tone
6. Keypad tones
7. Warning and game tones
8. Vibrating alert
9. Screen saver
Enter your choice:
""";
                    int tonesChoice = 0;
                    while (tonesChoice < 1 || tonesChoice > 9) {
                        System.out.println(tonesPrompt);
                        tonesChoice = inputCollector.nextInt();
                        if (tonesChoice < 1 || tonesChoice > 9) {
                            System.out.println("Invalid choice in Tones!");
                        }
                    }
                    switch (tonesChoice) {
                        case 1 -> {
                            System.out.println(" Ringing tone selected.");
                        }
                        case 2 -> {
                            System.out.println(" Ringing volume adjusted ");
                        }
                        case 3 -> {
                            System.out.println(" Incoming call alert");
                        }
                        case 4 -> {
                            System.out.println(" Composer opened ");
                        }
                        case 5 -> {
                            System.out.println(" Message alert tone selected.");
                        }
                        case 6 -> {
                            System.out.println(" Keypad tones On.");
                        }
                        case 7 -> {
                            System.out.println(" Warning and game tones On.");
                        }
                        case 8 -> {
                            System.out.println(" Vibrating alert On.");
                        }
                        case 9 -> {
                            System.out.println(" Screen saver activated");
                        }
                        default -> System.out.println("Invalid choice in Tones!");
                    }
                }
                case 6 -> {
                    String settingsMainPrompt = """
Settings:
1. Call settings
2. Phone settings
3. Security settings
4. Restore factory settings
Enter your choice:
""";
                    int settingsMainChoice = 0;
                    while (settingsMainChoice < 1 || settingsMainChoice > 4) {
                        System.out.println(settingsMainPrompt);
                        settingsMainChoice = inputCollector.nextInt();
                        if (settingsMainChoice < 1 || settingsMainChoice > 4) {
                            System.out.println("Invalid choice in Settings!");
                        }
                    }
                    switch (settingsMainChoice) {
                        case 1 -> {
                            String callSettingsPrompt = """
Call settings:
1. Automatic redial
2. Speed dialling
3. Call waiting options
4. Own number sending
5. Phone line in use
6. Automatic answer
Enter your choice:
""";
                            int callSetChoice = 0;
                            while (callSetChoice < 1 || callSetChoice > 6) {
                                System.out.println(callSettingsPrompt);
                                callSetChoice = inputCollector.nextInt();
                                if (callSetChoice < 1 || callSetChoice > 6) {
                                    System.out.println("Invalid in Call settings!");
                                }
                            }
                            switch (callSetChoice) {
                                case 1 -> {
                                    System.out.println(" Automatic redial is ON.");
                                }
                                case 2 -> {
                                    System.out.println("Speed dialling is ON.");
                                }
                                case 3 -> {
                                    System.out.println(" Call waiting options configured.");
                                }
                                case 4 -> {
                                    System.out.println(" Own number sending is ON.");
                                }
                                case 5 -> {
                                    System.out.println("Phone line in use.");
                                }
                                case 6 -> {
                                    System.out.println(" Automatic answer id OFF");
                                }
                                default -> System.out.println("Invalid in Call settings!");
                            }
                        }
                        case 2 -> {
                            String phoneSettingsPrompt = """
Phone settings:
1. Language
2. Cell info display
3. Welcome note
4. Network selection
5. Lights
6. Confirm SIM service actions
Enter your choice:
""";
                            int phoneSetChoice = 0;
                            while (phoneSetChoice < 1 || phoneSetChoice > 6) {
                                System.out.println(phoneSettingsPrompt);
                                phoneSetChoice = inputCollector.nextInt();
                                if (phoneSetChoice < 1 || phoneSetChoice > 6) {
                                    System.out.println("Invalid in Phone settings!");
                                }
                            }
                            switch (phoneSetChoice) {
                                case 1 -> {
                                    System.out.println(" Language: English.");
                                }
                                case 2 -> {
                                    System.out.println(" Cell info display is ON.");
                                }
                                case 3 -> {
                                    System.out.println(" Welcome note set to 'Hello!'");
                                }
                                case 4 -> {
                                    System.out.println(" Network selection is Automatic.");
                                }
                                case 5 -> {
                                    System.out.println(" Lights are ON.");
                                }
                                case 6 -> {
                                    System.out.println(" Confirm SIM service actions are ON.");
                                }
                                default -> System.out.println("Invalid in Phone settings!");
                            }
                        }
                        case 3 -> {
                            String securityPrompt = """
Security settings:
1. PIN code request
2. Call barring service
3. Fixed dialling
4. Closed user group
5. Phone security
6. Change access codes
Enter your choice:
""";
                            int secChoice = 0;
                            while (secChoice < 1 || secChoice > 6) {
                                System.out.println(securityPrompt);
                                secChoice = inputCollector.nextInt();
                                if (secChoice < 1 || secChoice > 6) {
                                    System.out.println("Invalid in Security settings!");
                                }
                            }
                            switch (secChoice) {
                                case 1 -> {
                                    System.out.println(" PIN code request is ON.");
                                }
                                case 2 -> {
                                    System.out.println(">>> Call barring service activated.");
                                }
                                case 3 -> {
                                    System.out.println(" Fixed dialling is ON.");
                                }
                                case 4 -> {
                                    System.out.println(" Closed user group configured.");
                                }
                                case 5 -> {
                                    System.out.println(" Phone security is ON.");
                                }
                                case 6 -> {
                                    System.out.println(" Change access codes ");
                                }
                                default -> System.out.println("Invalid in Security settings!");
                            }
                        }
                        case 4 -> {
                            System.out.println(" Restoring factory settings");
                        }
                        default -> System.out.println("Invalid choice in Settings!");
                    }
                }
                case 7 -> {
                    System.out.println(" Call divert activated.");
                }
                case 8 -> {
                    System.out.println(" Games menu opened ");
                }
                case 9 -> {
                    System.out.println(" Calculator opened.");
                }
                case 10 -> {
                    System.out.println("Reminders opened.");
                }
                case 11 -> {
                    String clockPrompt = """
Clock:
1. Alarm clock
2. Clock settings
3. Date setting
4. Stopwatch
5. Countdown timer
6. Auto update of date and time
Enter your choice:
""";
                    int clockChoice = 0;
                    while (clockChoice < 1 || clockChoice > 6) {
                        System.out.println(clockPrompt);
                        clockChoice = inputCollector.nextInt();
                        if (clockChoice < 1 || clockChoice > 6) {
                            System.out.println("Invalid choice in Clock!");
                        }
                    }
                    switch (clockChoice) {
                        case 1 -> {
                            System.out.println(" Alarm clock set for 07:00.");
                        }
                        case 2 -> {
                            System.out.println(" Clock settings is 12-hour format.");
                        }
                        case 3 -> {
                            System.out.println(" Date setting: 17/03/2026.");
                        }
                        case 4 -> {
                            System.out.println(" Stopwatch started.");
                        }
                        case 5 -> {
                            System.out.println(" Countdown timer set for 10 minutes.");
                        }
                        case 6 -> {
                            System.out.println(" Auto update of date and time are ON.");
                        }
                        default -> System.out.println("Invalid choice in Clock!");
                    }
                }
                case 12 -> {
                    System.out.println(" Profiles opened - select General or Silent.");
                }
                case 13 -> {
                    System.out.println(" SIM services accessed");
                }
                default -> System.out.println(" Invalid main menu choice.");
            }

            System.out.println("Returning to main menu...");
        }
    }
}