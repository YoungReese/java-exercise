package com.ly.stream.chap13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * liyang 2021-04-08
 *
 * 求子集
 */
public class SubsetsMain {

    public static void main(String[] args) {
        List<List<Integer>> subs = subsets(Arrays.asList(1, 4, 9));
        subs.forEach(System.out::println);
    }

    public static List<List<Integer>> subsets(List<Integer> l) {
        if (l.isEmpty()) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(Collections.emptyList());
            return ans;
        }
        Integer first = l.get(0);
        List<Integer> rest = l.subList(1, l.size());
        List<List<Integer>> subans = subsets(rest);             // first 不出现
        List<List<Integer>> subans2 = insertAll(first, subans); // first 出现
        return concat(subans, subans2);
    }

    public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> l : lists) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(l);
            result.add(copyList);
        }
        return result;
    }

    static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
        List<List<Integer>> r = new ArrayList<>(a);
        r.addAll(b);
        return r;
    }

}
