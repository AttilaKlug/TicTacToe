package hu.mydomain.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.mydomain.domain.Game;
import hu.mydomain.domain.Move;

/** Az üzleti logikát megvalósító osztály
 * 
 * @author Klug Attila
 *
 */
@Service
@Transactional
public class BusinessLogicService {
    
    private static final Logger log = LoggerFactory.getLogger(BusinessLogicService.class);

    @Autowired
    private AllRepositoryImpl repository;

    @Autowired
    private ComputerAlgorithmService algo;
    
    /**
     * Egy kis üdvözlő üzenet
     */
    private String welcome = "Good morning Sir Player! Let's start!";
    /**
     * Aktuális játék
     */
    private Game savedGame = null;

    public BusinessLogicService() {
        
    }

    /**
     * @return the welcome
     */
    public String getWelcome() {
        return welcome;
    }

    /**
     * @param welcome the welcome to set
     */
    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        if (savedGame == null) {
            if (repository.countGame() != 0) {

                savedGame = repository.findAllGame().get(0);
            } else {
                savedGame = new Game();
                savedGame = repository.saveGame(savedGame);
            }
        }
        return savedGame;
    }

    /**
     * @param game the game to set
     */
    public void setGame(Game game) {
        this.savedGame = game;
    }

    /**
     * Játék újraindítása
     */
    public void resetGame() {

    }

    /**
     *  Játék kiértékelés
     */
    public Game goGame(Game game) {
        savedGame = game;

        if (savedGame.isRestart()) {
            Boolean[][] fields = new Boolean[3][3];
            savedGame.setFields(fields);
            savedGame.setTheEnd(false);
            savedGame.setClickMe(true);
            savedGame.setSelectedPlayer("Player 1");
            savedGame.setRestart(false);
            savedGame = repository.saveGame(savedGame);
            return savedGame;
        }

        if (savedGame.isTheEnd()) {
            Boolean[][] fields = new Boolean[3][3];
            savedGame.setFields(fields);
            savedGame.setTheEnd(false);
            savedGame = repository.saveGame(savedGame);
        } else {
            //Gyors ellenorzes hogy maradt-e üres érték; false: nem maradt, true: maradt
            boolean freeFields = false;

            Boolean[][] fields = savedGame.getFields();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (fields[i][j] == null) {
                        freeFields = true;
                        break;
                    }
                }
            }

            if (savedGame.isMultiPlayer()) {
                if (savedGame.isClickMe()) {
                    savedGame.setClickMe(false);
                    savedGame.setSelectedPlayer("Player 2");
                } else {
                    savedGame.setClickMe(true);
                    savedGame.setSelectedPlayer("Player 1");
                }
                checkGame(freeFields);
            } else {
                if(!checkGame(freeFields)){
                    if (freeFields) {
                        // Computer algoritmus
                        Move bestMove = algo.go(savedGame.getFields());
                        Boolean[][] getFields = savedGame.getFields();
                        
                        getFields[bestMove.getRow()][bestMove.getCol()] = false;
                        savedGame.setFields(getFields);
                        savedGame.setSelectedPlayer("Player 1");
                        
                        freeFields=false;
                        
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 3; j++) {
                                if (fields[i][j] == null) {
                                    freeFields = true;
                                    break;
                                }
                            }
                        }
                        
                        checkGame(freeFields);
                    }
                }
            }
        }

        return savedGame;
    }

    private boolean checkGame(boolean freeFields){
        Boolean[][] array = savedGame.getFields();

        Boolean result = null;

        // Sorok ellenőrzése. ha végeredménye true akkor 1-es játékos, ha false, akkor a 2-es játékos (vagy computer) nyert.
        for (int i = 0; i < 3; i++) {
            if (array[i][0]!=null 
                && array[i][1]!=null 
                && array[i][2]!=null
                && array[i][0]==array[i][1] && array[i][0]==array[i][2]){
                result=array[i][0];
                break;
            }
            if (array[0][i]!=null 
                && array[1][i]!=null 
                && array[2][i]!=null
                && array[0][i]==array[1][i] && array[0][i]==array[2][i]){
                result=array[0][i];
                break;
            }
        }
        
        if (result != null) {
            if (savedGame.isMultiPlayer()) {
                if (result) {
                    savedGame.setWinner("Player 1");
                    savedGame.setPlayerOne(savedGame.getPlayerOne() + 1);
                } else {
                    savedGame.setWinner("Player 2");
                    savedGame.setPlayerTwo(savedGame.getPlayerTwo() + 1);
                }
            } else {
                if (result) {
                    savedGame.setWinner("Player 1");
                    savedGame.setPlayerOne(savedGame.getPlayerOne() + 1);
                } else {
                    savedGame.setWinner("Computer");
                    savedGame.setComputer(savedGame.getComputer() + 1);
                }
            }

            savedGame.setNumberOfMatches(savedGame.getNumberOfMatches() + 1);
            savedGame.setTheEnd(true);
            savedGame = repository.saveGame(savedGame);
            return true;
        } else {
            if (!freeFields) {
                savedGame.setDraw(savedGame.getDraw() + 1);
                savedGame.setWinner("Döntetlen");
                savedGame.setTheEnd(true);
                savedGame = repository.saveGame(savedGame);
                return true;
            }

        }
        
        return false;
    }
}
