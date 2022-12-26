package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author SwordFlame
 */
public class Password {
    private static final String haveBeen = "haveBeen";
    private static final String success = "success";
    private static final String error = "error";
    private static HashMap<String, String> passwordSet = new HashMap<>();
    private static HashSet<String> ipSet = new HashSet<>();

    public static String insert(String name, String password){
        if (!passwordSet.containsKey(name)) {
            passwordSet.put(name, password);
            Winlose.wins.put(name, 0);
            Winlose.lose.put(name, 0);
            Winlose.score.put(name, (double) 0);
            return success;
        } else if (!Objects.equals(passwordSet.get(name), password)) {
            return error;
        } else {
            return haveBeen;
        }
    }
    public static String insertip(String ip){
        if (!ipSet.contains(ip)) {
            ipSet.add(ip);
            Winlose.wins.put(ip, 0);
            Winlose.lose.put(ip, 0);
            Winlose.score.put(ip, (double) 0);
        }
        return success;
    }
    public static String check(String name, String password){
        if (!passwordSet.containsKey(name)) {
            return error;
        } else if (!Objects.equals(passwordSet.get(name), password)) {
            return error;
        } else {
            return success;
        }
    }
    public static String change(String name, String password){
        if (!passwordSet.containsKey(name)) {
            return insert(name, password);
        } else if (!Objects.equals(passwordSet.get(name), password)) {
            passwordSet.replace(name, password);
            return success;
        } else {
            return success;
        }
    }
}
