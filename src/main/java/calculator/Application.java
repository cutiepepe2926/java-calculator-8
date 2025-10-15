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

        // 커스텀 구분자를 찾는 정규표현식 패턴
        Pattern pattern = Pattern.compile("^//(.)(?:\\\\n)");

        // 커스텀 구분자 추출
        Matcher matcher = pattern.matcher(textLine);

        // 커스텀 구분자 존재하는 지 판별
        if (matcher.find()) {

            // 커스텀 구분자를 담을 변수 생성
            String customSeparator = matcher.group(1);

            // matcher.end() → //X\n 뒤의 실제 숫자 구간만 남기기
            textLine = textLine.substring(matcher.end());

            // 숫자, 기본 구분자(, :), 커스텀 구분자 정규식
            String regex =
                    "^[0-9,:" + Pattern.quote(customSeparator) + "]*$";

            // 숫자, 기본 구분자(, :), 커스텀 구분자만 포함하는지 검사
            if (textLine.matches(regex)) {

                // 입력 문자열 내의 ":" 또는 커스텀 구분자를 ","로 통일
                textLine = textLine.replaceAll(":|" +
                        Pattern.quote(customSeparator), ",");

                // 쉼표(,) 기준으로 숫자 분리
                String[] parts = textLine.split(",");

                // 각 숫자를 합산
                int result = 0;
                for (String part : parts) {
                    result += Integer.parseInt(part);
                }

                // 최종 결과 출력
                System.out.printf("결과 : %d", result);

            } else {
                // 잘못된 입력(허용되지 않은 문자 포함 시) 예외 처리
                handleInvalidInput();
            }

        } else { // 커스텀 구분자가 존재하지 않는 경우

            // 숫자, 쉼표(,), 콜론(:)만 허용하는 정규식
            String regex = "^[0-9,:]*$";
            if (textLine.matches(regex)) {
                // ":"를 ","로 변환 (기본 구분자 통일)
                textLine = textLine.replaceAll(":", ",");

                // 쉼표(,) 기준으로 숫자 분리
                String[] parts = textLine.split(",");

                // 각 숫자를 합산
                int result = 0;
                for (String part : parts) {
                    result += Integer.parseInt(part);
                }

                // 최종 결과 출력
                System.out.printf("결과 : %d", result);
            } else {
                // 잘못된 입력(허용되지 않은 문자 포함 시) 예외 처리
                handleInvalidInput();
            }
        }
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
