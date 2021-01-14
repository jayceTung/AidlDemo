package com.asuper.aidldemo.java;

import com.asuper.aidldemo.collection.TreeNode;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @author SuperM1n
 * @brief description
 * @date 2021-01-12
 */
public class test1 {

    public static void main(String[] args) {


        HashMap<String, Integer> hashMap = new HashMap<>();
        findKthLargest2(new int[]{3,2,1,5,6,4}, 2);

        System.out.println(String2Integer("dsafsad"));

    }

    public ListNode reverseList(ListNode head) {
        ListNode first = null;
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        first = stack.pop();
        ListNode node = first;
        while (!stack.isEmpty()) {
            ListNode next = stack.pop();
            node.next = next;
            node = next;
        }
        return first;
    }


    /**
     * 快慢指针判断有环
     * @param head
     * @return
     */
    private static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    class ListNode {
        int value;
        ListNode next;
    }


    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (Integer integer : nums) {
            queue.add(integer);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    public static int findKthLargest2(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums[k];
    }

    public static int String2Integer(String str) {
        int results = 0;
        for (int i = 0; i < str.length(); i++) {
           int k = str.charAt(i) - '0';
           results = results * 10 + k;
        }
        return results;
    }

    public static int deep(Tree.TreeNode tree) {
        if (tree == null) {
            return 0;
        }
        int max = 0;
        Stack<Tree.TreeNode> stacks = new Stack<>();
        Stack<Integer> heightStacks = new Stack<>();

        stacks.push(tree);
        heightStacks.push(1);

        while (!stacks.isEmpty()) {
            Tree.TreeNode temp = stacks.pop();
            int height = heightStacks.pop();

            if (temp.left != null) {
                stacks.push(temp.left);
                heightStacks.push(height + 1);
            }

            if (temp.right != null) {
                stacks.push(temp.right);
                heightStacks.push(height + 1);
            }

            if (height > max) {
                max = height;
            }
        }

        return max;
    }


}
