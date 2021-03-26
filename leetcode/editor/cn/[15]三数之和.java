//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 3120 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;

        // 排序, O(NlgN)
        Arrays.sort(nums);

        // 开始遍历, i 代表固定的第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;      // 如果和上一个数相同,那么无需进行

            int left = i+1, right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                while (left < right && nums[left] == nums[left + 1]) left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
                if (nums[left] + nums[right] == target){
                    // 快速添加list
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));
                    left++;
                    right--;
//                    while (left < right && nums[left] == nums[left - 1]) left++;
//                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] > target)
                    right--;
                else if (nums[left] + nums[right] < target)
                    left++;
            }
        }

        return ans;

        // beat 49 time, 71 memory
    }
}

/** leetcode官方的解答方法 */
class LeetcodeSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) return ans;

        // 排序, O(NlgN)
        Arrays.sort(nums);

        // 开始多重遍历,i 代表固定的第一个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;         //如果第一个数大于0, 那个后面的数加起来不可能=0,直接break
            if (i > 0 && nums[i] == nums[i - 1]) continue;      // 如果和上一个数相同,那么无需进行

            // 开始双指针
            int right = nums.length - 1;
            for (int left = i+1; left < nums.length; left++) {
                if (left > i+1 && nums[left] == nums[left-1])  continue;     //跳过一样的left (可以用while改写)

                while (left < right && nums[left] + nums[right] + nums[i] > 0) {      //跳过 nums[right] 太大的情况
                    right--;
                }

                if (right == left)      // 左右指针重叠的时候,就不可能了
                    break;

                // 确定 i 和 left之后,只可能有一个数满足三数之和为0
                if (nums[left] + nums[right] + nums[i] == 0){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[i]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        ans.add(list);
                }
            }
        }

        return ans;
        // beat 67 time, 57 memory
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
排序 + 双指针
暴力法的时间复杂度是 O(n^3)。可以先固定一个值，然后寻找后两个值时可采取双指针的方法，将总的时间复杂度优化到 O(n^2)。
实现的过程中，要注意优化以及去重。 因为要求的是不重复元素.
首先我们先对原数组进行排序，这样可以把重复的值集中到一起，便于去重。
确定第一个元素时，如果它已经比 0 大了，那么可以直接跳出循环，因为后面的数字都比它大。如 [1, 2, 3, 4], i = 0, nums[i] > 0, 这样是不可能产生合法的情况的，直接 break。
确定第一个元素时，如果发现它与它前面的值一样，那么跳过本轮。如 [-1, -1, 0, 1], 在第一轮后，已经选出了 {-1, 0, 1}, 现在 i = 1，nums[i] == nums[i - 1], 为了避免重复，直接 continue。
接下来利用双指针，left 指向 i + 1, right 指向 nums.length - 1。逐个进行判断，并注意去重。
*/