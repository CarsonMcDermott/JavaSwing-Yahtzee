import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* file: Score.java
* Author: Carson McDermott
* Date Modified: 9/20/2022
* Description: This program runs Yahtzee, in Java!
*/

public class Score implements ActionListener{

    //tempscorecard buttons
    JButton tBStart, tB13, tB14, tB15, tB16, tB17, tB18, tB19; 

    int numDice;
    int numSides;
    int numRolls;

    Score(){
        // System.out.println("NumDice:" + numDice + ", NumSides: " + numSides);
        // this.numDice = getNumDice();
        // this.numSides = getNumSides();
    }

    public void setValues(int numDice, int numSides){
        this.numDice = numDice;
        this.numSides = numSides;
    }

    // public Score(int numDice, int numSides){
    //     this.numDice = numDice;
    //     this.numSides = numSides;
    // }

    //this function returns the count of the die value occurring most in the hand
    //but not the value itself
    public int maxOfAKindFound(int[] hand){
        int maxCount = 0;
        int currentCount;
        for (int dieValue = 1; dieValue <=numSides; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < numDice; diePosition++)
            {
                if (hand[diePosition] == dieValue)
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;
    }
    //checks for a max straight of the dice.
    public int maxStraightFound(int[] hand){
        int maxLength = 1;
        int curLength = 1;
        for(int counter = 0; counter < numDice -1; counter++)
        {
            if (hand[counter] + 1 == hand[counter + 1] ) //jump of 1
                curLength++;
            else if (hand[counter] + 1 < hand[counter + 1]) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    } 
    //checks to see if there is a full house, (2x + 3x of dice)
    public boolean fullHouseFound(int[] hand){
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        int currentCount ;
        for (int dieValue = 1; dieValue <=numSides; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < numDice; diePosition++)
            {
                if (hand[diePosition] == dieValue)
                    currentCount++;
            }
            if (currentCount == numDice -3)
                found2K = true;
            if (currentCount == numDice -2)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
        return foundFH;
    }

    // int totalAllDice(int hand[])
    //this function returns the total value of all dice in a hand
    public int totalAllDice(int[] hand){
        int total = 0;
        for(int diePosition = 0; diePosition < numDice; diePosition++){
            total += hand[diePosition];
        }
        return total;
    }

    //Sorts the array, after finishing your turn.
    private void sortArray(int[] hand, int size){
        boolean swap;
        int temp;

        do
        {
            swap = false;
            for (int count = 0; count < (size - 1); count++)
            {
                if (hand[count] > hand[count + 1])
                {
                    temp = hand[count];
                    hand[count] = hand[count + 1];
                    hand[count + 1] = temp;
                    swap = true;
                }
            }
        } while (swap);

    }
    //Tallies the total score of the dice rolls, and checks for special cases of dice sequences.
    public void totalScore(int[] hand, int[] score, int[] officialScore, Scanner kb){
        sortArray(hand, numDice);
        System.out.println("Here is your sorted hand : ");
        for (int dieNumber = 0; dieNumber < numDice; dieNumber++)
            {
                System.out.print(hand[dieNumber] + " ");
            }
            System.out.println();
        //upper scorecard
        for (int dieValue = 1; dieValue <= numSides; dieValue++)
        {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < numDice; diePosition++)
            {
                if (hand[diePosition] == dieValue)
                    currentCount++;
                    // System.out.print("Score " + dieValue * currentCount + " on the ");
                    // System.out.println(dieValue + " line");
            }
            System.out.print(dieValue + ": Score " + dieValue * currentCount + " on the ");
            System.out.println(dieValue + " line");
            score[dieValue -1] = dieValue * currentCount;
        }
        //lower scorecard
        if (maxOfAKindFound(hand) >= numDice - 2)
        {
            System.out.print("7: Score " + totalAllDice(hand) + " on the ");
            System.out.println("3 of a Kind line");
            score[numSides] = totalAllDice(hand);
        }
        else{
            System.out.println("7: Score 0 on the 3 of a Kind line");
            score[numSides] = 0;
        }

        if (maxOfAKindFound(hand) >= numDice - 1)
        {
            System.out.print("8: Score " + totalAllDice(hand) + " on the ");
            System.out.println("4 of a Kind line");
            score[numSides+1] = totalAllDice(hand);
        }
        else{
            System.out.println("8: Score 0 on the 4 of a Kind line");
            score[numSides+1] = 0;
        }

        if (fullHouseFound(hand)){
            System.out.println("9: Score 25 on the Full House line");
            score[numSides+2] = 25;
        }
        else{
            System.out.println("9: Score 0 on the Full House line");
            score[numSides+2] = 0;
        }

        if(maxStraightFound(hand) >= numDice - 3){
            System.out.println("10: Score 30 on the Small Straight line");
            score[numSides+3] = 30;
        }
        else{
            System.out.println("10: Score 0 on the Small Straight line");
            score[numSides+3] = 0;
        }

        if(maxStraightFound(hand) >= numDice-2){
            System.out.println("11: Score 40 on the Large Straight line");
            score[numSides+4] = 40;
        }
        else{
            System.out.println("11: Score 0 on the Large Straight line");
            score[numSides+4] = 0;
        }

        if (maxOfAKindFound(hand) >= numDice){
            System.out.println("12: Score 50 on the Yahtzee line");
            score[numSides+5] = 50;
        }
        else{
            System.out.println("12: Score 0 on the Yahtzee line");
            score[numSides+5] = 0;
        }

        System.out.print("13: Score " + totalAllDice(hand) + " on the ");
        System.out.println("Chance line");
        score[numSides+6] = totalAllDice(hand);
        System.out.println();
    }

    public void makeScorecard(int[] officialScore){
        for(int i = 0; i < 13; i++){
            officialScore[i] = -1;
        }
    }

    public void printScoreCard(int[] officialscore, JFrame officialScoreFrame, JPanel officialScorePanel, int numDice, int numSides){
        officialScorePanel.removeAll();
        String linesForOS = "";
        // System.out.println("Official Scorecard:");
        linesForOS += "Official Scorecard\n";
        linesForOS += "-------------------\n";
        //upper scorecard
        for(int i = 0; i < numSides; i++){
            if(officialscore[i] == -1){
                linesForOS += "Score 0 on the " + (i + 1) + " line\n";
            }
            else{
                linesForOS += "Score " + officialscore[i] + " on the " + (i + 1) + " line\n";
            }
        }
        //Calculate score for bonus by adding prior indexes
        int bonusCheck = 0;
        for(int i = 0; i <numSides; i++){
            bonusCheck += officialscore[i];
        }
        int bonus;
        if(bonusCheck > 63){
            bonus = 35;
            linesForOS += "Bonus: " + bonus +"\n";
        }
        else{
            bonus = 0;
            linesForOS += "Bonus: " + bonus +"\n";
        }

        int upperScorecardTotal = 0;
        for(int i = 0; i < numSides; i++){
            upperScorecardTotal += officialscore[i];
        } 
        upperScorecardTotal += bonus;

        linesForOS += "Upper Scorecard Total: " + upperScorecardTotal +"\n";

        linesForOS += "-------------------\n";

        for(int i = numSides; i < numSides + 7; i ++){
            //lowerscore card
            // 3K
            if(i == numSides){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the 3 of a Kind line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the 3 of a Kind Line\n";
                }
            }
            // 4K
            if(i == numSides+1){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the 4 of a Kind line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the 4 of a Kind Line\n";
                }
            }
            // FH
            if(i == numSides+2){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the Full House line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the Full House Line\n";
                }
            }
            // SS
            if(i == numSides+3){
                if(officialscore[i] == -1){
                    linesForOS +="Score 0 on the Small Straight Line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the Small Straight Line\n";
                }
            }
            // LS
            if(i == numSides+4){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the Large Straight Line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the Large Straight Line\n";
                }
            }
            // Y
            if(i == numSides+5){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the Yahtzee Line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the Yahtzee Line\n";
                }
            }
            // C
            if(i == numSides+6){
                if(officialscore[i] == -1){
                    linesForOS += "Score 0 on the Chance Line\n";
                }
                else{
                    linesForOS += "Score " + officialscore[i] + " on the Chance Line\n";
                }
            }
        }
        //Lower Total
        int lowerScorecardTotal = 0;
        for(int i = numSides; i < numSides + 7; i ++){
            lowerScorecardTotal += officialscore[i];
        }
        linesForOS += "Lower Scorecard total: " + lowerScorecardTotal +"\n";
        // Upper Total
        linesForOS += "Upper Scorecard total: " + upperScorecardTotal + "\n";
        //Grand Total
        int grandTotalScore = upperScorecardTotal + lowerScorecardTotal;
        // officialscore[numSides+12] = officialscore[numSides+10] + officialscore[numSides+11];
        linesForOS += "Grand Total Score: " + grandTotalScore + "\n";

        JTextArea officialScoreLabel = new JTextArea(linesForOS);
        officialScoreLabel.setEditable(false);
        officialScorePanel.add(officialScoreLabel);
        officialScoreFrame.add(officialScorePanel);
        officialScoreFrame.setVisible(true);
    }

    public boolean validNum(int[] tempScore, int[] officialScore, String input){
        if(officialScore[Integer.parseInt(input) - 1] == -1){
            officialScore[Integer.parseInt(input) -1 ] = tempScore[Integer.parseInt(input) - 1];
            return true;
        }
        return false;
    }

    public int[] updateTempScore(int[] hand, int[]tempScore, int[] officialScore, int numDice, int numSides, JPanel tempScorePanel, JFrame frame)
    {
        sortArray(hand, numDice);
        //Upper Scorecard
        for (int dieValue = 1; dieValue <= numSides; dieValue++){
            int currentCount = 0;
            for (int diePosition = 0; diePosition < numDice; diePosition++){
                if (hand[diePosition] == dieValue){
                    currentCount++;
                }
            }
            System.out.print(dieValue + ": Score " + dieValue * currentCount + " on the ");
            System.out.println(dieValue + " line");
            tempScore[dieValue - 1] = dieValue * currentCount;
        }
        //lower scorecard
        if (maxOfAKindFound(hand) >= numDice - 2)
        {
            System.out.print("7: Score " + totalAllDice(hand) + " on the ");
            System.out.println("3 of a Kind line");
            tempScore[numSides] = totalAllDice(hand);
        }
        else{
            System.out.println("7: Score 0 on the 3 of a Kind line");
            tempScore[numSides] = 0;
        }


        if (maxOfAKindFound(hand) >= numDice - 1)
        {
            System.out.print("8: Score " + totalAllDice(hand) + " on the ");
            System.out.println("4 of a Kind line");
            tempScore[numSides+1] = totalAllDice(hand);
        }
        else{
            System.out.println("8: Score 0 on the 4 of a Kind line");
            tempScore[numSides+1] = 0;
        }
        if (fullHouseFound(hand)){
            System.out.println("9: Score 25 on the Full House line");
            tempScore[numSides+2] = 25;
        }
        else{
            System.out.println("9: Score 0 on the Full House line");
            tempScore[numSides+2] = 0;
        }

        if(maxStraightFound(hand) >= numDice - 1){
            System.out.println("10: Score 30 on the Small Straight line");
            tempScore[numSides+3] = 30;
        }
        else{
            System.out.println("10: Score 0 on the Small Straight line");
            tempScore[numSides+3] = 0;
        }

        if(maxStraightFound(hand) >= numDice){
            System.out.println("11: Score 40 on the Large Straight line");
            tempScore[numSides+4] = 40;
        }
        else{
            System.out.println("11: Score 0 on the Large Straight line");
            tempScore[numSides+4] = 0;
        }

        if (maxOfAKindFound(hand) >= numDice){
            System.out.println("12: Score 50 on the Yahtzee line");
            tempScore[numSides+5] = 50;
        }
        else{
            System.out.println("12: Score 0 on the Yahtzee line");
            tempScore[numSides+5] = 0;
        }
        System.out.print("13: Score " + totalAllDice(hand) + " on the ");
        System.out.println("Chance line");
        tempScore[numSides+6] = totalAllDice(hand);

        // System.out.println();
        //return the updated tempScore Array
        // tempScorePanel.remove(frame);
        // frame.add(tempScorePanel);
        return tempScore;
    }

    public void updateTempScorePanel(JFrame frame, JPanel tempScorePanel, ArrayList<JButton> tempScoreButtons, int[] tempScore, int numDice, int numSides){
        // tempScorePanel.removeAll();
        // for(int i = 0; i < numSides; i++){
        //     tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on 1 Line" );
        // }
        // tempScorePanel.add(tempScoreButtons);
        // // frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        // TODO Auto-generated method stub
        
    }
}