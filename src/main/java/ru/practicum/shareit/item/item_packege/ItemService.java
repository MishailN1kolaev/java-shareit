package ru.practicum.shareit.item.item_packege;

import ru.practicum.shareit.item.comment.CommentDto;
import ru.practicum.shareit.item.item_packege.ItemDto;
import ru.practicum.shareit.item.item_packege.ItemResponseDto;

import java.util.List;

interface ItemService {

    ItemDto createItem(ItemDto newItem, Long ownerId);

    ItemResponseDto updateItem(Long itemId, ItemDto newItem, Long ownerId);

    List<ItemResponseDto> getAllItemsWithBooking(Long userId);

    ItemResponseDto getItemByIdResponse(Long itemId, Long userId);

    List<ItemDto> getAllItemByOwner(Long ownerId);

    List<ItemDto> searchItem(String request);

    CommentDto createComment(Long itemId, CommentDto commentDto, Long userId);
}

