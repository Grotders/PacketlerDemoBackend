package com.oguzcan.paketlerdemo.api;


import com.oguzcan.paketlerdemo.core.utilities.results.DataResult;
import com.oguzcan.paketlerdemo.core.utilities.results.Result;
import com.oguzcan.paketlerdemo.dto.AccountDto;
import com.oguzcan.paketlerdemo.dto.UserDto;
import com.oguzcan.paketlerdemo.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/users")
@CrossOrigin
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/buyPacket")
    public ResponseEntity<Result> buyPacket(Long userId, Long packetId) {
        return ResponseEntity.ok(this.userService.buyPacket(userId, packetId));
    }

    @PostMapping("/updatePacket")
    public ResponseEntity<Result> updatePacket(Long userId, Long packetId) {
        return ResponseEntity.ok(this.userService.updatePacket(userId, packetId));
    }
    
    @GetMapping("/getPackets")
    public ResponseEntity<DataResult> getPacket() {
        return ResponseEntity.ok(this.userService.getPackets());
    }

    @PostMapping("/login")
    public ResponseEntity<DataResult> login(@RequestBody AccountDto accountDto) {return ResponseEntity.ok(this.userService.login(accountDto));}

    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody UserDto userDto) {return ResponseEntity.ok(this.userService.register(userDto));}
}
