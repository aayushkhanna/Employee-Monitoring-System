/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import project.*;

public class Validation {

    public static boolean isValidName(String x) {
        if (x.length() < 3) {
            return false;
        }

        if (x.startsWith(".")) {
            return false;
        }

        if (x.contains("..")) {
            return false;
        }

        if (x.contains(". ")) {
            return false;
        }

        if (x.contains(" .")) {
            return false;
        }

        String h = x.toLowerCase();
        char a;
        for (int i = 0; i < h.length(); i++) {
            a = h.charAt(i);
            if (a < 97 || a > 122) {
                if (a != '.' && a != ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isValidEmail(String s) {
        boolean b1 = s.endsWith(".com");
        boolean b2 = s.endsWith(".co.in");
        int count=0;
        if (b1 == true || b2 == true) {
            if (s.lastIndexOf("@") == s.indexOf("@") && s.indexOf("@") != 0) {

                for (int i = 0; i < s.indexOf("@"); i++) {
                    char x = s.charAt(i);
                    if ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z') || (x >= '0' && x <= '9') || (x == '.') || (x == '_') || (x == '-')) {
                        count++;
                    } else {
                        break;
                    }
                }
                for (int j = s.indexOf("@") + 1; j < s.indexOf('.'); j++) {
                    char x = s.charAt(j);
                    if ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z')) {
                        count++;
                    } else {
                        break;
                    }
                }

                if (b1 == true) {
                    if (count == s.length() - 5) {
                        return true;
                    }
                }
                if (b2 == true) {
                    if (count == s.length() - 7) {
                       return true;
                    }
                }
            }

        }
        
        return false;
    }
}

