package com.company;

import com.company.db.DatabaseModification;
import com.company.db.DatabaseSelection;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseSelection dbSelection = new DatabaseSelection();
        DatabaseModification dbModification = new DatabaseModification();

        String menu = """

                Database `VIDEO_LIBRARY`

                1. Get FILMS of the current and last YEAR
                2. Get ACTORS from the FILM
                3. Get ACTORS who starred in at least N films
                4. Get ACTORS who directed at least a film
                5. Delete FILMS that were made later than N year
                0. CLOSE PROGRAM
                """;
        System.out.print(menu);
        String filmName;
        int countRoles;
        int filmYear;

        boolean status = true;
        int value;

        while (status) {
            System.out.print("\nInput value: ");
            value = scanner.nextInt();
            if (value == 1)
                dbSelection.getFilmsCurLastYear();
            else if (value == 2) {
                System.out.print("\nInput the Name of the Film: ");
                filmName = scanner.next();
                dbSelection.getActorsFromFilm(filmName);
            }
            else if (value == 3) {
                System.out.print("\nInput the count of roles: ");
                countRoles = scanner.nextInt();
                dbSelection.getCountOfRolesNotLessThanN(countRoles);
            }
            else if (value == 4)
                dbSelection.getActorsDirectors();
            else if (value == 5) {
                System.out.print("\nInput the year: ");
                filmYear = scanner.nextInt();
                System.out.println("\nWARNING /// Selected films will be permanently deleted!");
                System.out.print("Confirm deleting. Input 1 to delete or 0 to cancel: ");
                value = scanner.nextInt();
                if (value == 1)
                    dbModification.deleteFilmsYearMoreThanN(filmYear);
                else
                    System.out.println("\n... deleting operation has been canceled ...");
            }
            else if (value == 0) {
                status = false;
            }
            else {
                System.out.println("\nThis command is not supported!\nInput other command, please.");
            }
        }
    }
}