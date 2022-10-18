package com.oguzcan.paketlerdemo.services.customer.impl;


import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.dto.AdminDto;
import com.oguzcan.paketlerdemo.entities.*;
import com.oguzcan.paketlerdemo.repository.*;
import com.oguzcan.paketlerdemo.services.customer.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;
    private final PacketRepository packetRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;
    private final ModelMapper modelMapper;

    public CustomerServiceImpl(AdminRepository adminRepository, CustomerRepository customerRepository, PacketRepository packetRepository, PurchaseHistoryRepository purchaseHistoryRepository, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.packetRepository = packetRepository;
        this.purchaseHistoryRepository = purchaseHistoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataResult<List<PacketDto>> getPackets() {
        List<Packet> packets = packetRepository.findAll();
        List<PacketDto> packetDtos = packets.stream()
                .map(packet -> modelMapper.map(packet, PacketDto.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<PacketDto>>
                (packetDtos, "Paketler başarıyla getirildi.");
    }

//    @Override
//    public Result buyPacket(Long userId, Long packetId) {
//        Optional<User> user =  userRepository.findById(userId);
//
//        if (user.isPresent()) {
//            PurchaseHistory purchaseHistory = new PurchaseHistory();
//            purchaseHistory.setUserID(userId);
//            LocalDate date = LocalDate.now();
//            purchaseHistory.setPurchaseDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//            purchaseHistory.setPacketId(packetId);
//            purchaseHistoryRepository.save(purchaseHistory);
//        }
//
//        return new SuccessResult("Paket başarıyla satın alındı.");
//    }
//
//    @Override
//    public Result updatePacket(Long userId, Long packetId) {
//        Optional<User> user =  this.userRepository.findById(userId);
//        Optional<Packet> packet = this.packetRepository.findById(packetId);
//        if (user.isPresent() && packet.isPresent()) {
//            RemainingData remainingData = new RemainingData();
//            remainingData.setRemainingData(packet.get().getData());
//            remainingData.setRemainingSMS(packet.get().getSms());
//            remainingData.setRemainingMinutes(packet.get().getMinutes());
//            remainingData.setUserId(userId);
//            remainingData.setExpireDate(LocalDate.now().plusMonths(1));
//            this.remainingDataRepository.save(remainingData);
//        }
//        return new SuccessResult("paket güncellendi");
//    }
//

    private boolean isAdmin(String email) {
        return email.contains("@oguzcell.com");
    }

    @Override
    public DataResult<UserDto> login(UserDto userDto) {
        String email = userDto.getEmail();
        Optional<? extends User> user;

        if (isAdmin(email)) {
            user = this.adminRepository
                    .findByEmailAndPassword(email, userDto.getPassword());
        } else {
            user = this.customerRepository
                    .findByEmailAndPassword(email, userDto.getPassword());
        }

        if (user.isPresent()) {
            Class<? extends UserDto> userDtoClass = isAdmin(email) ? AdminDto.class : UserDto.class;
            UserDto userDto2 = modelMapper.map(user, userDtoClass);
            return new SuccessDataResult<>(userDto2, "Giriş başarılı");
        }
        return new ErrorDataResult("Hatalı şifre veya mail");

//        Optional<User> user = this.userRepository
//                .findByEmailAndPassword(accountDto.getEmail(), accountDto.getPassword());
//        if (user.isPresent()) {
//            UserDto dto = this.modelMapper.map(user, UserDto.class);
//            return new SuccessDataResult<UserDto>(dto, "Giriş başarılı");
//        }
//        return new ErrorDataResult<UserDto>("Hatalı şifre veya mail");
    }
//
//    @Override
//    public Result register(UserDto userDto) {
//        User user = this.modelMapper.map(userDto, User.class);
//        this.userRepository.save(user);
//        return new SuccessResult("Kayıt başarılı.");
//    }
}
