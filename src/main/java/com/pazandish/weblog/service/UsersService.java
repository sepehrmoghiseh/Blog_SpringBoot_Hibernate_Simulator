package com.pazandish.weblog.service;

import com.pazandish.weblog.domain.UsersEntity;
import com.pazandish.weblog.domain.enumeration.Role;
import com.pazandish.weblog.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersEntity signUp(UsersEntity usersEntity) {
        UsersEntity newUser = new UsersEntity();
        newUser.setFirstName(usersEntity.getFirstName());
        newUser.setLastName(usersEntity.getLastName());
        newUser.setBlocked(false);
        newUser.setUserName(usersEntity.getUserName());
        newUser.setPassWord(passToHash(usersEntity.getPassWord()));
        newUser.setEmailAdress(usersEntity.getEmailAdress());
        return usersRepository.save(newUser);
    }

    public void setAdmin(int id, String userName) {
        if (usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) {

            usersRepository.setRoleAdmin(id, Role.ADMIN);
        }
    }

    public void setSender(int id, String userName) {
        if (usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) {

            usersRepository.setRoleSender(id, Role.SENDER);
        }
    }

    public UsersRepository getUsersRepository() {
        return usersRepository;
    }

    public void setUnSender(int id, String userName) {
        if (usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) {

            usersRepository.setRoleSender(id, Role.USER);
        }
    }

    public void block(int id, String userName) {
        if (usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) {
            if ((usersRepository.findById(id).get().getRole().equals(Role.USER) || usersRepository.findById(id).get().getRole().equals(Role.SENDER)))
                usersRepository.block(true, id);
        }

    }

    public void unBlock(int id, String userName) {
        if (usersRepository.findByUserName(userName).get().getRole().equals(Role.ADMIN)) {
            if ((usersRepository.findById(id).get().getRole().equals(Role.USER) || usersRepository.findById(id).get().getRole().equals(Role.SENDER)))
                usersRepository.block(false, id);
        }
    }

    public String passToHash(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
