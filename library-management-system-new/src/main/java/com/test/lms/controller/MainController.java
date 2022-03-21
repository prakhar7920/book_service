package com.test.lms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.lms.entity.User;
import com.test.lms.helper.Message;
import com.test.lms.repository.UserRepository;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/")
	public String home() {
		return "redirect:login";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	// this is handler for registering user
	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,Model model,HttpSession session) {
		String userID = user.getUser_Id();
		String password = user.getUser_password();
		
		if(userID.length() < 5 || userID.length() > 50) {
			session.setAttribute("message",new Message("It should contain Min of 5 and Max of 50 characters !!", "alert-danger"));
			return "register";
		}
		
		if(password.length() < 5 || password.length() > 50) {
			session.setAttribute("message",new Message("It should contain Min of 5 and Max of 50 characters !!", "alert-danger"));
			return "register";
		}
		
		try {
			System.out.println("User"+user);
			User result = this.userRepository.save(user);
			model.addAttribute("user", new User());
			
			session.setAttribute("message",new Message("Successfully Registered !!", "alert-success"));
			return "register";
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message",new Message("Something went wrong !!"+e.getMessage(), "alert-danger"));
			return "register";
		}
	}
	
	@RequestMapping(value = "/do_login",method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user,Model model,HttpSession session) {
		String userID = user.getUser_Id();
		String password = user.getUser_password();
		
//		if(userID.length() < 5 || userID.length() > 50) {
//			session.setAttribute("message",new Message("It should contain Min of 5 and Max of 50 characters !!", "alert-danger"));
//			return "login";
//		}
//		
//		if(password.length() < 5 || password.length() > 50) {
//			session.setAttribute("message",new Message("It should contain Min of 5 and Max of 50 characters !!", "alert-danger"));
//			return "login";
//		}
		
		List<User> userByUserId = userRepository.getUserByUserId(userID);
		if(userByUserId.size() == 0) {
			session.setAttribute("message",new Message("User Does not Exist !", "alert-danger"));
			return "register";
		}else {
			for(User u : userByUserId) {
				if(u.getUser_password().equals(password)) {
//					System.out.println(u.getUser_Id()+" "+u.getUser_password());
					return "redirect:books";
				}
			}
		}
		session.setAttribute("message",new Message("Incorrect password !", "alert-danger"));
		return "login";
	}
}
