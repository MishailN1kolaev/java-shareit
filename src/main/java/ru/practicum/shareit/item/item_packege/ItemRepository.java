package ru.practicum.shareit.item.item_packege;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.shareit.item.item_packege.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByOwnerIdOrderById(Long ownerId);

    @Query("select it " +
            "from Item as it " +
            "where LOWER(it.name) LIKE LOWER((concat('%', ?1, '%'))) " +
            "OR LOWER(it.description) LIKE LOWER((concat('%', ?1, '%')))")
    List<Item> findItemByUserText(String request);


}
