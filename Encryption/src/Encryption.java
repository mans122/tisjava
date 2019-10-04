import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	
	    public static String getHash(String s) {
	        MessageDigest m = null;
	        String hash = null;
	    
	        try {
	         m = MessageDigest.getInstance("SHA-1");
	         m.update(s.getBytes(),0,s.length());
	         hash = new BigInteger(1, m.digest()).toString(16);
	        } catch (NoSuchAlgorithmException e) {
	         e.printStackTrace();
	        }
	    
	        return hash;
	   } 

	public static void main(String[] args) {
		System.out.println(getHash("1232224"));

	}

}
