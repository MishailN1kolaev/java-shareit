package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exeption.NoDataException;
import ru.practicum.shareit.item.item_package.ItemRepository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;
import ru.practicum.shareit.util.PaginationUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemRequestServiceImpl implements ItemRequestService {
    private final ItemRequestRepository itemRequestRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public ItemRequestDtoResponse createItemRequest(ItemRequestDto itemRequestDto, Long requesterId) {
        User requester = userRepository.findById(requesterId).orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        itemRequestDto.setCreated(LocalDateTime.now());
        return ItemRequestMapper.mapToItemRequestDtoResponse(itemRequestRepository.save(ItemRequestMapper.mapToItemRequest(itemRequestDto, requester)));
    }

    @Override
    public List<ItemRequestDtoResponse> getAllItemRequestByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + userId + " не найден"));
        List<ItemRequestDtoResponse> allItemRequests = new ArrayList<>(itemRequestRepository.findByRequesterId(userId).stream()
                .map(ItemRequestMapper::mapToItemRequestDtoResponse)
                .collect(Collectors.toList()));
        return allItemRequests;
    }

    @Override
    public List<ItemRequestDtoResponse> getItemRequestByOtherUser(Long userId, Integer from, Integer size) {

        Pageable page = PaginationUtils.createPageRequest(from, size);
        return itemRequestRepository.findAll(page)
                .filter(itemRequest -> itemRequest.getRequester().getId() != userId)
                .map(ItemRequestMapper::mapToItemRequestDtoResponse)
                .toList();
    }

    @Override
    public ItemRequestDtoResponse getItemRequestById(Long requestId, Long userId) {
        User requester = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Нет такого пользователя"));
        return ItemRequestMapper.mapToItemRequestDtoResponse(itemRequestRepository.findById(requestId).orElseThrow(() -> new NoDataException("Нет такого запроса")));

    }

}
