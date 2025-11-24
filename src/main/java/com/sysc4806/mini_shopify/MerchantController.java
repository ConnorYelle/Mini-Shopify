package com.sysc4806.mini_shopify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
    @GetMapping("" )
    public String merchant(){
        return "merchantHomePage";
    }
}
