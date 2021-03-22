//编写一个函数，以字符串作为输入，反转该字符串中的元音字母。 
//
// 
//
// 示例 1： 
//
// 输入："hello"
//输出："holle"
// 
//
// 示例 2： 
//
// 输入："leetcode"
//输出："leotcede" 
//
// 
//
// 提示： 
//
// 
// 元音字母不包含字母 "y" 。 
// 
// Related Topics 双指针 字符串 
// 👍 148 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseVowels(String s) {
        // String to char[]
        char[] ch = s.toCharArray();
        int left = 0, right = s.length()-1;

        while (left < right) {
            if (isVowel(ch[left]) && isVowel(ch[right])){           //两个都是元音字母
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;
                left++; right--;
            } else if (!isVowel(ch[left]) && isVowel(ch[right])) {  //左不是右是
                left++;
            } else if (isVowel(ch[left]) && !isVowel(ch[right])) {  //右不是左是
                right--;
            } else {        //两个都不是
                left++;
                right--;
            }
        }

        return new String(ch);
    }

    private boolean isVowel(char c) {
        /** 做判断最节省时间, 也可以用Set.contain, 但是时间会长 */
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' )
            return true;
        else
            return false;
    }
}

/** 超时了 因为新建了一个空的char[],耗费了大量内存和时间 */
class ExceedSolution {
    // 用到 char 的引用类型 Character, 函数外定义
    private final static HashSet<Character> vowels =
            new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String reverseVowels(String s) {
        if (s == null) return null;

        /** 需要注意的是, char[] 可以用 char[].length 来求长度, 但是 String 需要用 s.length() 来求长度 */
        char[] ch = new char[s.length()];
        int left = 0, right = s.length() - 1;

        while (left <= right) {
            char charLeft = s.charAt(left);
            char charRight = s.charAt(right);
            if (!vowels.contains(charLeft)) {
                ch[left] = charLeft;
                left++;
            }
            else if (vowels.contains(charRight)) {
                ch[right] = charLeft;
                ch[left] = charRight;
                left++; right--;
            }
            else {
                ch[right] = charRight;
                right--;
            }

            System.out.println(new String(ch));

            /** 简略版,将++放入语句内 */
//            if (!vowels.contains(charLeft)) {
//                ch[left++] = charLeft;
//            }
//            else if (vowels.contains(charRight)) {
//                ch[right--] = charLeft;
//                ch[left++] = charRight;
//            }
//            else {
//                ch[right--] = charRight;
//            }
        }

        // 将char[] 转换成 String的方法, 即String的一个构造函数
        return new String(ch);
    }
}

//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
双指针,类似反转字符串,碰到元音字母了就反转. *注意:元音字母包含大写字母
不能新建一个空的char[], 时间会耗费很多. beat 88 time, 64 memory
*/