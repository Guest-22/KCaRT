package kcart.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_SIZE = 16;
    private static final int KEY_SIZE = 32;
    private static final int ITERATIONS = 100000;

    // Generate a password hash.
    public static String hashPassword(String password) throws Exception {
        // Generate salt.
        byte[] salt = new byte[SALT_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        // PBKDF2 with SHA-256.
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_SIZE * 8
        );

        byte[] key;

        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            key = factory.generateSecret(spec).getEncoded();
        } finally {
            spec.clearPassword();
        }

        // Store as: iterations.salt.key.
        return ITERATIONS + "."
                + Base64.getEncoder().encodeToString(salt) + "."
                + Base64.getEncoder().encodeToString(key);
    }

    // Verify a password against the stored hash.
    public static boolean verifyPassword(String password, String storedHash)
            throws Exception {

        if (storedHash == null || storedHash.isEmpty()) {
            return false;
        }

        String[] parts = storedHash.split("\\.");

        if (parts.length != 3) {
            return false;
        }

        int iterations;

        try {
            iterations = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        byte[] salt;
        byte[] key;

        try {
            salt = Base64.getDecoder().decode(parts[1]);
            key = Base64.getDecoder().decode(parts[2]);
        } catch (IllegalArgumentException e) {
            return false;
        }

        // Hash the entered password using the stored salt.
        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                iterations,
                key.length * 8
        );

        byte[] keyToCheck;

        try {
            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            keyToCheck = factory.generateSecret(spec).getEncoded();
        } finally {
            spec.clearPassword();
        }

        // Constant-time comparison.
        return MessageDigest.isEqual(key, keyToCheck);
    }
}