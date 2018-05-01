package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import com.google.common.base.Stopwatch;
import com.ttrlalgs.algorithm.sort.Sort;

public class MyTimSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        LinkedList<LinkedList<T>> chain = new LinkedList<>();

        List<T> linkedColl = new LinkedList<>(collection);

        while (!linkedColl.isEmpty()) {
            ListIterator<T> iterator = linkedColl.listIterator();
            LinkedList<T> block = new LinkedList<>();
            LinkedList<T> revBlock = new LinkedList<>();
//            chain.add(block);
            chain.add(revBlock);
            while (iterator.hasNext()) {
                T e = iterator.next();

                if (block.size() == 0 || comparator.compare(e, block.getLast()) >= 0) {
                    block.addLast(e);
                    iterator.remove();
                    continue;
                }

                if (comparator.compare(e, block.getFirst()) <= 0 && (revBlock.size() == 0 || comparator.compare(e, revBlock.getFirst()) <= 0)) {
                    revBlock.addFirst(e);
                    iterator.remove();
                    continue;
                }
            }

//            int size = revBlock.size();
//            boolean same =false;
/*            if (!revBlock.isEmpty() && !block.isEmpty()) {
                if(comparator.compare(block.getFirst(), revBlock.getLast()) < 0) {
                    T t = block.removeFirst();
                    T t1 = revBlock.removeLast();
                    block.addFirst(t1);
                    revBlock.addLast(t);
                    same = true;
                }
            }*/
            revBlock.addAll(block);
//                chain.add(revBlock);
/*            boolean isSorted = SortTestUtils.isSorted(revBlock, comparator);
            if (!isSorted) {
                System.out.println("Unsorted: " + same);
            }*/
//            }
        }

        Collection<T> result = new ArrayList<>(collection.size());
        System.out.println("Chain created: " + stopwatch.toString());
/*        int count = 0;
        for (LinkedList<T> ts : chain) {
            boolean isSorted = SortTestUtils.isSorted(ts, comparator);
            if (!isSorted) {
                System.out.println("Unsorted: " + ++count);
            }
        }*/

        while (chain.size() > 0) {
            T min = null;
            LinkedList<T> minBlock = null;

            Iterator<LinkedList<T>> it = chain.iterator();
            while (it.hasNext()) {
                LinkedList<T> block = it.next();
                if (block.size() == 0) {
                    it.remove();
                    continue;
                }

                T firstE = block.getFirst();
                if (min == null) {
                    min = firstE;
                    minBlock = block;
                    continue;
                }

                if (comparator.compare(firstE, min) < 0) {
                    min = firstE;
                    minBlock = block;
                }

            }

            if (minBlock != null) {
                result.add(minBlock.removeFirst());
            }
        }
        System.out.println("Stopped: " + stopwatch.stop());

        return result;
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        return sort(collection, Comparator.naturalOrder());
    }
}
