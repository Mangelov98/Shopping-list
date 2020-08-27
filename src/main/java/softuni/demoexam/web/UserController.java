package softuni.demoexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.demoexam.models.binding.UserAddBindingModel;
import softuni.demoexam.models.binding.UserLoginBindingModel;
import softuni.demoexam.models.service.UserServiceModel;
import softuni.demoexam.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userLoginBindingModel")){
            model.addAttribute("userLoginBindingModel",new UserLoginBindingModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        } else {
            UserServiceModel user = this.userService.findByUsername(userLoginBindingModel.getUsername());

            if (user == null || !user.getPassword().equals(userLoginBindingModel.getPassword())) {
                redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
                redirectAttributes.addFlashAttribute("notFound", true);
                return "redirect:login";
            }
            httpSession.setAttribute("user", user);
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userAddBindingModel")) {
            model.addAttribute("userAddBindingModel", new UserAddBindingModel());

        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userAddBindingModel") UserAddBindingModel userAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userAddBindingModel.getPassword().equals(userAddBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userAddBindingModel", userAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userAddBindingModel", bindingResult);
            return "redirect:register";
        } else {
            this.userService.registerUser(this.modelMapper.map(userAddBindingModel, UserServiceModel.class));
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
