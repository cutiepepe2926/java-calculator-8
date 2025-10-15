package calculator;

public class Calculator {
    // 문자열을 숫자로 변환 후 합산 메서드
    public int calculateSum(String[] parts) {
        int result = 0;
        for (String part : parts) {
            if (part == null || part.trim().isEmpty()) {
                part = "0"; // 부분 빈 문자열 처리
            }
            result += Integer.parseInt(part.trim());
        }
        return result;
    }
}
