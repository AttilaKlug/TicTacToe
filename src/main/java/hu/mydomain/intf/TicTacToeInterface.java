package hu.mydomain.intf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hu.mydomain.domain.Game;
import hu.mydomain.service.BusinessLogicService;

@Controller
@RequestMapping(value = "/tictactoe")
public class TicTacToeInterface {
    
    @Autowired
    private BusinessLogicService service;
    
    @RequestMapping(value = "/savegame",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap saveGame(@RequestBody Game game) {
        
        return new ModelMap("success", true);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String defaultMapping() {
        
        return "Good Game!";
    }
    
    @RequestMapping(value = "/getgame", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame() {
        Game game = service.getGame();
        return game;
    }
    
    @RequestMapping(value = "/gogame",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public Game goGame(@RequestBody Game game) {
        Game g = service.goGame(game);
        return g;
    }
}
