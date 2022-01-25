package com.rzaaeeff.datastructalgo.recursion_and_dp.problems.ctci;

// Write a method to return all subsets of a set.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PowerSet {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

        ArrayList<ArrayList<Integer>> powerSet1 = getSubsets(list, 0);
//        for (List<Integer> subset: powerSet1) {
//            subset.sort(Integer::compare);
//        }
//        powerSet1.sort((a, b) -> a.size() > 0 && b.size() > 0 ? Integer.compare(a.get(0), b.get(0)) : -1);
//        powerSet1.sort(Comparator.comparingInt(ArrayList::size));

        List<List<Integer>> powerSet2 = getSubsetsIterative(list);
//        for (List<Integer> subset: powerSet2) {
//            subset.sort(Integer::compare);
//        }
//        powerSet2.sort((a, b) -> a.size() > 0 && b.size() > 0 ? Integer.compare(a.get(0), b.get(0)) : -1);
//        powerSet2.sort(Comparator.comparingInt(List::size));
//
//        System.out.println(powerSet1);
//        System.out.println(powerSet2);
    }

//    private static List<List<Integer>> generateAllSubsets(List<Integer> list) {
//        return generateAllSubsetsHelper(list, new ArrayList<>());
//    }
//
//    private static List<List<Integer>> generateAllSubsetsHelper(List<Integer> list, List<List<Integer>> prefix) {
//
//    }

    private static ArrayList<ArrayList<Integer>> getSubsets(List<Integer> set, int index) {
        ArrayList<ArrayList<Integer>> allsubsets;
        if (set.size() == index) {
            //Base case - add empty set
            allsubsets = new ArrayList<>();
            allsubsets.add(new ArrayList<>()); // Empty set
        } else {
            allsubsets = getSubsets(set, index + 1);
            int item = set.get(index);
            ArrayList<ArrayList<Integer>> moresubsets = new ArrayList<>();
            for (ArrayList<Integer> subset : allsubsets) {
                ArrayList<Integer> newsubset = new ArrayList<>(subset);
                newsubset.add(item);
                moresubsets.add(newsubset);
            }
            allsubsets.addAll(moresubsets);
        }

        return allsubsets;
    }

    private static List<List<Integer>> getSubsetsIterative(List<Integer> set) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        allSubsets.add(new ArrayList<>());

        for (int i = 0; i < set.size(); i++) {
            List<List<Integer>> newSubsets = new ArrayList<>();
            for (List<Integer> subset : allSubsets) {
                List<Integer> subsetCopy = new ArrayList<>(subset);
                subsetCopy.add(set.get(i));
                newSubsets.add(subsetCopy);
            }
            allSubsets.addAll(newSubsets);
        }

        return allSubsets;
    }
}
