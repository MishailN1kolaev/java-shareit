package ru.practicum.shareit.item.item_package;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByOwnerIdOrderById(Long ownerId, Pageable page);

    @Query("select it " +
            "from Item as it " +
            "where LOWER(it.name) LIKE LOWER(concat('%', :text, '%')) " +
            "OR LOWER(it.description) LIKE LOWER(concat('%', :text, '%'))")
    List<Item> findItemByUserText(String text);

}
