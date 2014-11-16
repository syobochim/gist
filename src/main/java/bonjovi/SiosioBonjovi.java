package bonjovi;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SiosioBonjovi {

    private static final List<String> INPUT = Arrays.asList("ｼﾞｮ", "ﾝ", "ﾎﾞ", "ｳﾞｨ");

    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws Exception {

        MySupplier supplier = () -> RANDOM.ints(0, INPUT.size())
                .limit(2)
                .mapToObj(INPUT::get)
                .collect(Collectors.joining());

        String result = Stream.iterate(supplier.get(), (s) -> s + '・' + supplier.get())
                .filter(s -> s.endsWith("ｼﾞｮﾝ・ﾎﾞﾝ・ｼﾞｮｳﾞｨ"))
                .findFirst()
                .get();

        System.out.println(result);
        System.out.println(result.split("・").length + " Bon Jovis");
        System.out.println("\n" +
                "＿人人人人人人人人人人人人人人＿\n" +
                "＞　You Give Love a Bad Name　＜\n" +
                "￣Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y^Y￣");
    }

    interface MySupplier extends Supplier<String>, Serializable {
    }
}
