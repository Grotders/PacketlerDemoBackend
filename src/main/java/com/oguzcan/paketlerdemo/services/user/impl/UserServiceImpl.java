package com.oguzcan.paketlerdemo.services.user.impl;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.AccountDto;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.entities.Packet;
import com.oguzcan.paketlerdemo.entities.PurchaseHistory;
import com.oguzcan.paketlerdemo.entities.RemainingData;
import com.oguzcan.paketlerdemo.entities.User;
import com.oguzcan.paketlerdemo.repository.PacketRepository;
import com.oguzcan.paketlerdemo.repository.PurchaseHistoryRepository;
import com.oguzcan.paketlerdemo.repository.RemainingDataRepository;
import com.oguzcan.paketlerdemo.repository.UserRepository;
import com.oguzcan.paketlerdemo.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PacketRepository packetRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final RemainingDataRepository remainingDataRepository;


    @Override
    public DataResult<List<PacketDto>> getPackets() {
        List<Packet> packets = packetRepository.findAll();
        List<PacketDto> dtos = packets.stream()
                .map(packet -> modelMapper.map(packet, PacketDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<PacketDto>>
                (dtos, "Paketler başarıyla getirildi.");
    }

    @Override
    public Result buyPacket(Long userId, Long packetId) {
        Optional<User> user =  userRepository.findById(userId);

        if (user.isPresent()) {
            PurchaseHistory purchaseHistory = new PurchaseHistory();
            purchaseHistory.setUserID(userId);
            LocalDate date = LocalDate.now();
            purchaseHistory.setPurchaseDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            purchaseHistory.setPacketId(packetId);
            purchaseHistoryRepository.save(purchaseHistory);
        }

        return new SuccessResult("Paket başarıyla satın alındı.");
    }

    @Override
    public Result updatePacket(Long userId, Long packetId) {
        Optional<User> user =  userRepository.findById(userId);
        Optional<Packet> packet = packetRepository.findById(packetId);
        if (user.isPresent() && packet.isPresent()) {
            RemainingData remainingData = new RemainingData();
            remainingData.setRemainingData(packet.get().getData());
            remainingData.setRemainingSMS(packet.get().getSms());
            remainingData.setRemainingMinutes(packet.get().getMinutes());
            remainingData.setPacketId(packetId);
            remainingData.setUserId(userId);
            remainingData.setExpireDate(LocalDate.now().plusMonths(1));
            remainingDataRepository.save(remainingData);
        }
        return new SuccessResult("paket güncellendi");
    }

    @Override
    public DataResult<UserDto> login(AccountDto accountDto) {
        Optional<User> user = userRepository.findByEmailAndPassword(accountDto.getEmail(), accountDto.getPassword());
        if (user.isPresent()) {
            UserDto dto = modelMapper.map(user, UserDto.class);
            return new SuccessDataResult<UserDto>(dto, "Giriş başarılı");
        }
        return new ErrorDataResult<UserDto>("Hatalı şifre veya mail");
    }

    @Override
    public Result register(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        return new SuccessResult("Kayıt başarılı.");
    }
}
