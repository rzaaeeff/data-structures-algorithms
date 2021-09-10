package com.rzaaeeff.datastructalgo.arrays_strings.impl


import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class ArrayListTest extends Specification {
    ArrayList<Integer> arrayListSpy

    @Shared
    def initialCapacity = 10

    void setup() {
        arrayListSpy = Spy(new ArrayList<>(initialCapacity))
    }

    @Unroll
    def "EnsureCapacity"() {
        given:
        def array = [0, 1, 2, 3, 4, 5, 6, 7]
        arrayListSpy.addAll(array)

        when:
        arrayListSpy.ensureCapacity(index)

        then:
        arrayListSpy.size() == 8
        arrayListSpy.array.length == size
        arrayListSpy.toArray() == array

        where:
        index | size
        0     | initialCapacity
        10    | initialCapacity * 2
    }

    @Unroll
    def "Add"(List<Integer> elements) {
        when:
        arrayListSpy.addAll(elements)

        then:
        elements.size() * arrayListSpy.ensureCapacity(_)
        arrayListSpy.size() == elements.size()

        where:
        elements << [0..1, 0..5, 0..9, 0..15]
    }

    @Unroll
    def "Add With Index"(index, element) {
        given:
        def oldVals = (100..200).toList()
        arrayListSpy.addAll(oldVals)

        when:
        arrayListSpy.add(index, element)

        then:
        1 * arrayListSpy.rangeCheck(index)
        1 * arrayListSpy.ensureCapacity(arrayListSpy.size() + 1)
        arrayListSpy.size() == oldVals.size() + 1

        def result = arrayListSpy.toArray().toList()
        result[index] == element
        result.subList(0, index) == oldVals.subList(0, index)
        result.subList(index + 1, result.size() - 1) == oldVals.subList(index, oldVals.size() - 1)

        where:
        index | element
        0     | 129049
        3     | 4567
        99    | 23525
    }

    def "TrimToSize"(List<Integer> elements, int size, List<Integer> newElements) {
        given:
        arrayListSpy.addAll(elements)

        when:
        arrayListSpy.trimToSize()

        then:
        arrayListSpy.size() == size
        arrayListSpy.array == newElements

        where:
        elements | size            | newElements
        0..11    | elements.size() | elements
        0..15    | elements.size() | elements
        0..3     | 10              | elements + [null] * 6
    }

    @Unroll
    def "Size"(elements) {
        given:
        arrayListSpy.addAll(elements)

        when:
        def actual = arrayListSpy.size()

        then:
        actual == elements.size()

        where:
        elements << [[], 0..11, 0..2]
    }

    def "IsEmpty"(elements, expected) {
        given:
        arrayListSpy.addAll(elements)

        when:
        def actual = arrayListSpy.isEmpty()

        then:
        actual == expected

        where:
        elements | expected
        []       | true
        [1]      | false
    }

    @Unroll
    def "Contains"(elements, test, expected) {
        given:
        arrayListSpy.addAll(elements)

        when:
        def actual = arrayListSpy.contains(test)

        then:
        actual == expected

        where:
        elements | test | expected
        0..2     | 0    | true
        0..2     | 1    | true
        0..2     | 2    | true
        0..2     | 3    | false
        0..2     | 4    | false
    }

    @Unroll
    def "ToArray"(elements) {
        given:
        arrayListSpy.addAll(elements)

        when:
        def actual = arrayListSpy.toArray()

        then:
        actual == elements.toArray()

        where:
        elements << [0..0, 0..2, 5..3, 6..90, 129401..129904]
    }

    @Unroll
    def "Remove With Object"(java.util.ArrayList<Integer> elements, test, expected) {
        given:
        arrayListSpy.addAll(elements)
        elements.removeElement(test)

        when:
        def actual = arrayListSpy.remove((Object) test)

        then:
        actual == expected
        arrayListSpy.toArray() == elements.toArray()

        where:
        elements  | test | expected
        0..4      | 3    | true
        0..4 * 2  | 3    | true
        0..4      | 5    | false
        900..2212 | 3    | false
    }

    @Unroll
    def "Remove With Index"(java.util.ArrayList<Integer> elements, int test, expected) {
        given:
        arrayListSpy.addAll(elements)
        elements.remove(test)

        when:
        def actual = arrayListSpy.remove(test)

        then:
        1 * arrayListSpy.rangeCheck(test)
        actual == expected
        arrayListSpy.toArray() == elements.toArray()

        where:
        elements  | test | expected
        1..4      | 3    | 4
        0..4 * 2  | 3    | 3
        0..4      | 4    | 4
        900..2212 | 3    | 903
    }

    @Unroll
    def "RangeCheck"(java.util.ArrayList<Integer> elements, int test) {
        given:
        arrayListSpy.addAll(elements)

        when:
        arrayListSpy.rangeCheck(test)

        then:
        thrown(IndexOutOfBoundsException)

        where:
        elements | test
        _        | -1
        _        | -33
        0..3     | 4
        23..25   | 3
    }

    @Unroll
    def "ContainsAll"(elements, tests, responses, expected) {
        given:
        arrayListSpy.addAll(elements)

        when:
        def actual = arrayListSpy.containsAll(tests)

        then:
        1 * arrayListSpy.contains(tests[0]) >> responses[0]
        (responses[0] ? 1 : 0) * arrayListSpy.contains(tests[1]) >> responses[1]
        actual == expected

        where:
        elements | tests    | responses     | expected
        0..5     | 'a'..'b' | [true] * 2    | true
        0..5     | 'a'..'b' | [false] * 2   | false
        0..5     | 'a'..'b' | [false, true] | false
        0..5     | 'a'..'b' | [true, false] | false

    }

    @Unroll
    def "AddAll"(elements) {
        when:
        arrayListSpy.addAll(elements)

        then:
        elements.each {
            1 * arrayListSpy.add(it) >> {}
        }

        where:
        elements << [1..3, 5..13, 3..6]
    }

    @Unroll
    def "AddAll With Index"(elements, index) {
        given:
        arrayListSpy.addAll(9..24)

        when:
        arrayListSpy.addAll(index, elements)

        then:
        for (int i = 0; i < elements.size(); i++) {
            1 * arrayListSpy.add(index + i, elements[i]) >> {}
        }

        where:
        elements | index
        5..6     | 3
        5..45    | 13
        2..36    | 10
    }

    @Unroll
    def "RemoveAll"(elements) {
        when:
        arrayListSpy.removeAll(elements)

        then:
        elements.each {
            1 * arrayListSpy.remove(it) >> {}
        }

        where:
        elements << [1..3, 5..13, 3..6]
    }

    def "Clear"() {
        given:
        arrayListSpy.addAll(0..123)

        when:
        arrayListSpy.clear()

        then:
        def result = arrayListSpy.toArray()
        result == [null] * result.size()
    }

    @Unroll
    @SuppressWarnings("ChangeToOperator")
    def "Equals"(test1, test2, expected) {
        given:
        def list1 = new ArrayList()
        list1.addAll(test1)
        def list2 = new ArrayList()
        list2.addAll(test2)

        when:
        def actual = list1.equals(list2)

        then:
        actual == expected

        where:
        test1     | test2     | expected
        1..24     | 1..24     | true
        1..24     | 1..23     | false
        345..6987 | 345..6987 | true
        345..6987 | 346..6987 | false
    }

//    def "HashCode"(elements) {
//        given:
//        arrayListSpy.addAll(elements)
//        def arrayHashCode = Arrays.hashCode(elements.toArray())
//
//        when:
//        def actual = arrayListSpy.hashCode()
//
//        then:
//        actual == 31 * arrayHashCode + arrayListSpy.size()
//
//        where:
//        elements << [1..5]
//    }

    def "Get"(test, expected) {
        given:
        arrayListSpy.addAll(60..90)

        when:
        def actual = arrayListSpy.get(test)

        then:
        1 * arrayListSpy.rangeCheck(test)
        actual == expected

        where:
        test | expected
        1    | 61
        15   | 75
        22   | 82
    }

    def "Set"(index, element) {
        given:
        def oldVals = 100..200
        arrayListSpy.addAll(oldVals)

        when:
        def actual = arrayListSpy.set(index, element)

        then:
        1 * arrayListSpy.rangeCheck(index)
        actual == oldVals[index]
        arrayListSpy.get(index) == element

        where:
        index | element
        12    | 24
        56    | 12314
        77    | 1290391
    }

    def "IndexOf"(test, expected) {
        given:
        arrayListSpy.addAll(50..350)

        when:
        def actual = arrayListSpy.indexOf(test)

        then:
        actual == expected

        where:
        test | expected
        50   | 0
        350  | 300
        150  | 100
        49   | -1
        351  | -1
    }

    def "LastIndexOf"(test, expected) {
        given:
        arrayListSpy.addAll(0..100)
        arrayListSpy.addAll(0..100)

        when:
        def actual = arrayListSpy.lastIndexOf(test)

        then:
        actual == expected

        where:
        test | expected
        0    | 101
        100  | 201
        50   | 151
        -1   | -1
        101  | -1
    }
}
