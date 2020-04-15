import java.security.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException 
    {
        Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to DROID_HASH\n\n\n");
		System.out.println("Enter the plain text:");
		String text = scanner.next();
		if (text.length() == 0) {
			System.out.println("\nErr no input found!");
			return;
		}
        byte[] salt = getSalt();
		System.out.println("\nOutput:\n");
        System.out.println(getSecurePassword(text, salt, "SHA-1"));
		System.out.println(getSecurePassword(text, salt, "SHA-256"));
		System.out.println(getSecurePassword(text, salt, "SHA-384"));
		System.out.println(getSecurePassword(text, salt, "SHA-512"));
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt, String message)
    {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(message);
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	
    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
 
