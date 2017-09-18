package com.ttrlalgs.algorithm.sort.nlogn;

import java.util.*;
import com.ttrlalgs.algorithm.sort.Sort;

public class MyTimSort implements Sort {

    @Override
    public <T> Collection<T> sort(Collection<T> collection, Comparator<T> comparator) {
        int initSize = collection.size();
        LinkedList<LinkedList<T>> chain = new LinkedList<>();
        chain.add(new LinkedList<>());

        List<T> linkedColl = new LinkedList<>(collection);

        ListIterator<T> iterator = linkedColl.listIterator();
        while (iterator.hasNext()) {
            T e = iterator.next();

            boolean wasAdded = false;
            for (LinkedList<T> block : chain) {
                if (block.size() == 0) {
                    block.addFirst(e);
                    wasAdded = true;
                    break;
                }

//                T lastE = block.get(block.size() - 1);
                T lastE = block.getLast();
                if (comparator.compare(e, lastE) >= 0) {
                    block.add(e);
                    wasAdded = true;
                    break;
                }
            }

            if (!wasAdded) {
                LinkedList<T> block = new LinkedList<>();
                block.add(e);
                chain.add(block);
            }

            iterator.remove();
        }

        Collection<T> result = new ArrayList<>(collection.size());

        while (chain.size() > 0) {
            T min = null;
            LinkedList<T> minBlock = null;
//            List<List<T>> toRemove = new ArrayList<>();

            Iterator<LinkedList<T>> it = chain.iterator();
            while (it.hasNext()) {
                LinkedList<T> block = it.next();
                if (block.size() == 0) {
                    it.remove();
//                    toRemove.add(block);
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
/*
            for (List<T> block : chain) {
                if (block.size() == 0) {
//                    toRemove.add(block);
                    continue;
                }

                T firstE = block.get(0);
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
*/

            if (minBlock != null) {
                result.add(minBlock.removeFirst());
            }

//            chain.removeAll(toRemove);
        }

        return result;
    }

    @Override
    public <T extends Comparable<T>> Collection<T> sort(Collection<T> collection) {
        return sort(collection, Comparator.naturalOrder());
    }
}
