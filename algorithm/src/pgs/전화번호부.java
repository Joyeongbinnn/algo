package pgs;


import java.util.*;

class 전화번호부 {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book); // 사전순 정렬

        for (int i = 0; i < phone_book.length - 1; i++) {
            // 다음 번호가 현재 번호로 시작하는지 확인
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                return false;
            }
        }

        return true;
    }
}
