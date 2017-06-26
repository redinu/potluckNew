package com.example.demo;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistryController {
	
	@Autowired
	RegistryRepository registryRepository;
	
	@RequestMapping("/")
	public String getForm(Model registryModel){
		
		Registry reg = new Registry(); 
		registryModel.addAttribute("registry", reg);
		
		return "registryForm";
	}
	
	@RequestMapping(path = "/save", method=RequestMethod.POST)
    public String register(@Valid Registry regAttr, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()){
			
			return "registry";
		}
		
		registryRepository.save(regAttr);
		
		return "saved";
    	
    }
	
	@RequestMapping(path = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam Long id,Model listModel){
		
		registryRepository.delete(id);
		Iterable<Registry> regList = registryRepository.findAll();
		listModel.addAttribute("list", regList);
		return "list";
	}
	
	@RequestMapping(path="/list")
	public String list(Model listModel){
		
		Iterable<Registry> regList = registryRepository.findAll();
		listModel.addAttribute("list", regList);
		return "list";
	}
}
