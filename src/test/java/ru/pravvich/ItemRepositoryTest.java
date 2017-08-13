package ru.pravvich;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Ignore
public class ItemRepositoryTest {
    /**
     * WARNING!!! WORK ONLY WITH DATABASE @SEE SQL DIR IN ROOT PROJECT.
     */
    @Test
    public void whenSaveItemToDatabaseThenItemContainInDB() {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-data-context.xml");

        final ItemRepository repository = context.getBean(ItemRepository.class);

        final Item item = new Item();
        item.setName("test");
        item.setDescription("test");

        final Item result = repository.save(item);

        assertThat(repository.findOne(result.getId()), is(result));
    }

}