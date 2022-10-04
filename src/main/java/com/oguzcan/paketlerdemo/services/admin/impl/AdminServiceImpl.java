package com.oguzcan.paketlerdemo.services.admin.impl;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.entities.Packet;
import com.oguzcan.paketlerdemo.entities.User;
import com.oguzcan.paketlerdemo.repository.PacketRepository;
import com.oguzcan.paketlerdemo.repository.PurchaseHistoryRepository;
import com.oguzcan.paketlerdemo.repository.UserRepository;
import com.oguzcan.paketlerdemo.services.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final   UserRepository userRepository;
    private final PacketRepository packetRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    private final ModelMapper modelMapper;




    // USER ############################################################################################################
    @Override
    public Result addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setCreateAt(new Date());
        userRepository.save(user);
        return new SuccessResult("Kullanıcı basarıyla olusturuldu.");
    }

    @Override
    public DataResult<UserDto> getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            UserDto dto = modelMapper.map(user, UserDto.class);
            return new SuccessDataResult<UserDto>(dto, "Kullanıcı getirildi.");
        }

        return new ErrorDataResult<UserDto>("Kullanıcı bulunamadı.");
    }

    @Override
    public DataResult<List<UserDto>> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<UserDto>>
                (dtos, "kullanıcılar getirildi.");
    }

    @Override
    public Result updateUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getUserId());
        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser = modelMapper.map(userDto, User.class);
            updatedUser.setUpdateAt(new Date());
            userRepository.save(updatedUser);
            return new SuccessResult("Kullanıcı başarıyla güncellendi.");
        }
        return new ErrorResult("Kullanıcı güncellemesi başarısız.");
    }

    @Override
    public Result deleteUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return new SuccessResult("Kullanıcı başarıyla silindi.");
        }
        return new ErrorResult("Kullanıcı silinemedi.");
    }


    // PACKET ##########################################################################################################

    @Override
    public Result addPacket(PacketDto packetDto) {
        Packet packet = this.modelMapper.map(packetDto, Packet.class);
        packet.setCreateAt(new Date());
        packet.setCreateBy("Admin-Oguzcan");
        this.packetRepository.save(packet);
        return new SuccessResult(packet.getTitle() + " adlı paket başarıyla oluşturuldu.");
    }
    @Override
    public Result deletePacketId(Long packetId) {
        Optional<Packet> packet = packetRepository.findById(packetId);
        if (packet.isPresent()) {
            packetRepository.deleteById(packetId);
            return new SuccessResult("Paket silme başarılı.");
        }
        return new ErrorResult("Paket silme başarısız.");
    }

    @Override
    public DataResult<List<Packet>> getAllPackets() {
        return new SuccessDataResult<List<Packet>>
                (this.packetRepository.findAll(), "Paketler listelendi");
    }

    @Override
    public DataResult<Packet> getByIdPacket(Long packetId) {
        Optional<Packet> packet = this.packetRepository.findById(packetId);
        if (packet.isPresent()) {
            return new SuccessDataResult<Packet>(packet.get(), "Paket getirildi.");
        }
        return new ErrorDataResult<Packet>("Paket bulunamadı.");
    }

    @Override
    public Result updatePacket(Long packetId, PacketDto packetDto) {
        Optional<Packet> resultPaket = packetRepository.findById(packetId);
        if (resultPaket.isPresent()) {
            resultPaket.get().setTitle(packetDto.getTitle());
            resultPaket.get().setPrice(packetDto.getPrice());
            resultPaket.get().setData(packetDto.getData());
            resultPaket.get().setMinutes(packetDto.getMinutes());
            resultPaket.get().setSms(packetDto.getSms());
            resultPaket.get().setUpdateAt(new Date());
            resultPaket.get().setUpdateBy("Admin-Oguzcan");
            this.packetRepository.save(resultPaket.get());
            return new SuccessResult("Paket güncelleme başarılı.");
        }
        return new ErrorResult("Paket güncelleme başarısız.");
    }
}
