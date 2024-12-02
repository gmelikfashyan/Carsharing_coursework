package com.example.controllers;

import com.example.entities.User;
import com.example.security.MyUserDetails;
import com.example.services.TripService;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController
{
    private UserService userService;

    @Autowired
    private TripService tripService;

    @GetMapping("account")
    public String getAccount(Model model , @AuthenticationPrincipal MyUserDetails myUserDetails)
    {
        Long id = myUserDetails.getUser().getId();
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("trips", tripService.getTripsByUser(myUserDetails.getUser()));
        return "account";
    }
    @GetMapping("/")
    public String getStart()
    {
        return "redirect:cars/map";
    }

    @PostMapping("registration")
    public String addUser(@ModelAttribute("userReg") User user)
    {
        userService.addUser(user);
        return "redirect:login";
    }
    @GetMapping("login")
    public String getRegistration(Model model)
    {
        model.addAttribute("userReg", new User());
        return "authorization";
    }
}
