package calculator;

// 일반 import
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 정적 import
import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        // camp.nextstep.edu.missionutils.Console의 static 메서드 readLine()
        String textLine = readLine();

        // 입력이 비어있는 경우 0을 반환하고 종료
        if (isEmpty(textLine)) {
            printResult(0);
            return;
        }

        if (hasCustomSeparator(textLine)) {
            handleCustomSeparator(textLine);
            return;
        }

        handleDefaultSeparator(textLine);
    }

    // 입력이 비어있는지 확인
    private static boolean isEmpty(String textLine) {
        return textLine == null || textLine.trim().isEmpty();
    }

    // 결과 출력
    private static void printResult(int result) {
        System.out.printf("결과 : %d", result);
    }

    // 커스텀 구분자 존재 여부 확인
    private static boolean hasCustomSeparator(String textLine) {
        Pattern pattern = Pattern.compile("^//(.)(?:\\\\n)");
        Matcher matcher = pattern.matcher(textLine);
        return matcher.find();
    }

    // 커스텀 구분자 처리
    private static void handleCustomSeparator(String textLine) {
        Pattern pattern = Pattern.compile("^//(.)(?:\\\\n)");
        Matcher matcher = pattern.matcher(textLine);
        matcher.find();

        String customSeparator = matcher.group(1);
        textLine = textLine.substring(matcher.end());

        String regex = "^[0-9,:" + Pattern.quote(customSeparator) + "]*$";
        if (!textLine.matches(regex)) {
            handleInvalidInput();
            return;
        }

        textLine = normalizeSeparators(textLine, customSeparator);
        int result = calculateSum(textLine);
        printResult(result);
    }

    // 기본 구분자 처리
    private static void handleDefaultSeparator(String textLine) {
        String regex = "^[0-9,:]*$";
        if (!textLine.matches(regex)) {
            handleInvalidInput();
            return;
        }

        textLine = textLine.replaceAll(":", ",");
        int result = calculateSum(textLine);
        printResult(result);
    }

    // 입력 문자열 내 구분자 통일
    private static String normalizeSeparators(String textLine, String customSeparator) {
        return textLine.replaceAll(":|" + Pattern.quote(customSeparator), ",");
    }

    // 문자열을 숫자로 변환 후 합산
    private static int calculateSum(String textLine) {
        String[] parts = textLine.split(",");
        int result = 0;
        for (String part : parts) {
            result += Integer.parseInt(part);
        }
        return result;
    }

    // 잘못된 입력(허용되지 않은 문자 포함 시) 예외 처리
    private static void handleInvalidInput() {
        throw new IllegalArgumentException();
    }
}
