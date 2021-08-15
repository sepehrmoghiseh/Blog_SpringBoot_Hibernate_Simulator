package com.pazandish.weblog.controller;

import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.service.UsersService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/signup")
    public UsersEntity userSignUp(final @RequestBody UsersEntity user) {
        return usersService.signUp(user);
    }

    @PutMapping("/adminpanel/{userName}/setrole/admin/{id}")
    public void setAdmin(@PathVariable int id, @PathVariable String userName) {
        usersService.setAdmin(id, userName);
    }

    @PutMapping("/adminpanel/{userName}/setrole/sender/{id}")
    public void setSender(@PathVariable int id, @PathVariable String userName) {
        usersService.setSender(id, userName);
    }

    @PutMapping("/adminpanel/{userName}/unrole/sender/{id}")
    public void setUnSender(@PathVariable int id, @PathVariable String userName) {
        usersService.setUnSender(id, userName);
    }

    @PutMapping("/adminpanel/{userName}/block/{id}")
    public void block(@PathVariable int id, @PathVariable String userName) {
        usersService.block(id, userName);

    }

    @PutMapping("/adminpanel/{userName}/unblock/{id}")
    public void unBlock(@PathVariable int id, @PathVariable String userName) {
        usersService.unBlock(id, userName);

    }
}
