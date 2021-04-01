package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ItemRepository;
import web.dto.ItemRequestDto;
import web.dto.ItemResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

//    public Long save(ItemRequestDto requestDto) {
//
//        Item item = requestDto.toEntity();
//        itemRepository.save(item);
//        return item.getId();
//    }

    public List<ItemResponseDto> findItems() {
        return itemRepository.findAll().stream().map(ItemResponseDto::new)
                .collect(Collectors.toList());
    }

}
