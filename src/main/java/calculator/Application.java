package calculator;

// 일반 import
import java.util.regex.Matcher;

// 정적 import
import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        // 객체 생성
        InputValidator validator = new InputValidator();
        SeparatorHandler handler = new SeparatorHandler();
        Calculator calculator = new Calculator();
        ResultPrinter printer = new ResultPrinter();

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        // camp.nextstep.edu.missionutils.Console의 static 메서드 readLine()
        String textLine = readLine();

        // 입력이 비어있는 경우 -> 결과 0 출력 후 종료
        if (validator.isEmptyInput(textLine)) {
            printer.printResult(0);
            return;
        }

        // 커스텀 구분자 존재 시
        if (handler.hasCustomSeparator(textLine)) {
            // 커스텀 구분자 추출
            Matcher matcher = handler.extractCustomSeparator(textLine);
            String customSeparator = matcher.group(1);

            // "//X\n" 이후 숫자 부분만 추출
            textLine = handler.extractNumberPart(textLine, matcher);

            // 입력값 유효성 검증 (커스텀 구분자 포함)
            if (validator.isInvalidInput(textLine, customSeparator)) {
                validator.handleInvalidInput();
            }

            // 모든 구분자를 ',' 기준으로 통일
            textLine = handler.normalizeSeparators(textLine, customSeparator);

            // 문자열을 ',' 기준으로 분리 -> 숫자 배열 반환
            String[] parts = handler.separateTextLines(textLine);

            // 숫자 합계 계산
            int result = calculator.calculateSum(parts);

            // 결과 출력
            printer.printResult(result);

            return;
        }

        // 커스텀 구분자가 없는 경우
        // 입력값 유효성 검증
        if (validator.isInvalidInput(textLine)) {
            validator.handleInvalidInput();
        }

        // ':'를 ','로 통일
        textLine = handler.normalizeSeparators(textLine);

        // 문자열을 ',' 기준으로 분리 -> 숫자 배열 반환
        String[] parts = handler.separateTextLines(textLine);

        // 숫자 합계 계산
        int result = calculator.calculateSum(parts);

        // 결과 출력
        printer.printResult(result);
    }
}