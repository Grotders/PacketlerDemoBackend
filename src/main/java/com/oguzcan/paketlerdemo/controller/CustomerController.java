package com.oguzcan.paketlerdemo.controller;


import com.oguzcan.paketlerdemo.core.utilities.results.DataResult;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.services.customer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customers")
@CrossOrigin
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

//    @PostMapping("/buyPacket")
//    public ResponseEntity<Result> buyPacket(Long userId, Long packetId) {
//        return ResponseEntity.ok(this.userService.buyPacket(userId, packetId));
//    }
//
//    @PostMapping("/updatePacket")
//    public ResponseEntity<Result> updatePacket(Long userId, Long packetId) {
//        return ResponseEntity.ok(this.userService.updatePacket(userId, packetId));
//    }

    @GetMapping("/getPackets")
    public ResponseEntity<DataResult> getPackets() {
        return ResponseEntity.ok(this.customerService.getPackets());
    }

    @PostMapping("/login")
    public ResponseEntity<DataResult> login(@RequestBody UserDto userDto) {return ResponseEntity.ok(this.customerService.login(userDto));}

//    @PostMapping("/register")
//    public ResponseEntity<Result> register(@RequestBody UserDto userDto) {return ResponseEntity.ok(this.userService.register(userDto));}
}
