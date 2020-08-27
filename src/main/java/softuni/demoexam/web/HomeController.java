package softuni.demoexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.demoexam.service.ProductService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public HomeController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public ModelAndView home(HttpSession httpSession,ModelAndView modelAndView){

        if (httpSession.getAttribute("user") == null){
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("products", this.productService.findAllProducts());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }


}
