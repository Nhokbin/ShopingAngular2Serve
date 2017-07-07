package com.dav.shopping.controller;

import com.dav.shopping.entity.Action;
import com.dav.shopping.entity.Function;
import com.dav.shopping.entity.Permission;
import com.dav.shopping.entity.Role;
import com.dav.shopping.service.PermissionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Duong Vu on 06/07/2017.
 */
@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/get-permissions", method = RequestMethod.GET)
    public ResponseEntity<?> getPermissions(
            @RequestParam(required = true, defaultValue = "0", value = "functionId") long functionId,
            @RequestParam(required = true, defaultValue = "0", value = "roleId") long roleId,
            @RequestParam(required = true, defaultValue = "", value = "nameAction") String nameAction){
        return new ResponseEntity<List<Permission>>(permissionService.getPermission(roleId,functionId,"/api/"+nameAction), HttpStatus.OK);
    }

    @RequestMapping(value = "/save-permission", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> add(@RequestBody String data) throws IOException {

        JSONObject jsData = new JSONObject(data);

        Map<String, Boolean> result = new HashMap<>();
        Role role = new Role();
        role.setId(jsData.getLong("role"));
        Function function = new Function();
        function.setId(jsData.getLong("function"));

        JSONArray arrayActions = jsData.getJSONArray("actions");

        for(int i=0;i<arrayActions.length(); i++){
            Action action = new Action();
            action.setId(arrayActions.getLong(i));

            Permission permission = new Permission();
            permission.setAction(action);
            permission.setFunction(function);
            permission.setRole(role);

            permissionService.save(permission);
        }

        /*if (user.getEmail().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userService.save(user) != null) {

                JSONArray roleIds = jsData.getJSONArray("userRoles");
                addRole(roleIds, user);
                System.out.print("123213" +  roleIds.length());

                result.put("result", true);
                return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
            }
        }*/
        result.put("result", true);
        return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
    }
}
