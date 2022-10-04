package com.oguzcan.paketlerdemo.services.admin;

import com.oguzcan.paketlerdemo.core.utilities.results.DataResult;
import com.oguzcan.paketlerdemo.core.utilities.results.Result;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.entities.Packet;

import java.util.List;

public interface AdminService {

    Result addUser(UserDto userDto);
    DataResult<UserDto> getUser(Long userId);
    DataResult<List<UserDto>> getUsers();
    Result updateUser(UserDto userDto);
    Result deleteUser(Long userId);


    Result addPacket(PacketDto packetDto);
    Result deletePacketId(Long packetId);
    DataResult<List<Packet>> getAllPackets();
    DataResult<Packet> getByIdPacket(Long packetId);
    Result updatePacket(Long packetId, PacketDto packetDto);

}
