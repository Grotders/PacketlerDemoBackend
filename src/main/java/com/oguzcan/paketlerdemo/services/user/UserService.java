package com.oguzcan.paketlerdemo.services.user;

import com.oguzcan.paketlerdemo.core.utilities.results.DataResult;
import com.oguzcan.paketlerdemo.core.utilities.results.Result;
import com.oguzcan.paketlerdemo.dto.AccountDto;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;

import java.util.List;

public interface UserService {

    // CRUD


    DataResult<List<PacketDto>> getPackets();
    Result buyPacket(Long userId, Long packetId);
    Result updatePacket(Long userId, Long packetId);
    DataResult<UserDto> login(AccountDto accountDto);
    Result register(UserDto userDto);
}
