package hu.mydomain.intf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.mydomain.service.BusinessLogicService;

@Controller
@RequestMapping(value = "/app")
public class ApplicationInterface {

    @Autowired
    private BusinessLogicService service;
    
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @ResponseBody
    public String welcome(){
        return service.getWelcome();
    }
}
