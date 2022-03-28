package com.personal.cl.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class Code {

    @Test
    public void test() {
        var res = this.truncateSentence("Hello how are you Contestant", 4);
        System.out.println(res + "end");
    }

    Character startCharacter = 'a';

    List<Character> list = List.of(startCharacter,'e','i','o','u');

    Iterator<Character> iterator = list.iterator();

    public int longestBeautifulSubstring(String word) {
        int max = 0, len = word.length(), start = -1;
        if (len < 5) {
            return max;
        }
        char next = this.iterator.next();
        char[] chars = word.toCharArray();
        for (int i = 0; i < len; i++) {
            char c = chars[i]; int sL = i;
            for (int j = i + 1; j < len; j++) {
                if (chars[j] == chars[i]) sL = j;
                else break;
            }
            if (c == startCharacter) start = i;
            if (c == next) {
                if (!iterator.hasNext() && start > -1) {
                    max = Math.max(max, sL - start + 1);
                    this.iterator = this.list.iterator();
                }
                next = this.iterator.next();
            } else {
                this.iterator = this.list.iterator();
                next = this.iterator.next();
                if (c == startCharacter) next = this.iterator.next();
            }
            i = sL;
        }
        return max;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        while (k > 0) {
            Arrays.sort(nums);
            if (nums[0] < 0) {
                nums[0] = - nums[0]; k--;
            } else if (nums[0] == 0) {
                break;
            } else {
                nums[0] = k % 2 == 0 ? nums[0] : - nums[0]; break;
            }
        }
        return IntStream.of(nums).sum();
    }

    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && --k < 1) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        for (String string : board) {
            char[] charArray = string.toCharArray();
            for (char c : charArray) {
                if (c == 'X') xCount++;
                if (c == 'O') oCount++;
            }
        }
        if (Math.abs(oCount - xCount) > 1) {
            return false;
        }
        if (this.check(board, 'X') && oCount != xCount - 1) {
            return false;
        }
        return !this.check(board, 'O') || oCount == xCount;
    }

    private boolean check(String[] board, char player) {
        String win = new String(new char[]{player, player, player});
        boolean flag1 = true, flag2 = true;
        for (int i = 0; i < 3; i++) {
            if (win.equals(board[i])) return true;
            if (win.equals(new String(new char[]{board[0].charAt(i), board[1].charAt(i), board[2].charAt(i)}))) return true;
            flag1 &= board[i].charAt(i) == player;
            flag2 &= board[i].charAt(2 - i) == player;
        }
        return flag1 | flag2;
    }

    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] source = new int[26];
        char[] chars = licensePlate.toCharArray();
        for (char c : chars) {
            if (Character.isLetter(c)) source[Character.toLowerCase(c) - 'a']++;
        }
        int idx = -1;
        for (int k = 0; k < words.length; k++) {
            String string = words[k];
            int[] target = new int[26];
            char[] arr = string.toCharArray();
            for (char c : arr) {
                target[c - 'a']++;
            }
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (source[i] < target[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag && (idx == -1 || string.length() < words[idx].length())) {
                idx = k;
            }
        }
        return words[idx];
    }

}
