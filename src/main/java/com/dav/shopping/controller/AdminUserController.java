package com.dav.shopping.controller;

import com.dav.shopping.entity.Role;
import com.dav.shopping.entity.User;
<<<<<<< HEAD
import com.dav.shopping.service.RoleService;
import com.dav.shopping.service.UserService;
=======
import com.dav.shopping.entity.UserRole;
import com.dav.shopping.service.RoleService;
import com.dav.shopping.service.UserRoleService;
import com.dav.shopping.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
=======
import java.io.IOException;
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
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
<<<<<<< HEAD
=======
    private UserRoleService userRoleService;

    @Autowired
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
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
<<<<<<< HEAD
    public ResponseEntity<Map<String, Boolean>> add(@RequestBody User user) {
        System.out.println(user.getEmail());
=======
    public ResponseEntity<Map<String, Boolean>> add(@RequestBody String data) throws IOException {

        JSONObject jsData = new JSONObject(data);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsData.getJSONObject("user").toString(),User.class);


>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
        Map<String, Boolean> result = new HashMap<>();
        if (user.getEmail().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userService.save(user) != null) {
<<<<<<< HEAD
=======

                JSONArray roleIds = jsData.getJSONArray("userRoles");
                addRole(roleIds, user);
                System.out.print("123213" +  roleIds.length());

>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
                result.put("result", true);
                return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
            }
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }

<<<<<<< HEAD
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
=======
    private void addRole(JSONArray roleIds, User user){
        for(int i= 0 ; i<roleIds.length(); i++){
            Role role = new Role();
            role.setId(roleIds.getLong(i));

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRoleService.save(userRole);
        }
    }

    private void removeUserRoles(User user){
        userRoleService.deleteWithUser(user.getId());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> delete(@RequestParam(required = true, defaultValue = "1", value = "id") long id) {

        Map<String, Boolean> result = new HashMap<>();

        if (userService.delete(id)) {
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
<<<<<<< HEAD
    public ResponseEntity<Map<String, Boolean>> update(@RequestBody User user) {

=======
    public ResponseEntity<Map<String, Boolean>> update(@RequestBody String data) throws IOException {

        JSONObject jsData = new JSONObject(data);

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsData.getJSONObject("user").toString(),User.class);
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92

        Map<String, Boolean> result = new HashMap<>();
        if (user.getEmail().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else if (userService.update(user) != null) {
<<<<<<< HEAD
=======
            removeUserRoles(user);

            JSONArray roleIds = jsData.getJSONArray("userRoles");
            addRole(roleIds, user);

>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }
}
