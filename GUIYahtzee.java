import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* file: GUIYahtzee.java
* Author: Carson McDermott
* Date Modified: 11/16/2022
* Description: This program runs Yahtzee, in Java!
*/

public class GUIYahtzee implements ActionListener{

//startFrame Components
    JFrame startFrame;
    JPanel welcomePanel, instructionsPanel, configPanel, dropBoxPanel;
    JLabel welcomeLabel, ins1, ins2, ins3, ins4, configLabel1, configLabel2;
    JButton confirmConfigButton;
    
    Integer[] DiceNum = {5, 6, 7};
    Integer[] SideNum =  {6, 8, 12};
    Integer[] RollNum = {3, 4, 5};

    JComboBox<Integer> DiceBox;
    JComboBox<Integer> SidesBox;
    JComboBox<Integer> RollsBox;

    int numDice;
    int numSides;
    int numRolls;

//Main Frame Components

    Die die = new Die();
    Score score = new Score();
    int[] hand, tempScore, officialScore;

    JFrame frame;
    JPanel buttonPanel, diePanel, checkboxPanel;
    JButton officialScorecardButton, rollDice, nextTurn;
    JCheckBox keepCheckBox1,keepCheckBox2,keepCheckBox3,keepCheckBox4,keepCheckBox5,keepCheckBox6,keepCheckBox7;
    ArrayList<JCheckBox> keepCheckBoxes;
    //Dice
    JLabel label, dieLabel, numSidesLabel, numDiceLabel, numRollsLabel, rollsLeftLabel; 
    ImageIcon diePic1, diePic2, diePic3, diePic4, diePic5, diePic6, diePic7, diePic8;

    int player  = 1;
    int diceRollCount = 1;
    int playerTurn = 0;
    int finalOfficialScore = 0;
    //Buttons

    //Temp Scorecard Panel
    JPanel tempScorePanel;
    ArrayList<JButton> tempScoreButtons;

    //Official Scorecard Frame
    JFrame officialScoreFrame;
    JPanel officialScorePanel;
    JLabel officialScore1,  officialScore2,  officialScore3,  officialScore4,  officialScore5,  officialScore6,  officialScore7,  officialScore8,  officialScore9,  officialScore10,  officialScore11,  officialScore12,  officialScore13,  officialScore14,  officialScore15,  officialScore16,  officialScore17,  officialScore18,  officialScore19,  officialScore20;  


//Game Over Frame
    //frame
    JFrame gameOverFrame;
    JPanel gameOverPanel;
    JLabel gameOverLabel, finalScoreLabel;



    GUIYahtzee(){
        //preset values for numDice, numSides, numRolls;
        setNumDice(5);
        setNumSides(6);
        setNumRolls(3);

    
        //Components for startup screen
        //Start frame
        startFrame = new JFrame();
        startFrame.setTitle("Welcome to Yahtzee!");
        startFrame.getContentPane().setBackground(Color.DARK_GRAY);
        startFrame.setSize(1500, 600);
        startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        startFrame.setLayout(null);

        //Welcome Panel
        welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.lightGray);
        welcomePanel.setBounds(20, 20, 1450, 100);

        //Welcome Label
        welcomeLabel = new JLabel();
        welcomeLabel.setText("-------WELCOME TO YAHTZEE-------");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 72));

        //Adding welcome Label/Panel
        welcomePanel.add(welcomeLabel);
        startFrame.add(welcomePanel);


        //Instructions Panel
        instructionsPanel = new JPanel();
        instructionsPanel.setBackground(Color.lightGray);
        instructionsPanel.setBounds(20,130, 1450, 225);

        //Instructions Label 1
        ins1 = new JLabel();
        ins1.setText("------------Yahtzee Instructions------------");
        ins1.setFont(new Font("Arial", Font.PLAIN, 48));
        //Instructions Label 2
        ins2 = new JLabel();
        ins2.setText("Step 1: Roll the dice for the given number of turns. You can select specific dice to keep between rolls!");
        ins2.setFont(new Font("Arial", Font.PLAIN, 28));
        //Instructions Label 3
        ins3 = new JLabel();
        ins3.setText("Step 2: After finishing your rolls, select which of the scorecard scores your would like to keep!");
        ins3.setFont(new Font("Arial", Font.PLAIN, 28));
        //Instructions Label 4
        ins4 = new JLabel();
        ins4.setText("Step 3: Repeat this process until your scorecard is filled. Add all the scores together, and that is your final score!");
        ins4.setFont(new Font("Arial", Font.PLAIN, 28));

        // Add instruction labels/panels
        instructionsPanel.add(ins1);
        instructionsPanel.add(ins2);
        instructionsPanel.add(ins3);
        instructionsPanel.add(ins4);
        startFrame.add(instructionsPanel);

        //Config panel
        configPanel = new JPanel();
        configPanel.setBackground(Color.lightGray);
        configPanel.setBounds(20, 375, 1450, 80);
        //Config labels
        configLabel1 = new JLabel();
        configLabel1.setText("Please select the number of DICE, SIDES, and ROLLS you would like to play with.");
        configLabel1.setFont(new Font("Arial", Font.PLAIN, 28));
        configLabel2 = new JLabel();
        configLabel2.setText("When you are happy with your configuration, please press the 'Confirm Configuration and Start Game' button");
        configLabel2.setFont(new Font("Arial", Font.PLAIN, 24));

        configPanel.add(configLabel1);
        configPanel.add(configLabel2);
        startFrame.add(configPanel);

        //Panel for JCombo Boxes
        dropBoxPanel = new JPanel();
        dropBoxPanel.setBackground(Color.lightGray);
        dropBoxPanel.setBounds(20, 475, 1450, 50);

        //Configuration Combo Boxes
        //Number of Dice Boxes
        DiceBox = new JComboBox<Integer>(DiceNum);
        DiceBox.setSelectedItem(0);
        DiceBox.addActionListener((ActionListener)this);
        //Number of Sides
        SidesBox = new JComboBox<Integer>(SideNum);
        SidesBox.setSelectedItem(0);
        SidesBox.addActionListener((ActionListener)this);
        //Number of Rolls per Turn
        RollsBox = new JComboBox<Integer>(RollNum);
        RollsBox.setSelectedItem(0);
        RollsBox.addActionListener((ActionListener)this);

    //Dropdown Panel
        //Confirm Config Button
        confirmConfigButton = new JButton();
        confirmConfigButton.setText("Confirm Configuration and Start Game");
        confirmConfigButton.addActionListener((ActionListener)this);
        
        //add JCombo Boxes to JPanel, then JPanel to JFrame
        //Boxes-->Panel
        dropBoxPanel.add(DiceBox);
        dropBoxPanel.add(SidesBox);
        dropBoxPanel.add(RollsBox);
        dropBoxPanel.add(confirmConfigButton);
        //Panel-->Frame
        startFrame.add(dropBoxPanel);


    //components for main game

        //frame for the main game
        frame = new JFrame();
        frame.setTitle("Let's Play Some Yahtzee!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        // frame.setLocationRelativeTo(null); 
            //use this if you want the frame centered in the screen
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.setSize(1500, 600);
        //create panels to place onto the frame
        //Top panel
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setBounds(300,20, 1150, 200);

        //bottom panel
        diePanel = new JPanel();
        diePanel.setBackground(Color.lightGray);
        diePanel.setBounds(300,250, 1150, 200);

        //tempscore panel
        tempScorePanel = new JPanel();
        tempScorePanel.setBackground(Color.lightGray);
        tempScorePanel.setBounds(50,20, 225, 505);

        //Making tempScore buttons done inside confirmConfig ActionListener

        //checkbox panel
        checkboxPanel = new JPanel();
        checkboxPanel.setBackground(Color.lightGray);
        checkboxPanel.setBounds(300,475, 1200, 50);

        //checkboxes for checkbox panel
        keepCheckBox1 = new JCheckBox("Keep Die 1");
        keepCheckBox2 = new JCheckBox("Keep Die 2");
        keepCheckBox3 = new JCheckBox("Keep Die 3");
        keepCheckBox4 = new JCheckBox("Keep Die 4");
        keepCheckBox5 = new JCheckBox("Keep Die 5");
        keepCheckBox6 = new JCheckBox("Keep Die 6");
        keepCheckBox7 = new JCheckBox("Keep Die 7");
        checkboxPanel.add(keepCheckBox1);
        checkboxPanel.add(keepCheckBox2);
        checkboxPanel.add(keepCheckBox3);
        checkboxPanel.add(keepCheckBox4);
        checkboxPanel.add(keepCheckBox5);
        checkboxPanel.add(keepCheckBox6);
        checkboxPanel.add(keepCheckBox7);

        //Buttons on top panel
        officialScorecardButton = new JButton();
        officialScorecardButton.setText("--View Scorecard--");
        officialScorecardButton.setBounds(330,40,200,100);
        officialScorecardButton.setPreferredSize(new Dimension(350,185));
        officialScorecardButton.addActionListener((ActionListener)this);
        buttonPanel.add(officialScorecardButton);

        rollDice = new JButton();
        rollDice.setText("--Roll Dice--");
        rollDice.setBounds(330,40,200,100);
        rollDice.setPreferredSize(new Dimension(350,185));
        rollDice.addActionListener((ActionListener)this);
        buttonPanel.add(rollDice);

        //Label for Number of Rolls left per turn
        rollsLeftLabel = new JLabel();
        rollsLeftLabel.setText("Rolls left: " + numRolls);
        rollsLeftLabel.setFont(new Font("Arial", Font.PLAIN, 72));
        buttonPanel.add(rollsLeftLabel);

        //Official Scorecard Frame
        officialScoreFrame = new JFrame();
        officialScoreFrame.setTitle("Official Score");
        officialScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        officialScoreFrame.setLayout(null);
        // frame.setLocationRelativeTo(null); 
            //use this if you want the frame centered in the screen
        officialScoreFrame.getContentPane().setBackground(Color.DARK_GRAY);
        officialScoreFrame.setSize(500, 700);
        //Panel for official scorecardc frame
        officialScorePanel = new JPanel();
        officialScorePanel.setBackground(Color.lightGray);
        officialScorePanel.setBounds(25,25, 425, 600);
        officialScoreFrame.add(officialScorePanel);

//Game Over Frame
        //frame
        gameOverFrame = new JFrame();
        gameOverFrame.setTitle("Game Over");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.getContentPane().setBackground(Color.DARK_GRAY);
        gameOverFrame.setSize(1500, 600);
        //Game over panel
        gameOverPanel = new JPanel();
        gameOverPanel.setBackground(Color.lightGray);
        gameOverPanel.setBounds(50,50, 1400, 300);
        //game over label
        gameOverLabel = new JLabel(); 
        gameOverLabel.setText("---GAME OVER---");
        gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 72));

        // final score label 
        finalScoreLabel = new JLabel();
        // // //temporary final score value
        finalScoreLabel.setText("Final Score: 0");
        finalScoreLabel.setFont(new Font("Arial", Font.PLAIN, 64));

        //Labels --> Frame
        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(finalScoreLabel);
        //Panel --> Frame
        gameOverFrame.add(gameOverPanel);

        



        //add panels to the frame at the end!
        frame.add(buttonPanel);
        frame.add(diePanel);
        frame.add(checkboxPanel);
        frame.add(tempScorePanel);

        // frame.setVisible(true);
        startFrame.setVisible(true);
        // officialScoreFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == DiceBox){
            System.out.println("Clicked Dice Config box " + DiceBox.getSelectedItem());
            // numDice = (Integer)DiceBox.getSelectedItem();
            setNumDice((Integer)DiceBox.getSelectedItem());
            // System.out.println(numDice);
        }
        if(e.getSource() == SidesBox){
            System.out.println("Clicked Sides Config box " + SidesBox.getSelectedItem());
            // numSides = (Integer)SidesBox.getSelectedItem();
            setNumSides((Integer)SidesBox.getSelectedItem());
            // System.out.println(numDice);
        }
        if(e.getSource() == RollsBox){
            System.out.println("Clicked Rolls Config box " + RollsBox.getSelectedItem());
            // numRolls = (Integer)SidesBox.getSelectedItem();
            setNumRolls((Integer)RollsBox.getSelectedItem());
            // System.out.println(numDice);
        }
        if(e.getSource() == confirmConfigButton){
            System.out.println("Clicked confirm Configuration Button");
            System.out.println("Starting Game!");
            //Close Start Frame
            startFrame.dispose();
            //Initialize Arrays needed for program
            score.setValues(getNumDice(), getNumSides());
            hand = new int[getNumDice()];
            tempScore = new int[7+getNumSides()];
            officialScore = new int[7+getNumSides()];

            //Keep Dice CheckBoxes
            // keepCheckBoxes = new ArrayList<JCheckBox>(numDice);
            // for(int i = 0; i < numDice; i++){
            //     JCheckBox checkBox = new JCheckBox("Keep Die " + (i+1));
            //     checkBox.addActionListener(this);
            //     keepCheckBoxes.add(checkBox);
            //     checkboxPanel.add(keepCheckBoxes.get(i));
            // }

            //Temp Score Buttons
            //Set values of officialScore all to -1 to start (-1 will mean no score for this scorecard line)
            for(int i = 0; i < officialScore.length; i++){
                officialScore[i] = -1;
            }

            tempScoreButtons = new ArrayList<JButton>(numSides+7);
            for(int i = 0; i < numSides; i++){
                JButton scoreButton = new JButton("Score 0 on the " + (i+1) + " line");
                scoreButton.addActionListener(this);
                tempScoreButtons.add(scoreButton);
                tempScorePanel.add(tempScoreButtons.get(i));
            }

            for(int i = numSides; i < numSides+7; i++){
                if(i == numSides){
                    JButton scoreButton = new JButton("Score 0 on the 3K line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+1){
                    JButton scoreButton = new JButton("Score 0 on the 4K line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+2){
                    JButton scoreButton = new JButton("Score 0 on the Full House line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+3){
                    JButton scoreButton = new JButton("Score 0 on the Small Straight line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+4){
                    JButton scoreButton = new JButton("Score 0 on the Large Straight line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+5){
                    JButton scoreButton = new JButton("Score 0 on the Yahtzee line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
                if(i == numSides+6){
                    JButton scoreButton = new JButton("Score 0 on the Chance line");
                    scoreButton.addActionListener(this);
                    tempScoreButtons.add(scoreButton);
                    tempScorePanel.add(tempScoreButtons.get(i));
                }
            }
            frame.setVisible(true);
        }
        if(e.getSource() == rollDice){
            // hand = new int[getNumDice()];
            // tempScore = new int[13+getNumSides()];
            System.out.println("Clicked roll Dice button");
            // numRolls--;
            // System.out.println("Rolls left in this turn: " + numRolls);
            diePanel.removeAll();
            if(numRolls != 0){
                numRolls--;
                rollsLeftLabel.setText("Rolls left:" + numRolls);
                hand = die.playerRoll(frame, diePanel, getNumDice(), getNumSides(), hand, GUIYahtzee.this);
                //TEST FOR HAND IN GUIYAHTZEE
                // System.out.println("GUI Hand Array:");
                // for(int i = 0; i < numDice; i++){
                //     System.out.println(hand[i]);
                // }

                //change temp score array
                tempScore = score.updateTempScore(hand, tempScore, officialScore, numDice, numSides, tempScorePanel, frame);
            }
            //now update temp scores in tempScorePanel
            // 1 through NumSides Dice Buttons
            for(int i = 0; i < numSides; i++){
                tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the " + (i+1) + " Line" );
            }
            //Lower Scorecard Buttons
            for(int i = numSides; i < numSides+7; i++){
                if(i == numSides){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the 3K Line");
                }
                if(i == numSides +1){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the 4K Line");
                }
                if(i == numSides+2){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the Full House Line");
                }
                if(i == numSides+3){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the Small Straight Line");
                }
                if(i == numSides+4){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the Large Straight Line");
                }
                if(i == numSides+5){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the Yahtzee Line");
                }
                if(i == numSides+6){
                    tempScoreButtons.get(i).setText("Score " + tempScore[i] + " on the Chance Line");
                }
            }   
        }

        for(int i = 0; i < numSides+7; i++){
            if(e.getSource() == tempScoreButtons.get(i)){
                tempScoreButtons.get(i).setEnabled(false); 
                for(int j = 0; j < numSides+7; j++){
                    finalOfficialScore += officialScore[j];
                }
                System.out.println("Current final official Score: " + finalOfficialScore);
                //set all tempScores = 0
                // tempScore[i] = 0;
                // tempScoreButtons.get(i).setText("");
                officialScore[i] = tempScore[i];
                playerTurn++;
                numRolls = 3;
                rollsLeftLabel.setText("Rolls left: " + numRolls);
                //Reset all selected Dice
                keepCheckBox1.setSelected(false);
                keepCheckBox2.setSelected(false);
                keepCheckBox3.setSelected(false);
                keepCheckBox4.setSelected(false);
                keepCheckBox5.setSelected(false);
                keepCheckBox6.setSelected(false);
                keepCheckBox7.setSelected(false);

                if(playerTurn == numDice+8){
                    frame.dispose();
                    System.out.println("Game Over!");
                    // gameOverFrame.setVisible(true);
                    // for(int j = 0; j < numSides+7; i++){
                    //     finalOfficialScore += officialScore[j];
                    // }
                    System.out.println("Final Score: " + finalOfficialScore);
                    // finalScoreLabel = new JLabel();
                    finalScoreLabel.setText("Final Score: " + finalOfficialScore);
                    // gameOverPanel.add(finalScoreLabel);
                    // gameOverFrame.add(gameOverPanel);
                    gameOverFrame.setVisible(true);
                }
            }
            finalOfficialScore = 0;
        }

        if(e.getSource() == officialScorecardButton){
            System.out.println("Clicked View Scorecard Button");
            score.printScoreCard(officialScore, officialScoreFrame, officialScorePanel, getNumDice(), getNumSides());
        }
    }

    //Getters Functions
    //NumDice
    public void setNumDice(int numDice){
        this.numDice = numDice;
    }
    public int getNumDice(){
        return this.numDice;
    }

    //NumSides
    public void setNumSides(int numSides){
        this.numSides = numSides;
    }
    public int getNumSides(){
        return this.numSides;
    }

    //NumRolls
    public void setNumRolls(int numRolls){
        this.numRolls = numRolls;
    }
    public int getNumRolls(){
        return this.numRolls;
    }
}
