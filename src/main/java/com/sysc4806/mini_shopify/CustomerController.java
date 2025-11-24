package com.sysc4806.mini_shopify;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("" )
    public String customer(){
        return "customerHomePage";
    }
}
