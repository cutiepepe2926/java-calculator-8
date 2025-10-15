package calculator;

import java.util.regex.Pattern;

public class InputValidator {
    // 입력이 비어있는지 확인하는 메서드
    public boolean isEmptyInput(String textLine) {
        return textLine == null || textLine.trim().isEmpty();
    }

    // 유효한 입력값 검증 메서드
    public boolean isInvalidInput(String textLine) {
        String regex = "^[0-9,:]*$";
        return !textLine.matches(regex);
    }

    // 유효한 입력값 검증 (커스텀 구분자 전용 메서드)
    public boolean isInvalidInput(String textLine, String customSeparator) {
        String regex = "^[0-9,:" + Pattern.quote(customSeparator) + "]*$";
        return !textLine.matches(regex);
    }

    // 잘못된 입력(허용되지 않은 문자 포함 시) 예외
    public void handleInvalidInput() {
        throw new IllegalArgumentException("잘못된 입력 형식입니다.");
    }
}
