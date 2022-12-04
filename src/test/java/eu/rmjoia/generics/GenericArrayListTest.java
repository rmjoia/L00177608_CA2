package eu.rmjoia.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericArrayListTest {

    @Test
    void PersonArrayList_CanAdd_DoesNotThrow() {

        var itemToAdd = "Almada";
        var PersonArrayList = new GenericArrayList<String>();

        PersonArrayList.add(itemToAdd);
    }

    @Test
    void IntArrayList_Size_ReturnsCorrectValue() {

        var itemToAdd1 = 1;
        var itemToAdd2 = 2;
        var itemToAdd3 = 3;
        var intArrayList = new GenericArrayList<Integer>();

        intArrayList.add(itemToAdd1);
        intArrayList.add(itemToAdd2);
        intArrayList.add(itemToAdd3);

        Assertions.assertEquals(3, intArrayList.size());
    }

    @Test
    void PersonArrayList_get_ReturnsValueAtIndex() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(itemToAdd);

        Assertions.assertEquals("Maria", personArrayList.get(0).getFirstname());
    }

    @Test
    void PersonArrayList_get_ThrowsOutOfIndexException() {

        var personArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personArrayList.get(10);
        }, "Index out of Bounds");
    }

    @Test
    void PersonArrayList_add_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personArrayList.add(10, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void PersonArrayList_set_ReturnsAddedItem() {
        var name = "Maria";
        var itemToAdd = new Person(14, "Joia", name);
        var personArrayList = new GenericArrayList<Person>();

        var result = personArrayList.set(1, itemToAdd);

        Assertions.assertEquals(name, result.getFirstname());
    }

    @Test
    void PersonArrayList_set_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personArrayList.set(10, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void PersonArrayList_isEmpty_ReturnsTrueWhenEmpty() {

        var personArrayList = new GenericArrayList<Person>();

        Assertions.assertEquals(true, personArrayList.isEmpty());
    }

    @Test
    void PersonArrayList_isEmpty_ReturnsFalseWhenNotEmpty() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(itemToAdd);

        Assertions.assertEquals(false, personArrayList.isEmpty());
    }

    @Test
    void PersonArrayList_removeAtIndex_ReturnsRemovedItem() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(itemToAdd);

        Assertions.assertEquals(1, personArrayList.size());

        var result = personArrayList.remove(0);

        Assertions.assertEquals("Maria", result.getFirstname());
        Assertions.assertEquals(0, personArrayList.size());
    }

    @Test
    void PersonArrayList_removeAtIndex_ThrowsIfOutOfRange() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            personArrayList.remove(1);
        }, "Index out of Bounds");
    }

    @Test
    void PersonArrayList_removeElement_ReturnsTrueWhenItemIsFoundAndRemoved() {

        var item = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(item);

        Assertions.assertEquals(1, personArrayList.size());

        var result = personArrayList.remove(item);

        Assertions.assertEquals(true, result);
        Assertions.assertEquals(0, personArrayList.size());
    }

    @Test
    void PersonArrayList_removeElement_ReturnsFalseWhenItemIsNotFoundAndNotRemoved() {

        var addedItem = new Person(4, "Joia", "Martin");
        var itemToRemove = new Person(19, "Joia", "Miguel");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(addedItem);

        Assertions.assertEquals(1, personArrayList.size());

        var result = personArrayList.remove(itemToRemove);

        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1, personArrayList.size());
    }

    @Test
    void PersonArrayList_contains_ReturnsTrueWhenArrayListContainsElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(addedItem1);
        personArrayList.add(addedItem2);
        personArrayList.add(addedItem3);
        personArrayList.add(addedItem4);

        var result = personArrayList.contains(addedItem1);

        Assertions.assertEquals(true, result);
    }

    @Test
    void PersonArrayList_contains_ReturnsFalseWhenArrayListDoesNotContainElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var notAddedItem = new Person(46, "Nunes", "Ana");
        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(addedItem1);
        personArrayList.add(addedItem2);
        personArrayList.add(addedItem3);
        personArrayList.add(addedItem4);

        var result = personArrayList.contains(notAddedItem);

        Assertions.assertEquals(false, result);
    }

    @Test
    void PersonArrayList_iterator_ReturnsItemsInArrayList() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");

        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(addedItem1);
        personArrayList.add(addedItem2);
        personArrayList.add(addedItem3);
        personArrayList.add(addedItem4);

        for (var item = personArrayList.iterator(); item.hasNext(); ) {
            Assertions.assertEquals(true, !item.next().getSurname().isEmpty());
        }
    }

    @Test
    void PersonArrayList_toString_ReturnsStringWithListOfItems() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");

        var personArrayList = new GenericArrayList<Person>();

        personArrayList.add(addedItem1);
        personArrayList.add(addedItem2);
        personArrayList.add(addedItem3);

        Assertions.assertEquals(
                " -> Person{firstname='" + addedItem1.getFirstname() + "', surname='" + addedItem1.getSurname() + "', age=" + addedItem1.getAge() + "}" +
                " -> Person{firstname='" + addedItem2.getFirstname() + "', surname='" + addedItem2.getSurname() + "', age=" + addedItem2.getAge() + "}" +
                " -> Person{firstname='" + addedItem3.getFirstname() + "', surname='" + addedItem3.getSurname() + "', age=" + addedItem3.getAge() + "}",
                personArrayList.toString());
    }
}
