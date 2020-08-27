package softuni.demoexam.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.demoexam.models.binding.ProductAddBindingModel;
import softuni.demoexam.models.service.ProductServiceModel;
import softuni.demoexam.service.ProductService;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        if (!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel",new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel") ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }
        this.productService.addProduct(this.modelMapper.map(productAddBindingModel, ProductServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        this.productService.delete(id);

        return "redirect:/";
    }

    @GetMapping("/delete/all")
    public String deleteAll(){
        this.productService.deleteAll();
        return "redirect:/";
    }
}
