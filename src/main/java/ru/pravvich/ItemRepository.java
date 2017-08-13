package ru.pravvich;

import org.springframework.data.repository.CrudRepository;

/**
 * Author : Pavel Ravvich.
 * Created : 13.08.17.
 */
public interface ItemRepository extends CrudRepository<Item, Integer> {
}
