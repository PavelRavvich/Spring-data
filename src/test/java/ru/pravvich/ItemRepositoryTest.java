package ru.pravvich;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * WARNING!!! WORK ONLY WITH REAL DATABASE @SEE SQL DIR IN ROOT PROJECT.
 */
public class ItemRepositoryTest {

    private final ClassPathXmlApplicationContext context =
            new ClassPathXmlApplicationContext("spring-data-context.xml");

    private final ItemRepository repository = context.getBean(ItemRepository.class);

    private final Item item = new Item("test", "test");

    @Test
    public void whenSaveItemToDatabaseThenItemContainInDB() {

        final Item result = repository.save(item);

        assertThat(repository.findOne(result.getId()), is(result));

        repository.delete(result.getId());
    }

    @Test
    public void whenItemDeleteThenItemNotExist() {

        final Item saved = repository.save(item);

        repository.delete(saved);

        assertFalse(repository.exists(saved.getId()));
    }

    /**
     * Method save(it) == update, if send object with id which contain in table.
     */
    @Test
    public void whenSameItemWhichExistThenItemInTableUpdatedToNewState() {

        final Item beforeUpdate = repository.save(item);

        final String oldName = beforeUpdate.getName();

        beforeUpdate.setName("update");

        final Item afterUpdate = repository.save(beforeUpdate);

        final String newName = afterUpdate.getName();

        assertThat(beforeUpdate.getId(), is(afterUpdate.getId()));
        assertThat(oldName, is("test"));
        assertThat(newName, is("update"));

        repository.delete(beforeUpdate.getId());
    }
}