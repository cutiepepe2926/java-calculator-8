package calculator;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String textLine = readLine(); //camp.nextstep.edu.missionutils.Console의 static 메서드 readLine()

        // 커스텀 구분자를 찾는 정규표현식 패턴
        Pattern pattern = Pattern.compile("^//(.)(?:\\\\n)");

        // 커스텀 구분자 추출
        Matcher matcher = pattern.matcher(textLine);

        // 커스텀 구분자 존재하는 지 판별
        if(matcher.find()){

            // 커스텀 구분자를 담을 변수 생서
            String customSeparator = matcher.group(1);

            // matcher.end() → //X\n 뒤의 실제 숫자 구간만 남기기
            textLine = textLine.substring(matcher.end());

            // 숫자, 기본 구분자(, :), 커스텀 구분자 정규식
            String regex = "^[0-9,:" + Pattern.quote(customSeparator) + "]*$";

            // 숫자, 기본 구분자(, :), 커스텀 구분자만 포함하는지 검사
            if(textLine.matches(regex)){

                // 입력 문자열 내의 ":" 또는 커스텀 구분자를 ","로 통일
                textLine = textLine.replaceAll(":|" + Pattern.quote(customSeparator), ",");

            }
            else {
                System.out.println("문제있음");
            }

        }
        else {
            System.out.println("커스텀 구분자 존재 X");

        }


    }
}
