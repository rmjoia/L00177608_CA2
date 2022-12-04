package eu.rmjoia.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericLinkedListTest {

    @Test
    void StringLinkedList_CanAdd_DoesNotThrow() {

        var itemToAdd1 = "Almada";
        var itemToAdd2 = "Lisboa";

        var stringLinkedList = new GenericLinkedList<String>();

        stringLinkedList.add(itemToAdd1);
        stringLinkedList.add(itemToAdd2);
    }

    @Test
    void PersonLinkedList_addAtInvalidPosition_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericLinkedList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personArrayList.add(2, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void PersonLinkedList_addAtValidPosition_AddsSuccessfully() {

        var itemToAdd1 = new Person(4, "Joia", "Martin");
        var itemToAdd2 = new Person(14, "Joia", "Maria");

        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(itemToAdd1);

        Assertions.assertDoesNotThrow(() -> {
            personLinkedList.add(1, itemToAdd2);
        }, "Index out of Bounds");

        Assertions.assertEquals(2, personLinkedList.size());
    }

    @Test
    void IntArrayList_Size_ReturnsCorrectValue() {

        var itemToAdd1 = 1;
        var itemToAdd2 = 2;
        var itemToAdd3 = 3;
        var intLinkedList = new GenericLinkedList<Integer>();

        intLinkedList.add(itemToAdd1);
        intLinkedList.add(itemToAdd2);
        intLinkedList.add(itemToAdd3);

        Assertions.assertEquals(3, intLinkedList.size());
    }

    @Test
    void PersonLinkedList_set_ReturnsAddedItem() {
        var name = "Maria";
        var itemToAdd = new Person(14, "Joia", name);
        var personLinkedList = new GenericLinkedList<Person>();

        var result = personLinkedList.set(1, itemToAdd);

        Assertions.assertEquals(name, result.getFirstname());
    }

    @Test
    void PersonLinkedList_set_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personLinkedList = new GenericLinkedList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personLinkedList.set(3, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void PersonLinkedList_get_ReturnsValueAtIndex() {

        var itemToAdd1 = new Person(4, "Joia", "Martin");
        var itemToAdd2 = new Person(14, "Joia", "Maria");
        var itemToAdd3 = new Person(19, "Joia", "Miguel");
        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(itemToAdd1);
        personLinkedList.add(itemToAdd2);
        personLinkedList.add(itemToAdd3);

        Assertions.assertEquals("Miguel", personLinkedList.get(2).getFirstname());
    }

    @Test
    void PersonLinkedList_get_ThrowsOutOfIndexException() {

        var personLinkedList = new GenericLinkedList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personLinkedList.get(10);
        }, "Index out of Bounds");
    }

    @Test
    void PersonLinkedList_isEmpty_ReturnsTrueWhenEmpty() {

        var personLinkedList = new GenericLinkedList<Person>();

        Assertions.assertEquals(true, personLinkedList.isEmpty());
    }


    @Test
    void PersonLinkedList_isEmpty_ReturnsFalseWhenNotEmpty() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(itemToAdd);

        Assertions.assertEquals(false, personLinkedList.isEmpty());
    }


    @Test
    void PersonLinkedList_removeAtIndex_ReturnsRemovedItem() {

        var itemToAdd1 = new Person(4, "Joia", "Martin");
        var itemToAdd2 = new Person(14, "Joia", "Maria");
        var itemToAdd3 = new Person(19, "Joia", "Miguel");

        var peopleLinkedList = new GenericLinkedList<Person>();

        peopleLinkedList.add(itemToAdd1);
        peopleLinkedList.add(itemToAdd2);
        peopleLinkedList.add(itemToAdd3);

        Assertions.assertEquals(3, peopleLinkedList.size());

        var result = peopleLinkedList.remove(1);

        Assertions.assertEquals("Maria", result.getFirstname());
        Assertions.assertEquals(2, peopleLinkedList.size());
    }

    @Test
    void PersonLinkedList_removeAtIndex_ThrowsIfOutOfRange() {

        var peopleLinkedList = new GenericLinkedList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            peopleLinkedList.remove(3);
        }, "Index out of Bounds");
    }

    @Test
    void PersonLinkedList_removeElement_ReturnsTrueWhenItemIsFoundAndRemoved() {

        var item = new Person(14, "Joia", "Maria");
        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(item);

        Assertions.assertEquals(1, personLinkedList.size());

        var result = personLinkedList.remove(item);

        Assertions.assertEquals(true, result);
        Assertions.assertEquals(0, personLinkedList.size());
    }


    @Test
    void PersonLinkedList_removeElement_ReturnsFalseWhenItemIsNotFoundAndNotRemoved() {

        var addedItem = new Person(4, "Joia", "Martin");
        var itemToRemove = new Person(19, "Joia", "Miguel");
        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(addedItem);

        Assertions.assertEquals(1, personLinkedList.size());

        var result = personLinkedList.remove(itemToRemove);

        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1, personLinkedList.size());
    }

    @Test
    void PersonLinkedList_contains_ReturnsTrueWhenArrayListContainsElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(addedItem1);
        personLinkedList.add(addedItem2);
        personLinkedList.add(addedItem3);
        personLinkedList.add(addedItem4);

        var result = personLinkedList.contains(addedItem1);

        Assertions.assertEquals(true, result);
    }

    @Test
    void PersonLinkedList_contains_ReturnsFalseWhenArrayListDoesNotContainElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var notAddedItem = new Person(46, "Nunes", "Ana");

        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(addedItem1);
        personLinkedList.add(addedItem2);
        personLinkedList.add(addedItem3);
        personLinkedList.add(addedItem4);

        var result = personLinkedList.contains(notAddedItem);

        Assertions.assertEquals(false, result);
    }

    @Test
    void PersonLinkedList_iterator_ReturnsItemsInArrayList() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");

        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(addedItem1);
        personLinkedList.add(addedItem2);
        personLinkedList.add(addedItem3);
        personLinkedList.add(addedItem4);

        for (var item = personLinkedList.iterator(); item.hasNext(); ) {
            Assertions.assertEquals(true, !((Person)item.next()).getFirstname().isEmpty());
        }
    }


    @Test
    void PersonLinkedList_toString_ReturnsStringWithListOfItems() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");

        var personLinkedList = new GenericLinkedList<Person>();

        personLinkedList.add(addedItem1);
        personLinkedList.add(addedItem2);
        personLinkedList.add(addedItem3);

        Assertions.assertEquals(
                " -> Person{firstname='" + addedItem1.getFirstname() + "', surname='" + addedItem1.getSurname() + "', age=" + addedItem1.getAge() + "}" +
                " -> Person{firstname='" + addedItem2.getFirstname() + "', surname='" + addedItem2.getSurname() + "', age=" + addedItem2.getAge() + "}" +
                " -> Person{firstname='" + addedItem3.getFirstname() + "', surname='" + addedItem3.getSurname() + "', age=" + addedItem3.getAge() + "}",
                personLinkedList.toString());
    }
}
