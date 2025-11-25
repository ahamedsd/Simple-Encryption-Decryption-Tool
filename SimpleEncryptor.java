import java.util.*;
import java.io.*;
import java.util.Base64;

public class SimpleEncryptor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File folder = new File("encrypted_files");
        if (!folder.exists()) folder.mkdir();

        while (true) {
            System.out.println("\n--- Simple Encryption Tool ---");
            System.out.println("1. Encrypt & Save");
            System.out.println("2. Decrypt File");
            System.out.println("3. List Files");
            System.out.println("4. Delete File");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter filename: ");
                String fname = sc.nextLine();

                System.out.print("Enter text: ");
                String text = sc.nextLine();

                System.out.print("Create passcode for the file: ");
                String pass = sc.nextLine();
                int key = makeKey(pass);

                String encrypted = encrypt(text, key);

                try {
                    FileWriter fw = new FileWriter("encrypted_files/" + fname + ".enc");
                    fw.write(encrypted);
                    fw.close();
                    System.out.println("Saved.");
                } catch (Exception e) {
                    System.out.println("Error saving.");
                }
            }

            else if (choice == 2) {
                System.out.print("Enter filename: ");
                String fname = sc.nextLine();

                System.out.print("Enter passcode for this file: ");
                String pass = sc.nextLine();
                int key = makeKey(pass);

                try {
                    String data = new String(java.nio.file.Files.readAllBytes(
                        new File("encrypted_files/" + fname + ".enc").toPath()
                    ));
                    String dec = decrypt(data, key);
                    System.out.println("Decrypted: " + dec);
                } catch (Exception e) {
                    System.out.println("Error reading file.");
                }
            }

            else if (choice == 3) {
                File[] files = folder.listFiles();
                if (files.length == 0) {
                    System.out.println("No files.");
                } else {
                    for (File f : files) System.out.println(f.getName());
                }
            }

            else if (choice == 4) {
                File[] files = folder.listFiles();
                if (files.length == 0) {
                    System.out.println("No files to delete.");
                } else {
                    System.out.println("Files:");
                    for (File f : files) System.out.println("- " + f.getName());
                    System.out.print("Enter exact filename to delete: ");
                    String del = sc.nextLine();

                    File target = new File("encrypted_files/" + del);
                    if (target.exists()) {
                        if (target.delete()) System.out.println("Deleted.");
                        else System.out.println("Couldn't delete.");
                    } else {
                        System.out.println("File not found.");
                    }
                }
            }

            else if (choice == 5) {
                System.out.println("Bye!");
                break;
            }

            else {
                System.out.println("Wrong choice.");
            }
        }
    }

    public static int makeKey(String pass) {
        int sum = 0;
        for (char c : pass.toCharArray()) sum += (int)c;
        return sum == 0 ? 7 : sum;
    }

    public static String encrypt(String text, int key) {
        char[] arr = text.toCharArray();
        for (int i = 0; i < arr.length; i++) arr[i] = (char)(arr[i] ^ key);
        String reversed = new StringBuilder(new String(arr)).reverse().toString();
        return Base64.getEncoder().encodeToString(reversed.getBytes());
    }

    public static String decrypt(String data, int key) {
        try {
            String decoded = new String(Base64.getDecoder().decode(data));
            String reversed = new StringBuilder(decoded).reverse().toString();
            char[] arr = reversed.toCharArray();
            for (int i = 0; i < arr.length; i++) arr[i] = (char)(arr[i] ^ key);
            return new String(arr);
        } catch (Exception e) {
            return "Wrong passcode or corrupted file.";
        }
    }
}
