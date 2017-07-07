package com.dav.shopping.controller;

import com.dav.shopping.entity.Function;
import com.dav.shopping.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Duong Vu on 24/06/2017.
 */

@RestController
@RequestMapping("/api/function")
public class FunctionController {

    @Autowired
    private FunctionService functionService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ResponseEntity<?> getFunctions() {
        return new ResponseEntity<List<Function>>(functionService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/get-functions-by-parent", method = RequestMethod.GET)
    public ResponseEntity<?> getByParent(
            @RequestParam(required = true, defaultValue = "1", value = "parentId") long parentId
    ) {

        return new ResponseEntity<List<Function>>(functionService.findByParentId(parentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<?> getById(
            @RequestParam(required = true, defaultValue = "1", value = "id") long parentId) {
        Function function = functionService.findById(parentId);
        if(function.getParentId() != null){

            function.set_parentId(function.getParentId().getId());
        }
        return new ResponseEntity<Function>(function, HttpStatus.OK);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Boolean>> add(@RequestBody Function function) {
        System.out.println(function.getName());
        Map<String, Boolean> result = new HashMap<>();
        if (function.getName().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else if (functionService.create(function)) {
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> delete(
            @RequestParam(required = true, defaultValue = "1", value = "id") long id) {

        Map<String, Boolean> result = new HashMap<>();
        Function  function = functionService.findById(id);
        if (function == null) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        }
        if (functionService.delete(function)) {
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Map<String, Boolean>> update(@RequestBody Function function) {

        System.out.println(function.getName());
        Map<String, Boolean> result = new HashMap<>();
        if (function.getName().trim().equals("")) {
            return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
        } else if (functionService.update(function)!=null) {
            result.put("result", true);
            return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
        }
        result.put("result", false);
        return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
    }
}
