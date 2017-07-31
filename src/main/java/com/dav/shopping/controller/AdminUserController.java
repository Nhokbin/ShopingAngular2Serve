package com.dav.shopping.controller;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
import com.dav.shopping.service.RoleService;
import com.dav.shopping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Duong Vu on 16/06/2017.
 */

@RestController
@RequestMapping("/api/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public Page<User> getRoles(@RequestParam(required = true, defaultValue = "1", value = "page") int page,
                               @RequestParam(required = false, defaultValue = "1", value = "pageSize") int pageSize,
                               @RequestParam(required = false, defaultValue = "", value = "filter") String filter) {

        return userService.findAll(page, pageSize, filter);
    }

    @RequestMapping(value = "/get-detail", method = RequestMethod.GET)
    public ResponseEntity<User> getDetail(@RequestParam(required = true, defaultValue = "1", value = "id") long id) {

        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> add(@RequestBody User user) {
        System.out.println(user.getEmail());
        Map<String, Boolean> result = new HashMap<>();
        if (user.getEmail().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userService.save(user) != null) {
                result.put("result", true);
                return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
            }
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> delete(
            @RequestParam(required = true, defaultValue = "1", value = "id") long id) {

        Map<String, Boolean> result = new HashMap<>();
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        }
        if (userService.delete(user)) {
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> update(@RequestBody User user) {


        Map<String, Boolean> result = new HashMap<>();
        if (user.getEmail().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else if (userService.update(user) != null) {
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }
}
