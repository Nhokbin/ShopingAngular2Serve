package com.dav.shopping.controller;

import com.dav.shopping.entity.Action;
import com.dav.shopping.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Duong Vu on 30/06/2017.
 */

@RestController
@RequestMapping("/api/action")
public class ActionController {

    @Autowired
    private ActionService actionService;

    @RequestMapping(value = "/get-by-type", method = RequestMethod.GET)
    public List<Action> getActions(@RequestParam(required = true, defaultValue = "error", value = "name") String name){
        System.out.print(name);

        return actionService.findByType("/api/"+name);
    }

}
