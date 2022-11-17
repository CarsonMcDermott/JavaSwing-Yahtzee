import java.awt.Image;
import javax.swing.*;

/*
* file: Die.java
* Author: Carson McDermott
* Date Modified: 9/20/2022
* Description: This program runs Yahtzee, in Java!
*/
//  extends GUIYahtzee?
public class Die{
    JLabel diceLabel;
    JPanel diePanel;

    public int[] hand = new int[7];
        //13 for regular scorecard, 1 for bonus and one for total score    
    ImageIcon dieImageIcon;

    // Simple function for generating numbers, 1-number of dice
    public int rollDice(int numSides){
        int number = (int)(Math.random()*numSides) + 1;
        return number;
    }

    public int[] playerRoll(JFrame frame, JPanel diePanel, int numDice, int numSides, int[] GUIhand, GUIYahtzee gui){
        for(int dieNumber = 1; dieNumber < numDice+1; dieNumber ++){
            if(!checkDieBox(gui, dieNumber, numDice)){
                hand[dieNumber-1] = rollDice(numSides);
                ImageIcon dieImageIcon = new ImageIcon("DiePics/" + hand[dieNumber-1] + ".png");
                Image dieImage = dieImageIcon.getImage();
                dieImage = dieImage.getScaledInstance(150,150, java.awt.Image.SCALE_SMOOTH);
                diceLabel = new JLabel(new ImageIcon(dieImage));
                diePanel.add(diceLabel);
            }
            else{
                ImageIcon dieImageIcon = new ImageIcon("DiePics/" + hand[dieNumber-1] + ".png");
                Image dieImage = dieImageIcon.getImage();
                dieImage = dieImage.getScaledInstance(150,150, java.awt.Image.SCALE_SMOOTH);
                diceLabel = new JLabel(new ImageIcon(dieImage));
                diePanel.add(diceLabel);
            }
        }
        
        //Display Current Roll
        System.out.println("Current dice roll:");
        for(int i = 0; i < numDice; i++){
            System.out.println(hand[i]);
            //Setting the GUIhand array = to the current hand
            GUIhand[i] = hand[i];
        }
        frame.setVisible(true);
        return GUIhand;
    }

    public boolean checkDieBox(GUIYahtzee gui, int index, int numDice){

        // for(int i = 0; i < numDice; i++){
        //     if(gui.keepCheckBoxes.get(i).isSelected()){
        //         return true;
        //     }
        //     else{
        //         return false;
        //     }
        // }

        switch(index){

            case 1:
            if(gui.keepCheckBox1.isSelected()){
                return true;
            }
            else{
                return false;
            }
            case 2:
            if(gui.keepCheckBox2.isSelected()){
                return true;
            }
            else{
                return false;
            }
            case 3:
            if(gui.keepCheckBox3.isSelected()){
                return true;
            }
            else{
                return false;
            }
            case 4:
            if(gui.keepCheckBox4.isSelected()){
                return true;
            }
            else{
                return false;
            }
            case 5:
            if(gui.keepCheckBox5.isSelected()){
                return true;
            }
            else{
                return false;
            }
            case 6:
            if(gui.getNumDice() > 5){
                if(gui.keepCheckBox6.isSelected()){
                    return true;
                }
                else{
                    return false;
                }
            }
            case 7:
            if(gui.getNumDice() > 6){
                if(gui.keepCheckBox7.isSelected()){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return false;
    }



}