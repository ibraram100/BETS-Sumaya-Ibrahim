package net.SumayaIbrahim.bets.service.WaitingListImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.dto.WaitingListDTO;
import net.SumayaIbrahim.bets.entity.User;
import net.SumayaIbrahim.bets.entity.WaitingList;
import net.SumayaIbrahim.bets.repository.WaitingListRepository;
import net.SumayaIbrahim.bets.service.UserService;
import net.SumayaIbrahim.bets.service.WaitingListService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WaitingListServiceImpl implements WaitingListService {
    private ModelMapper modelMapper;
    private WaitingListRepository waitingListRepository;
    private UserService userService;
    @Override
    public WaitingListDTO createWaitingList(WaitingListDTO waitingListDTO) {
        WaitingList waitingList = modelMapper.map(waitingListDTO,WaitingList.class);
        WaitingList savedWaitingList = waitingListRepository.save(waitingList);
        WaitingListDTO savedWaitingListDTO = modelMapper.map(savedWaitingList, WaitingListDTO.class);
        savedWaitingListDTO.setId(savedWaitingList.getId());
        return savedWaitingListDTO;
    }

    @Override
    public WaitingList findWaitingListById(long id) {
        return null;
    }

    @Override
    public void addUserToWaitingList(long userId, long waitingListId) {
        
    }

    @Override
    public WaitingList findWaitingListByEventId(long eventId) {
        return waitingListRepository.findByEventEventID(eventId);
    }

    @Override
    public void updateWaitingList(WaitingListDTO waitingListDTO) {
        List<Long> userIds = waitingListDTO.getUserIds();
        List<Optional<User>> users = userIds.stream().map(userService::findUserById).collect(Collectors.toList());
        WaitingList waitingList = modelMapper.map(waitingListDTO,WaitingList.class);
        // This line is also important as ModelMapper can't just convert a list of long userIds to an actual list of users objects
        List<User> users2 = userIds.stream()
                .map(userId -> userService.findUserById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId)))
                .collect(Collectors.toList());
        waitingList.setUsers(users2);

        waitingListRepository.save(waitingList);
    }
}
