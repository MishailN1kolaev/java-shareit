package ru.practicum.shareit.item.item_package;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemForBookingDto {
    private Long id;
    private String name;
}
