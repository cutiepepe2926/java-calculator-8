package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeparatorHandler {
    // 커스텀 구분자 존재 여부 확인 메서드
    public boolean hasCustomSeparator(String textLine) {
        Pattern pattern = Pattern.compile("^//(.)\\\\n");
        Matcher matcher = pattern.matcher(textLine);
        return matcher.find();
    }

    // 커스텀 구분자 추출 메서드
    public Matcher extractCustomSeparator(String textLine) {
        Pattern pattern = Pattern.compile("^//(.)\\\\n");
        Matcher matcher = pattern.matcher(textLine);
        matcher.find();
        return matcher;
    }

    // 숫자 부분만 추출 (//X\n 제거) 메서드
    public String extractNumberPart(String textLine, Matcher matcher) {
        return textLine.substring(matcher.end());
    }

    // 입력 문자열 내 구분자 통일(커스텀 구분자가 있는 경우) 메서드
    public String normalizeSeparators(String textLine, String customSeparator) {
        return textLine.replaceAll(":|" + Pattern.quote(customSeparator), ",");
    }

    // 입력 문자열 내 구분자 통일(커스텀 구분자가 없는 경우) 메서드
    public String normalizeSeparators(String textLine) {
        return textLine.replaceAll(":", ",");
    }

    // 구분자 콤마( , )로 파싱 후 배열로 반환 메서드
    public String[] separateTextLines(String textLine) {
        return textLine.split(",");
    }
}
