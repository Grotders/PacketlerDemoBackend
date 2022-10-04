package com.oguzcan.paketlerdemo.api;

import com.oguzcan.paketlerdemo.core.utilities.results.DataResult;
import com.oguzcan.paketlerdemo.core.utilities.results.Result;
import com.oguzcan.paketlerdemo.dto.PacketDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.services.admin.AdminService;
import com.oguzcan.paketlerdemo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admins")
@CrossOrigin
public class AdminsController {

    private AdminService adminService;
    private UserService userService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // USER ############################################################################################################
    @PostMapping("/addUsers")
    public ResponseEntity<Result> add(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.adminService.addUser(userDto));
    }

    @PostMapping("/deleteByUserId")
    public ResponseEntity<Result> deleteByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(this.adminService.deleteUser(userId));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<DataResult> getAllUsers() {
        return ResponseEntity.ok(this.adminService.getUsers());
    }

    @GetMapping("/getByIdUser")
    public ResponseEntity<DataResult> getByIdUser(@RequestParam Long userId) {
        return ResponseEntity.ok(this.adminService.getUser(userId));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Result> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.adminService.updateUser(userDto));
    }

    // PACKET ##########################################################################################################
    @PostMapping("/addPacket")
    public ResponseEntity<Result> addPacket(@RequestBody PacketDto packetDto) {
        return ResponseEntity.ok(this.adminService.addPacket(packetDto));
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
    public ResponseEntity<Result> updatePacket(@RequestParam Long id,@RequestBody PacketDto packetDto) {
        return ResponseEntity.ok(this.adminService.updatePacket(id, packetDto));
    }
}
