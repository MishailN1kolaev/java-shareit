package ru.practicum.shareit.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PaginationUtils {
    /**
     * Создает объект Pageable на основе параметров from и size.
     *
     * @param from начальный индекс
     * @param size количество элементов на странице
     * @return объект Pageable
     */
    public static Pageable createPageRequest(int from, int size) {
        int page = from > 0 ? from / size : 0;
        return PageRequest.of(page, size);
    }
}