package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomHelper {
    public static String generateRandomNumber(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        return String.valueOf(randomNumber);
    }

    public static String generateRandomString() {
        return generateRandomString(8);
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomPassword(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public static String generateRandomEmail() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        sb.append("@example.com");
        return sb.toString();
    }

    public static String generateRandomPhoneNumber(int length) {
        StringBuilder phoneNumber = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            phoneNumber.append(random.nextInt(10));
        }
        return phoneNumber.toString();
    }

    public static String randomTime(){
        Random random = new Random();
        int hour = random.nextInt(24);
        int min = random.nextInt(60);
        Date date = new Date();
        date.setHours(hour);
        date.setMinutes(min);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
}
