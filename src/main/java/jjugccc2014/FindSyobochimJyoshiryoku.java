package jjugccc2014;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static jjugccc2014.util.Syobochim.jyoshiryoku;

public class FindSyobochimJyoshiryoku {
    public static void main(String[] args) {

        jyoshiryoku();

        List<String> bonJovi = Arrays.asList("ｼﾞｮ", "ﾝ", "ﾎﾞ", "ｳﾞｨ");

        Random random = new Random();
        String johnBonJovi = "";
        int count = 0;
        while (!johnBonJovi.endsWith("ｼﾞｮﾝ・ﾎﾞﾝ・ｼﾞｮｳﾞｨ")) {
            johnBonJovi = johnBonJovi + "・";
            String twoWords = random
                    .ints(0, bonJovi.size())
                    .limit(2)
                    .mapToObj(bonJovi::get)
                    .collect(Collectors.joining());
            johnBonJovi = johnBonJovi + twoWords;
            count++;
        }

        System.out.println(johnBonJovi);
        System.out.println(count + " Bon Jovis");

    }

}
