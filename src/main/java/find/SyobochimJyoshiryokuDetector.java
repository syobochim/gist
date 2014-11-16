package find;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.classfile.Utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SyobochimJyoshiryokuDetector {
    static final String PATH = "/Users/syobochim/dev/gist/build/classes/main/jjugccc2014/";

    public static void main(String[] args) {
        File dir = new File(PATH);
        List<File> files = Arrays.asList(dir.listFiles());

        files.stream()
                .filter(File::isFile)
                .forEach(file -> {
                    try {
                        JavaClass javaClass = new ClassParser(file.getPath()).parse();
                        System.out.print(javaClass.getClassName() + " : ");
                        if (hasJyoshiryoku(javaClass)) {
                            System.out.println("女子力あります");
                        } else {
                            System.out.println("女子力ないです");
                        }
                        System.out.println();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static boolean hasJyoshiryoku(final JavaClass javaClass) {
        List<Method> methods = Arrays.asList(javaClass.getMethods());

        return methods.stream()
                .map(Method::getCode)
                .map(code -> Utility.codeToString(code.getCode(), javaClass.getConstantPool(), 0, 1, false))
                .flatMap(s -> Arrays.stream(s.split("\n")))
                .anyMatch(s -> s.matches(".*Syobochim\\.jyoshiryoku.*"));

    }

    private static void printJavap(final JavaClass javaClass) {
        List<Method> methods = Arrays.asList(javaClass.getMethods());
        methods.stream()
                .map(Method::getCode)
                .map(code -> Utility.codeToString(code.getCode(), javaClass.getConstantPool(), 0, 1, false))
                .forEach(System.out::println);
    }
}
