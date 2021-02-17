package hu.mydomain.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

/** A játék fő osztálya (Entitása)
 * 
 * @author Klug Attila
 *
 */
@Entity
public class Game {
    /**
     * A játék egyedi azonosítója
     */
    @Id
    private String id = UUID.randomUUID().toString();
    /**
     * Egyes játékos eredménye
     */
    private Integer playerOne = 0;
    /**
     * Kettes játékos eredménye
     */
    private Integer playerTwo = 0;
    /**
     * Komputer eredménye
     */
    private Integer computer = 0;
    /**
     * Döntetlen
     */
    private Integer draw = 0;
    /**
     * Több játékos mód
     */
    private boolean multiPlayer = false;
    
    /**
     * játék vége jelzésre szolgáló érték
     */
    private boolean theEnd= false;
    /**
     * Meccsek száma
     */
    private Integer numberOfMatches  = 0;
    /**
     * Mezők értéke
     */
    @Transient
    private Boolean[][] fields = new Boolean[3][3];
    
    /**
     * Aktuális játékos
     */
    private String selectedPlayer = "Player 1";
    /**
     * Te vagy a soron
     */
    private boolean clickMe = true;
    /**
     * Nyertes kiíratása
     */
    private String winner;
    
    private boolean restart=false;
    
    
    public Game(){

    }

    
    /**
     * @return the clickMe
     */
    public boolean isClickMe() {
        return clickMe;
    }


    /**
     * @param clickMe the clickMe to set
     */
    public void setClickMe(boolean clickMe) {
        this.clickMe = clickMe;
    }


    /**
     * @return the fields
     */
    public Boolean[][] getFields() {
        return fields;
    }

    /**
     * @param fields the fields to set
     */
    public void setFields(Boolean[][] fields) {
        this.fields = fields;
    }

    /**
     * @return the selectedPlayer
     */
    public String getSelectedPlayer() {
        return selectedPlayer;
    }

    /**
     * @param selectedPlayer the selectedPlayer to set
     */
    public void setSelectedPlayer(String selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the playerOne
     */
    public Integer getPlayerOne() {
        return playerOne;
    }

    /**
     * @param playerOne the playerOne to set
     */
    public void setPlayerOne(Integer playerOne) {
        this.playerOne = playerOne;
    }

    /**
     * @return the playerTwo
     */
    public Integer getPlayerTwo() {
        return playerTwo;
    }

    /**
     * @param playerTwo the playerTwo to set
     */
    public void setPlayerTwo(Integer playerTwo) {
        this.playerTwo = playerTwo;
    }

    /**
     * @return the computer
     */
    public Integer getComputer() {
        return computer;
    }

    /**
     * @param computer the computer to set
     */
    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    /**
     * @return the draw
     */
    public Integer getDraw() {
        return draw;
    }

    /**
     * @param draw the draw to set
     */
    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    /**
     * @return the multiPlayer
     */
    public boolean isMultiPlayer() {
        return multiPlayer;
    }

    /**
     * @param multiPlayer the multiPlayer to set
     */
    public void setMultiPlayer(boolean multiPlayer) {
        this.multiPlayer = multiPlayer;
    }

    /**
     * @return the theEnd
     */
    public boolean isTheEnd() {
        return theEnd;
    }

    /**
     * @param theEnd the theEnd to set
     */
    public void setTheEnd(boolean theEnd) {
        this.theEnd = theEnd;
    }

    /**
     * @return the numberOfMatches
     */
    public Integer getNumberOfMatches() {
        return numberOfMatches;
    }

    /**
     * @param numberOfMatches the numberOfMatches to set
     */
    public void setNumberOfMatches(Integer numberOfMatches) {
        this.numberOfMatches = numberOfMatches;
    }


    /**
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }


    /**
     * @param winner the winner to set
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }


    /**
     * @return the restart
     */
    public boolean isRestart() {
        return restart;
    }


    /**
     * @param restart the restart to set
     */
    public void setRestart(boolean restart) {
        this.restart = restart;
    }
    
    
}
