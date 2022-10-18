package com.oguzcan.paketlerdemo.services.admin;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.*;
import com.oguzcan.paketlerdemo.entities.Packet;

import java.util.List;
import java.util.Set;

public interface AdminService {

    Result addAdmin(AdminDto adminDto);


    Result addCustomer(CustomerDto customerDto);
    DataResult<CustomerDto> getCustomer(Long userId);
    DataResult<List<CustomerDto>> getCustomers();
    Result updateCustomer(CustomerDto customerDto);
    Result deleteCustomer(Long userId);


    Result addPacket(PacketDto packetDto, String adminFullName);
    Result deletePacketId(Long packetId);
    DataResult<List<Packet>> getAllPackets();
    DataResult<Packet> getByIdPacket(Long packetId);
    Result updatePacket(Long packetId, PacketDto packetDto, String adminFullName);

}
