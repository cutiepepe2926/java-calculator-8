package calculator;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현



        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String textLine = readLine(); //camp.nextstep.edu.missionutils.Console의 static 메서드 readLine()

        // 입력이 비어있는 경우 0을 반환하고 종료
        if (textLine == null || textLine.trim().isEmpty()) {
            System.out.println("결과 : 0");
            return;
        }

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

                // 쉼표(,) 기준으로 숫자 분리
                String[] parts = textLine.split(",");

                // 각 숫자를 합산
                int result = 0;
                for (String part : parts) {
                    result += Integer.parseInt(part);
                }

                // 최종 결과 출력
                System.out.printf("결과 : %d", result);

            }
            else {
                // 잘못된 입력(허용되지 않은 문자 포함 시) 예외 처리
                try {
                    throw new IllegalArgumentException("잘못된 입력");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    throw e; // 프로그램 종료
                }
            }

        }
        // 커스텀 구분자가 존재하지 않는 경우
        else {

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
            }
            else {
                // 잘못된 입력(허용되지 않은 문자 포함 시) 예외 처리
                try {
                    throw new IllegalArgumentException("잘못된 입력");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    throw e; // 프로그램 종료
                }
            }
        }


    }
}
