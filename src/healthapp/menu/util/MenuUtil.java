package healthapp.menu.util;

import java.util.List;
import java.util.Scanner;

public class MenuUtil {
    private static final Scanner sc = new Scanner(System.in);

    // Select an item by ID
    public static <T> T selectById(List<T> list, String entityName, java.util.function.Function<Integer, T> finder) {
        if (list.isEmpty()) {
            System.out.println("Nuk ka " + entityName + " të regjistruar!");
            return null;
        }
        System.out.print("Shkruaj ID-në e " + entityName + ": ");
        try {
            int id = Integer.parseInt(sc.nextLine());
            T obj = finder.apply(id);
            if (obj == null) System.out.println(entityName + " nuk u gjet!");
            return obj;
        } catch (NumberFormatException e) {
            System.out.println("ID e pavlefshme!");
            return null;
        }
    }

    // Confirm deletion
    public static boolean confirmDelete(String entityName) {
        System.out.print("Jeni të sigurt që doni të fshini " + entityName + "? (PO/JO): ");
        return sc.nextLine().equalsIgnoreCase("PO");
    }
}
