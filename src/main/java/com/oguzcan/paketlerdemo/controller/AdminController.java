package com.oguzcan.paketlerdemo.controller;

import com.oguzcan.paketlerdemo.core.utilities.results.*;
import com.oguzcan.paketlerdemo.dto.AdminDto;
import com.oguzcan.paketlerdemo.dto.CustomerDto;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.services.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admins")
@CrossOrigin    // api yi dışarı açmamızı sağlıyor. Yoksa frontend alamıyor.
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // ADMIN ############################################################################################################

    @PostMapping("/addAdmin")
    public ResponseEntity<Result> addAdmin(@RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(this.adminService.addAdmin(adminDto));
    }

    // CUSTOMER ############################################################################################################
    @PostMapping("/addCustomer")
    public ResponseEntity<Result> addCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(this.adminService.addCustomer(customerDto));
    }

    @PostMapping("/deleteCustomerByUserId")
    public ResponseEntity<Result> deleteByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(this.adminService.deleteCustomer(userId));
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<DataResult> getAllCustomers() {
        return ResponseEntity.ok(this.adminService.getCustomers());
    }

    @GetMapping("/getByIdCustomer")
    public ResponseEntity<DataResult> getByIdCustomer(@RequestParam Long userId) {
        return ResponseEntity.ok(this.adminService.getCustomer(userId));
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity<Result> updateCustomer(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.ok(this.adminService.updateCustomer(customerDto));
    }

    // PACKET ##########################################################################################################
    @PostMapping("/addPacket")
    public ResponseEntity<Result> addPacket(@RequestBody PacketDto packetDto, @RequestParam String adminFullName) {
        return ResponseEntity.ok(this.adminService.addPacket(packetDto, adminFullName));
    }

    @PostMapping("/deleteByPacketId")
    public ResponseEntity<Result> deleteByPacketId(@RequestParam Long packetId) {
        return ResponseEntity.ok(this.adminService.deletePacketId(packetId));
    }

    @GetMapping("/getAllPackets")
    public ResponseEntity<DataResult> getAllPackets(){
        return ResponseEntity.ok(this.adminService.getAllPackets());
    }


    @GetMapping("/getByIdPacket")
    public ResponseEntity<DataResult> getByIdPacket(@RequestParam Long packetId) {
        return ResponseEntity.ok(this.adminService.getByIdPacket(packetId));
    }

    @PostMapping("/updatePacket")
    public ResponseEntity<Result> updatePacket(@RequestParam Long id,@RequestBody PacketDto packetDto, @RequestParam String adminFullName) {
        return ResponseEntity.ok(this.adminService.updatePacket(id, packetDto, adminFullName));
    }
}
