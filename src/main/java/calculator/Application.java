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
            System.out.println("커스텀 구분자 존재 O");
        }
        else {
            System.out.println("커스텀 구분자 존재 X");

        }


    }
}
