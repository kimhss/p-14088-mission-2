package com.back;

public class Calc {

    public static int run(String s) {
        s = s.trim();

        // 필수로
        if(s.contains("-")) {
            s = s.replace("- ", "+ -");
        }

        // 괄호 벗기기 + 괄호 먼저 계산
        if(s.contains("(") && s.contains(")")) {
            String result = "";
            String pareBits = "";
            int depth = 0;

            String[] sBits = s.split("");
            for(int i = 0; i < sBits.length; i++) {
                if(sBits[i].equals("(")) {
                    depth++;
                    if(depth == 1) continue; // 가장 바깥 괄호는 저장하지 않음
                    else pareBits += sBits[i]; // 내부 괄호는 그대로 저장
                    continue;
                }

                if(sBits[i].equals(")")) {
                    depth--;
                    if(depth == 0) {
                        // 괄호 닫힘 → 내부 계산 후 result에 추가
                        result += String.valueOf(run(pareBits));
                        pareBits = "";
                        continue;
                    }
                    else {
                        pareBits += sBits[i];
                        continue;
                    }
                }

                if(depth > 0) {
                    pareBits += sBits[i];
                } else {
                    result += sBits[i];
                }
            }

            return run(result);
        }


        // 덧셈
        if(s.contains("+")) {
            int sum = 0;
            String[] arr = s.split("\\+");
            for(String str : arr) {
                if(str.contains("*")) sum += run(str.trim());
                else sum += Integer.parseInt(str.trim());
            }
            return sum;
        }

        // 곱셈
        if(s.contains("*") && s.contains(" ")) {
            int mul = 1;
            String[] arr = s.split("\\*");
            for(String str : arr) {
                mul *= Integer.parseInt(str.trim());
            }
            return mul;
        }

        return Integer.parseInt(s);
    }
}