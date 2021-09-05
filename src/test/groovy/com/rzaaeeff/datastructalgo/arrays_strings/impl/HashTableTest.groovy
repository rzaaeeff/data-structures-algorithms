package com.rzaaeeff.datastructalgo.arrays_strings.impl

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HashTableTest extends Specification {
    class Person {
        private String name
        private String surname

        Person(String name, String surname) {
            this.name = name
            this.surname = surname
        }
    }

    HashTable<String, Person> hashTable

    @Shared
    def persons = [new Person("a1", "b1"),
                   new Person("a2", "b2"),
                   new Person("a3", "b3")]

    void setup() {
        hashTable = new HashTable<>()
    }

    @Unroll
    def "Size"(additions, removals, expected) {
        given:
        def person = persons[0]

        for (int i = 0; i < additions; i++) {
            hashTable.put(person.name, person)
        }

        for (int i = 0; i < removals; i++) {
            hashTable.remove(person.name)
        }

        when:
        def actual = hashTable.size()

        then:
        actual == expected

        where:
        additions | removals | expected
        0         | 0        | 0
        3         | 3        | 0
        5         | 2        | 3
    }

    @Unroll
    def "IsEmpty"(additions, removals, expected) {
        given:
        def person = persons[0]

        for (int i = 0; i < additions; i++) {
            hashTable.put(person.name, person)
        }

        for (int i = 0; i < removals; i++) {
            hashTable.remove(person.name)
        }

        when:
        def actual = hashTable.isEmpty()

        then:
        actual == expected

        where:
        additions | removals | expected
        0         | 0        | true
        3         | 3        | true
        5         | 2        | false
    }

    @Unroll
    @SuppressWarnings("GroovyAssignabilityCheck")
    def "ContainsKey"(keys, test, expected) {
        given:
        keys.forEach {
            hashTable.put(it, new Person(it, "b"))
        }

        when:
        def actual = hashTable.containsKey(test)

        then:
        actual == expected

        where:
        keys            | test | expected
        ["a", "b", "c"] | "a"  | true
        ["a", "b", "c"] | "b"  | true
        ["a", "b", "c"] | "c"  | true
        ["a", "b", "c"] | "d"  | false
        ["a", "b", "c"] | "e"  | false
    }

    @Unroll
    def "ContainsValue"(test, expected) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        def actual = hashTable.containsValue(test)

        then:
        actual == expected

        where:
        test                   | expected
        persons[0]             | true
        persons[1]             | true
        persons[2]             | true
        new Person("a4", "b4") | false
        new Person("a5", "b5") | false
    }

    @Unroll
    def "Get"(test, expected) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        def actual = hashTable.get(test)

        then:
        actual == expected

        where:
        test            | expected
        persons[0].name | persons[0]
        persons[1].name | persons[1]
        persons[2].name | persons[2]
    }

    @Unroll
    def "Get NullPointerException"(test) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        hashTable.get(test)

        then:
        def exc = thrown(NullPointerException)
        exc.message == "Entry with key $test could not be found"

        where:
        test << ["amqp", "www", "http"]
    }

    @Unroll
    def "Put"(test) {
        when:
        def actual = hashTable.put(test.name, test)

        then:
        actual == test
        hashTable.size() == 1
        hashTable.get(test.name) == test

        where:
        test << [persons[0], persons[1], persons[2]]
    }

    @Unroll
    def "Remove"(test) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        def actual = hashTable.remove(test.name)

        then:
        actual == test
        hashTable.size() == persons.size() - 1

        where:
        test << [persons[0], persons[1], persons[2]]
    }

    @Unroll
    def "Remove NullPointerException"(test) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        hashTable.remove(test)

        then:
        def exc = thrown(NullPointerException)
        exc.message == "Entry with a key $test could not be found"

        where:
        test << ["www", "http", "smtp"]
    }

    @Unroll
    def "PutAll"(test) {
        given:
        def anotherHashTable = new HashTable<String, Person>()

        test.forEach {
            anotherHashTable.put(it.name, it)
        }

        when:
        hashTable.putAll(anotherHashTable)

        then:
        hashTable.size() == test.size()
        test.each {
            hashTable.get(it.name) == it
        }

        where:
        test << [[persons[0]], [persons[0], persons[1]], [persons[0], persons[1], persons[2]]]
    }

    @Unroll
    def "Clear"(test) {
        given:
        test.forEach {
            hashTable.put(it.name, it)
        }

        when:
        hashTable.clear()

        then:
        hashTable.size() == 0

        where:
        test << [[persons[0]], [persons[0], persons[1]], [persons[0], persons[1], persons[2]]]
    }

    @Unroll
    def "KeySet"(test) {
        given:
        test.forEach {
            hashTable.put(it.name, it)
        }

        def expected = test*.name as Set

        when:
        def actual = hashTable.keySet()

        then:
        actual == expected

        where:
        test << [[persons[0]], [persons[0], persons[1]], [persons[0], persons[1], persons[2]]]
    }

    @Unroll
    def "Values"(test) {
        given:
        test.forEach {
            hashTable.put(it.name, it)
        }

        def expected = test as Set

        when:
        def actual = hashTable.values()

        then:
        actual == expected

        where:
        test << [[persons[0]], [persons[0], persons[1]], [persons[0], persons[1], persons[2]]]
    }

    @Unroll
    def "EntrySet"(test) {
        given:
        test.forEach {
            hashTable.put(it.name, it)
        }

        when:
        def actual = hashTable.entrySet()

        then:
        actual.size() == test.size()

        where:
        test << [[persons[0]], [persons[0], persons[1]], [persons[0], persons[1], persons[2]]]
    }

    @Unroll
    def "GetOrDefault"(test, defaultVal, expected) {
        given:
        persons.forEach {
            hashTable.put(it.name, it)
        }

        when:
        def actual = hashTable.getOrDefault(test, defaultVal)

        then:
        actual == expected

        where:
        test            | defaultVal   | expected
        persons[0].name | "defaultVal" | persons[0]
        persons[1].name | "defaultVal" | persons[1]
        persons[2].name | "defaultVal" | persons[2]
        "ftp"           | "defaultVal" | "defaultVal"
        "ssl"           | "defaultVal" | "defaultVal"
        "ldap"          | "defaultVal" | "defaultVal"
    }
}
