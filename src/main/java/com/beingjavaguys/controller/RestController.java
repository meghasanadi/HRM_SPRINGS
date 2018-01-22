package com.beingjavaguys.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import com.beingjavaguys.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beingjavaguys.model.Employee;
import com.beingjavaguys.services.DataServices;

@Controller
@RequestMapping("/employee")
public class RestController {

	@Autowired
	DataServices dataServices;
	

	static final Logger logger = Logger.getLogger(RestController.class);

	

	@RequestMapping(value = "/saveEmployee/{firstname}/{lastname}/{email}/{phone}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status saveNewmployee(@PathVariable("firstname") String firstname,@PathVariable("lastname") String lastname,@PathVariable("email") String email,@PathVariable("phone") String phone) throws Exception {
		try {
			System.out.println(firstname+lastname+email+phone);
		Employee employee=new Employee();
    	employee.setFirstName(firstname);
		employee.setLastName(lastname);
		employee.setEmail(email);
		employee.setPhone(phone);
			
			dataServices.saveEmployee(employee);
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		} 
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET,consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Status addEmployee(@RequestBody Employee employee) {
		try {
			
			dataServices.addEntity(employee);
			return new Status(1, "Employee added Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Employee getEmployee(@PathVariable("id") long id) {
		Employee employee = null;
		try {
			employee = dataServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Employee> getEmployee() {

		List<Employee> employeeList = null;
		try {
			employeeList = dataServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeeList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Status deleteEmployee(@PathVariable("id") long id) {

		try {
			dataServices.deleteEntity(id);
			return new Status(1, "Employee deleted Successfully !");
		} catch (Exception e) {
			return new Status(0, e.toString());
		}

	}
	
	
}
