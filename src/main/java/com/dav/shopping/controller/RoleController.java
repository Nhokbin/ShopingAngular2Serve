package com.dav.shopping.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dav.shopping.entity.Action;
import com.dav.shopping.entity.Function;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dav.shopping.entity.Role;
import com.dav.shopping.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/get-roles", method = RequestMethod.GET)
	public List<Role> getRoles(){
		return roleService.findAll();
	}

<<<<<<< HEAD
=======
	@RequestMapping(value = "/get-by-user", method = RequestMethod.GET)
	public List<Role> getUserRole(@RequestParam(required = true, defaultValue = "1", value = "userId") long userId){
		return roleService.findByUser(userId);
	}

>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
	@RequestMapping(value = "/get-all", method = RequestMethod.GET)
	public Page<Role> getRoles(@RequestParam(required = true, defaultValue = "1", value = "page") int page,
			@RequestParam(required = false, defaultValue = "1", value = "pageSize") int pageSize,
			@RequestParam(required = false, defaultValue = "", value = "filter") String filter) {

		return roleService.findAll(page, pageSize, filter);
	}

	@RequestMapping(value = "/get-detail", method = RequestMethod.GET)
	public ResponseEntity<Role> getDetail(@RequestParam(required = true, defaultValue = "1", value = "id") long id) {

		Role role = roleService.findById(id);

		if (role == null) {
			return new ResponseEntity<Role>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Boolean>> add(@RequestBody Role role) {
		System.out.println(role.getRole());
		Map<String, Boolean> result = new HashMap<>();
		if (role.getRole().trim().equals("")) {
			return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
		} else if (roleService.createRole(role)) {
			result.put("result", true);
			return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
		}
		result.put("result", false);
		return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/save-permission", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Boolean>> savePermission(@RequestBody String jsData) {

		JSONObject jsRole = new JSONObject(jsData);

		System.out.println(jsRole.getInt("id"));
		Map<String, Boolean> result = new HashMap<>();
		Role role = roleService.findById(jsRole.getInt("id"));

		if (role == null) {
			return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
		}

		JSONArray jsFunctions = jsRole.getJSONArray("functions");
		JSONArray jsActions = jsRole.getJSONArray("actions");

		List<Function> functions = new ArrayList<>();
		List<Action> actions = new ArrayList<>();

		int functionLength = jsFunctions.length();
		int actionLength = jsActions.length();

		for (int i=0; i<functionLength; i++){
			Function function = new Function();
			function.setId(jsFunctions.getJSONObject(i).getLong("id"));
			functions.add(function);
		}

		for(int i = 0 ; i<actionLength; i++){
			Action action = new Action();
			action.setId(jsActions.getJSONObject(i).getLong("id"));
			actions.add(action);
		}


<<<<<<< HEAD
		role.setFunctions(functions);
=======
	/*	role.setFunctions(functions);
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
		role.setActions(actions);
		if(roleService.savePermission(role)!=null){
			result.put("result", true);
			return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
<<<<<<< HEAD
		}
=======
		}*/
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
		return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Boolean>> update(@RequestBody Role role) {

		System.out.println(role.getRole());
		Map<String, Boolean> result = new HashMap<>();
		if (role.getRole().trim().equals("")) {
			return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
<<<<<<< HEAD
		} else if (roleService.createRole(role)) {
=======
		} else if (roleService.update(role)) {
>>>>>>> 186bf2c4019450fca81ea03ab4dd11a24e2dee92
			result.put("result", true);
			return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
		}
		result.put("result", false);
		return new ResponseEntity<Map<String, Boolean>>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Boolean>> delete(
			@RequestParam(required = true, defaultValue = "1", value = "id") long id) {

		Map<String, Boolean> result = new HashMap<>();
		Role role = roleService.findById(id);
		if (role == null) {
			return new ResponseEntity<Map<String, Boolean>>(HttpStatus.NOT_FOUND);
		}
		if (roleService.delete(role)) {
			result.put("result", true);
			return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.OK);
		}
		result.put("result", false);
		return new ResponseEntity<Map<String, Boolean>>(result, HttpStatus.BAD_REQUEST);
	}
}
