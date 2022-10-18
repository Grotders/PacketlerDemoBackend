package com.oguzcan.paketlerdemo.services.customer;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;

import java.util.List;

public interface CustomerService {

    // CRUD


    DataResult<List<PacketDto>> getPackets();
//    Result buyPacket(Long userId, Long packetId);
//    Result updatePacket(Long userId, Long packetId);
    DataResult<UserDto> login(UserDto userDto);
//    Result register(UserDto userDto);
}
