package vn.dev.na.btl.data.repository;

import android.content.Context;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import vn.dev.na.btl.data.QLHPDatabase;
import vn.dev.na.btl.data.entity.Account;

public class AccountRepository {

    private QLHPDatabase database;

    public AccountRepository(Context context) {
        database = QLHPDatabase.getDatabase(context);
    }

    public Account login(String username, String password) {
        return database.accountDAO().login(username, getSHA512(password));
    }

    public void createAdmin() {
        if( database.accountDAO().checkExitAccount() == 0) {
            Account accountDTO = new Account();
            accountDTO.setCode(String.valueOf(new Date().getTime()));
            accountDTO.setFullName("admin");
            accountDTO.setUserName( "admin");
            accountDTO.setPassword(getSHA512("admin"));
            database.accountDAO().insertAll(accountDTO);
        }
    }

    private String getSHA512(String input) {
        try {
            // getInstance() method is called with algorithm SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
