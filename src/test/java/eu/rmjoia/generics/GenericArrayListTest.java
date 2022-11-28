package eu.rmjoia.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericArrayListTest {

    @Test
    void StringArrayList_CanAdd_DoesNotThrow() {

        var itemToAdd = "Almada";
        var stringArrayList = new GenericArrayList<String>();

        stringArrayList.add(itemToAdd);
    }

    @Test
    void StringArrayList_Size_ReturnsCorrectValue() {

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
    void StringArrayList_get_ReturnsValueAtIndex() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(itemToAdd);

        Assertions.assertEquals("Maria", dummyArrayList.get(0).getFirstname());
    }

    @Test
    void StringArrayList_get_ThrowsOutOfIndexException() {

        var dummyArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyArrayList.get(10);
        }, "Index out of Bounds");
    }

    @Test
    void StringArrayList_add_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyArrayList.add(10, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void StringArrayList_set_ReturnsAddedItem() {
        var name = "Maria";
        var itemToAdd = new Person(14, "Joia", name);
        var dummyArrayList = new GenericArrayList<Person>();

        var result = dummyArrayList.set(1, itemToAdd);

        Assertions.assertEquals(name, result.getFirstname());
    }

    @Test
    void StringArrayList_set_ThrowsOutOfIndexException() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyArrayList.add(10, itemToAdd);
        }, "Index out of Bounds");
    }

    @Test
    void StringArrayList_isEmpty_ReturnsTrueWhenEmpty() {

        var dummyArrayList = new GenericArrayList<Person>();

        Assertions.assertEquals(true, dummyArrayList.isEmpty());
    }

    @Test
    void StringArrayList_isEmpty_ReturnsFalseWhenNotEmpty() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(itemToAdd);

        Assertions.assertEquals(false, dummyArrayList.isEmpty());
    }

    @Test
    void StringArrayList_removeAtIndex_ReturnsRemovedItem() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(itemToAdd);

        Assertions.assertEquals(1, dummyArrayList.size());

        var result = dummyArrayList.remove(0);

        Assertions.assertEquals("Maria", result.getFirstname());
        Assertions.assertEquals(0, dummyArrayList.size());
    }

    @Test
    void StringArrayList_removeAtIndex_ThrowsIfOutOfRange() {

        var itemToAdd = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            dummyArrayList.remove(1);
        }, "Index out of Bounds");
    }

    @Test
    void StringArrayList_removeElement_ReturnsTrueWhenItemIsFoundAndRemoved() {

        var item = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(item);

        Assertions.assertEquals(1, dummyArrayList.size());

        var result = dummyArrayList.remove(item);

        Assertions.assertEquals(true, result);
        Assertions.assertEquals(0, dummyArrayList.size());
    }

    @Test
    void StringArrayList_removeElement_ReturnsFalseWhenItemIsNotFoundAndNotRemoved() {

        var addedItem = new Person(4, "Joia", "Martin");
        var itemToRemove = new Person(19, "Joia", "Miguel");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(addedItem);

        Assertions.assertEquals(1, dummyArrayList.size());

        var result = dummyArrayList.remove(itemToRemove);

        Assertions.assertEquals(false, result);
        Assertions.assertEquals(1, dummyArrayList.size());
    }

    @Test
    void StringArrayList_contains_ReturnsTrueWhenArrayListContainsElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(addedItem1);
        dummyArrayList.add(addedItem2);
        dummyArrayList.add(addedItem3);
        dummyArrayList.add(addedItem4);

        var result = dummyArrayList.contains(addedItem1);

        Assertions.assertEquals(true, result);
    }

    @Test
    void StringArrayList_contains_ReturnsFalseWhenArrayListDoesNotContainElement() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");
        var notAddedItem = new Person(46, "Nunes", "Ana");
        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(addedItem1);
        dummyArrayList.add(addedItem2);
        dummyArrayList.add(addedItem3);
        dummyArrayList.add(addedItem4);

        var result = dummyArrayList.contains(notAddedItem);

        Assertions.assertEquals(false, result);
    }

    @Test
    void StringArrayList_iterator_ReturnsItemsInArrayList() {

        var addedItem1 = new Person(4, "Joia", "Martin");
        var addedItem2 = new Person(19, "Joia", "Miguel");
        var addedItem3 = new Person(42, "Joia", "Ricardo");
        var addedItem4 = new Person(14, "Joia", "Maria");

        var dummyArrayList = new GenericArrayList<Person>();

        dummyArrayList.add(addedItem1);
        dummyArrayList.add(addedItem2);
        dummyArrayList.add(addedItem3);
        dummyArrayList.add(addedItem4);

        for (var item = dummyArrayList.iterator(); item.hasNext(); ) {
            Assertions.assertEquals(true, !item.next().getSurname().isEmpty());
        }
    }
}
