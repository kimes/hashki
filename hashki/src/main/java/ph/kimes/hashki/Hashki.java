package ph.kimes.hashki;

import java.util.ArrayList;

public class Hashki {

    private String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int length = 8;

    public Hashki() {}

    public Hashki(int length, String alphabet) {
        this.length = length;
        this.alphabet = alphabet;
    }

    public String encode(String text) throws HashkiException {
        try {
            //String alphabet = "0123456789";
            //String text = "B2A2021-11-10T13:57Nagaalps848.000534";
            //int hashLength = 8;

            int[] splitTo = split(text.length(), length);

            String hashText = "";
            int textCountHashed = 0;
            for (int split : splitTo) {
                String toHash = text.substring(textCountHashed, textCountHashed + split);

                int textSum = 0;
                for (int j = 0; j < toHash.length(); j++) {
                    textSum += toHash.charAt(j);
                }
                //System.out.println("hash fragment, " + toHash + ": " + textSum);
                hashText += alphabet.charAt(textSum % alphabet.length());

                textCountHashed += split;
            }
            //System.out.println("Hashed: " + hashText);
            return hashText;
        } catch (HashkiException e) {
            throw e;
        }
    }

    private int[] split(int x, int n) throws HashkiException {
        ArrayList<Integer> splitList = new ArrayList<>();
        if (x < n) {
            throw new HashkiException("Text length must be more than length");
        } else if (x % n == 0) {
            for (int i = 0; i < n; i++) {
                splitList.add(x / n);
            }
        } else {
            int zp = n - (x % n);
            int pp = x / n;
            for (int i = 0; i < n; i++) {
                if (i >= zp) {
                    splitList.add(pp + 1);
                } else {
                    splitList.add(pp);
                }
            }
        }

        int[] split = new int[splitList.size()];
        for (int i = 0; i < splitList.size(); i++) {
            split[i] = splitList.get(i);
        }
        return split;
    }
}
