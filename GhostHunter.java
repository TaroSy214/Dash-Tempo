/*
Sophia Yan
14 April 2023
GhostHunter.java
game project end of year, rhythm game
 */

//WHEN RUNNING PROGRAM, AFTER COMPILING THE FILE USING javac GhostHunter.java, IN THE TERMINAL,
//ENTER: java -splash:loadingBg.gif GhostHunter
//(INSTEAD OF java GhostHunter)
//in order for the splash screen to work

//graphics

import java.awt.*; //contains painting graphics and components
import java.awt.event.*; //deals with event listeners, fired by components
import javax.swing.*; //awt and swing are needed to call an app's main method, notifies all listeners
import javax.swing.border.Border; //border component class import
import javax.swing.border.LineBorder; //lined border component class import
import java.awt.CardLayout; //card layout class import
import javax.swing.JButton; //JButton class import
import javax.swing.JLabel; //JLabel class import
import java.awt.Graphics; //Graphic methods
import java.awt.Dimension; //gets width and height of an object
import java.awt.Graphics2D; //graphics method in 2D
import java.awt.event.ActionEvent; //action event methods
import java.awt.event.ActionListener; //listens to actions
import java.awt.event.KeyEvent; //key event methods
import javax.swing.AbstractAction; //listens to abstract actions, get, set methods
import javax.swing.ActionMap; //locates an action when a key is pressed
import javax.swing.InputMap; //looks for the key pressed
import javax.swing.JFrame; //JFrame class and methods
import javax.swing.JPanel; //JPanel class and methods
import javax.swing.KeyStroke; //key action on keyboard, modifier keys
import javax.swing.Timer; //timer class

//audio
import java.io.File; //File import to search for a file based on directory pathname
import java.util.HashMap; //java's class of HashMap, where you can assign values to objects
import java.util.Iterator; //allows the removal of elements
import java.util.Map; //maps keys to values
import javax.sound.sampled.AudioInputStream; //allows to obtain audio input stream from an external file
import javax.sound.sampled.AudioSystem; //lets you query and access mixers installed on the system, convert audio files
import javax.sound.sampled.Clip; //audio is loaded into a clip, plays the audio and other methods related with audio


public class GhostHunter extends JFrame { //GhostHunter, class with JFrame of my program

    public GhostHunter() { //constructor of GhostHunter class
        super("GhostHunter"); //calls JFrame constructor, adds title to window


    }

    public void reset() //resetting everything after player is done with a level
    {
        Panel_ghost panGhost = new Panel_ghost(this); //making object panel
        panGhost.runIt();
        //setting sizes and panels
        this.setSize(1350, 730); //sets size of window
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //what to do when user hits clos
        this.setLocation(0, 0); //set the left top point that you want to create component at
        this.setResizable(false); //if user will be able to resize the frame if they want to later
        this.setLayout(null); //the JFrame layout is null
        this.setContentPane(panGhost); //Swing's painting components set in the object pan (from panel)
        this.pack(); //window to be sized in preferred size and layouts of its components
        this.setLocationRelativeTo(null); //location is relative to nothing
        this.setVisible(true); //set the graphics visible

    }

    public static void main(String[] args) { //main method

        SplashDemo test = new SplashDemo(); //splash screen before ghost hunter panel
        GhostHunter gh = new GhostHunter(); //program class instance


        Panel_ghost panGhost = new Panel_ghost(gh); //making object panel
        panGhost.runIt(); //runs Ghost panel
        //setting sizes and panels
        gh.setSize(1350, 730); //sets size of window
        gh.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //what to do when user hits clos
        gh.setLocation(0, 0); //set the left top point that you want to create component at
        gh.setResizable(false); //if user will be able to resize the frame if they want to later
        gh.setLayout(null); //the JFrame layout is null
        gh.setContentPane(panGhost); //Swing's painting components set in the object pan (from panel)
        gh.pack(); //window to be sized in preferred size and layouts of its components
        gh.setLocationRelativeTo(null); //location is relative to nothing
        gh.setVisible(true); //set the graphics visible

    }
}

class Panel_ghost extends JPanel { //class with all my graphics and gameplay

    static String delete = ""; //for my personal laptop

    static int score = 0;

    //all panels
    JPanel allPanels;

    //main screen panel
    JPanel mainBgPan;
    JLabel mainBgLabel;
    ImageIcon mainBgIcon;
    String mainBgFile = delete + "title_page.gif"; //file of the main screen background

    //for all main screen buttons
    JButton playBtn;
    JButton tutorialBtn;
    JButton settingsBtn;
    JButton instructionsBtn;
    //for main screen buttons
    ImageIcon playBtnIcon;
    ImageIcon tutorialBtnIcon;
    ImageIcon settingsBtnIcon;
    ImageIcon instructionsBtnIcon;
    String playBtnFile = delete + "playBtn.png";
    String tutorialBtnFile = delete + "tutorialBtn.png";
    String settingsBtnFile = delete + "settingsBtn.png";
    String instructionsBtnFile = delete + "instructionsBtn.png";


    //for all back buttons
    Border backBtnBorder;
    JButton tutorialBackBtn;
    JButton settingsBackBtn;
    JButton instructionsBackBtn;

    //play panel
    JPanel playPan;

    JButton levelOneBtn;
    JButton levelTwoBtn;
    JButton levelThreeBtn;
    JLabel levelsBgLabel;
    ImageIcon levelsBgIcon;
    ImageIcon levelScreenOneIcon;
    ImageIcon levelScreenTwoIcon;
    ImageIcon levelScreenThreeIcon;
    String levelsBgFile = delete + "levelsBg.png";
    String levelScreenOneFile = delete + "levelScreenOne.png";
    String levelScreenTwoFile = delete + "levelScreenTwo.png";
    String levelScreenThreeFile = delete + "levelScreenThree.png";
    //level one panel
    JPanel levelOnePan;
    LevelOneLabel levelOneBgLabel;
    String liveScreenFile = delete + "liveScreen.gif";
    ImageIcon liveScreenImage;
    String deathScreenFile = delete + "deathScreen.gif";
    ImageIcon deathScreenImage;
    JLabel deathScreenLabel;
    JLabel liveScreenLabel;
    JPanel deathScreenPan;
    JPanel liveScreenPan;
    String deathReturnBtnFile = delete + "gameOverReturnBtn.png";
    ImageIcon deathReturnBtnImage;
    JButton deathReturnBtn;
    String liveReturnBtnFile = delete + "gameOverReturnBtn.png";
    ImageIcon liveReturnBtnImage;
    JButton liveReturnBtn;

    //level two panel
    JPanel levelTwoPan;
    LevelTwoLabel levelTwoBgLabel;

    //level three panel
    JPanel levelThreePan;
    LevelThreeLabel levelThreeBgLabel;

    //tutorial panel
    JPanel tutorialPan;
    JLabel tutorialBgLabel;
    ImageIcon tutorialBgIcon;
    String tutorialBgFile = delete + "tutorialBg.gif";
    CardLayout tutorialCl;
    JPanel tutorialCardLayout;
    JPanel tutorialLevelOnePan;
    String tutorialLevelOneBgFile = delete + "tutorialLevelOneBg.png";
    ImageIcon tutorialLevelOneBgIcon;
    JLabel tutorialLevelOneBgLabel;
    String tutorialLevelOneJumpFile = delete + "tutorialLevelOneJump.gif";
    ImageIcon tutorialLevelOneJumpIcon;
    JLabel tutorialLevelOneJumpLabel;
    String tutorialLevelOneShootFile = delete + "tutorialLevelOneShoot.gif";
    ImageIcon tutorialLevelOneShootIcon;
    JLabel tutorialLevelOneShootLabel;
    JPanel tutorialLevelTwoPan;
    JPanel tutorialLevelThreePan;
    String tutorialPowerUp1BgFile = delete + "tutorialPowerUp1Bg.png";
    ImageIcon tutorialPowerUp1BgIcon;
    JLabel tutorialPowerUp1BgLabel;
    String tutorialHealFile = delete + "tutorialHeal.gif";
    ImageIcon tutorialHealIcon;
    JLabel tutorialHealLabel;
    String tutorialAttackFile = delete + "tutorialAttack.gif";
    ImageIcon tutorialAttackIcon;
    JLabel tutorialAttackLabel;
    String tutorialPowerUp2BgFile = delete + "tutorialPowerUp2Bg.png";
    ImageIcon tutorialPowerUp2BgIcon;
    JLabel tutorialPowerUp2BgLabel;
    String tutorialShieldFile = delete + "tutorialShield.gif";
    ImageIcon tutorialShieldIcon;
    JLabel tutorialShieldLabel;
    String tutorialBoardFile = delete + "tutorialBoard.gif";
    ImageIcon tutorialBoardIcon;
    JLabel tutorialBoardLabel;
    String tutorialLevelChangeBtnFile = delete + "tutorialLevelChangeBtn.png";
    ImageIcon tutorialLevelChangeBtnIcon;
    JButton tutorialLevelChangeBtn;

    //settings panel
    JPanel settingsPan;
    JLabel settingsBgLabel;
    ImageIcon settingsBgIcon;
    String settingsBgFile = delete + "settingsBg.gif";
    ImageIcon buttonSoundOnIcon;
    String buttonSoundOnFile = delete + "buttonSoundOn.png";
    ImageIcon buttonSoundOffIcon;
    String buttonSoundOffFile = delete + "buttonSoundOff.png";
    ImageIcon trackSoundOffIcon;
    String trackSoundOffFile = delete + "trackSoundOff.png";
    ImageIcon trackSoundOnIcon;
    String trackSoundOnFile = delete + "trackSoundOn.png";
    JToggleButton buttonSoundTogBtn;
    JToggleButton trackSoundTogBtn;
    Border togBtnBorder;

    //instructions panel
    JPanel instructionsPan;
    JLabel instructionsBgLabel;
    ImageIcon instructionsBgIcon;
    String instructionsBgFile = delete + "instructionsBg.gif";
    JPanel instructionsTextPanel;
    JLabel instructionsTextLabel;
    ImageIcon instructionsTextIcon;
    String instructionsTextFile = delete + "instructionsText.gif";
    boolean pressed;

    //stars
    static boolean died = false;
    //music
    String mainMusicFile = delete + "mainMusic.wav"; //file of the main screen audio
    String buttonClickFile = delete + "buttonClick.wav"; //file of all button click sounds
    String levelOneMusicFile = delete + "levelOneMusic.wav";
    String levelTwoMusicFile = delete + "levelTwoMusic.wav";
    String levelThreeMusicFile = delete + "levelThreeMusic.wav";
    //music
    static File musicPath;
    Clip bgmClip;
    static Clip buttonClip;
    Clip levelOneClip;
    Clip levelTwoClip;
    Clip levelThreeClip;
    boolean trackSoundOn = true;
    boolean buttonSoundOn = true;
    boolean levelOneSoundOn = false;
    boolean levelTwoSoundOn = false;
    boolean levelThreeSoundOn = false;
    GhostHunter ghostHunter;

    CardLayout cl; //cardlayout

    public Panel_ghost(GhostHunter gh) { //constructor of panel, adds listeners and timers
        ghostHunter = gh;
        pressed = false;
        this.setDoubleBuffered(true); //making panels glitch less
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //paints

    }

    public void runIt() {
        //initializing components


        //all panels is a card layout
        cl = new CardLayout(); //creates a cardlayout
        allPanels = new JPanel(); //creates a JPanel called allPanels
        allPanels.setLayout(cl); //set allPanels to a cardlayout

        //title screen

        mainBgPan = new JPanel(null); //sets the main screen layout as a nullLayout
        mainBgPan.setPreferredSize(new Dimension(1350, 730)); //dimensions of the main screen
        //title screen background
        mainBgIcon = new ImageIcon(mainBgFile); //image of main screen background
        mainBgLabel = new JLabel(mainBgIcon); //image of main screen background placed inside JLabel
        mainBgLabel.setBounds(0, 0, 1350, 730); //sets the location of main screen background
        //buttons
        //play button
        playBtnIcon = new ImageIcon(playBtnFile); //image
        playBtn = new JButton(playBtnIcon); //play button object
        playBtn.setBounds(490, 300, 390, 75); //sets position and width height for button
        //tutorial button
        tutorialBtnIcon = new ImageIcon(tutorialBtnFile); //image
        tutorialBtn = new JButton(tutorialBtnIcon); //tutorial button object
        tutorialBtn.setBounds(490, 400, 390, 75); //sets position and width height for button
        //settings button
        settingsBtnIcon = new ImageIcon(settingsBtnFile); //image
        settingsBtn = new JButton(settingsBtnIcon); //settings button object
        settingsBtn.setBounds(490, 500, 390, 75); //sets position and width height for button
        //instructions button
        instructionsBtnIcon = new ImageIcon(instructionsBtnFile); //image
        instructionsBtn = new JButton(instructionsBtnIcon); //instructions button object
        instructionsBtn.setBounds(490, 600, 390, 75); //sets position and width height for button

        //common for all panels

        //back buttons
        backBtnBorder = new LineBorder(new Color(114, 128, 133), 3); //button border properties
        //play
        //tutorial
        tutorialBackBtn = new JButton("BACK"); //button object and text
        backBtnProperties(tutorialBackBtn); //sets properties for back buttons
        //settings
        settingsBackBtn = new JButton("BACK"); //button object and text
        backBtnProperties(settingsBackBtn); //sets properties for back buttons
        //instructions
        instructionsBackBtn = new JButton("BACK"); //button object and text
        backBtnProperties(instructionsBackBtn); //sets properties for back buttons

        //play screen
        playPan = new JPanel(null); //sets play JPanel as a null layout
        playPan.setPreferredSize(new Dimension(1350, 730)); //sets play JPanel dimensions
        //levels
        levelsBgIcon = new ImageIcon(levelsBgFile); //icon of levels page
        levelsBgLabel = new JLabel(levelsBgIcon); //JLabel of levels page
        levelsBgLabel.setPreferredSize(new Dimension(1350, 730)); //size of JLabel level background
        levelsBgLabel.setBounds(0, 0, 1350, 730); //position of JLabel level background
        levelScreenOneIcon = new ImageIcon(levelScreenOneFile); //icon of levels page
        levelOneBtn = new JButton(levelScreenOneIcon); //button object and text
        levelOneBtn.setBounds(170, 270, 250, 200); //placement of level button
        levelScreenTwoIcon = new ImageIcon(levelScreenTwoFile); //icon of levels page
        levelTwoBtn = new JButton(levelScreenTwoIcon); //button object and text
        levelTwoBtn.setBounds(550, 270, 250, 200); //placement of level button
        levelScreenThreeIcon = new ImageIcon(levelScreenThreeFile); //icon of levels page
        levelThreeBtn = new JButton(levelScreenThreeIcon); //button object and text
        levelThreeBtn.setBounds(945, 270, 250, 200); //placement of level button
        //level one
        levelOnePan = new JPanel(null); //layout of level one panel is null
        levelOnePan.setPreferredSize(new Dimension(1350, 730)); //setting size of level one panel
        levelOneBgLabel = new LevelOneLabel(this); //redefine JLabel to be of level one label
        levelOneBgLabel.setPreferredSize(new Dimension(1350, 730)); //size of level one background
        levelOneBgLabel.setBounds(0, 0, 1350, 730); //position of level one background
        //setting details of death panel and live panel after user completes a level
        deathScreenPan = new JPanel(null);
        deathScreenPan.setPreferredSize(new Dimension(1350, 730));
        liveScreenPan = new JPanel(null);
        liveScreenPan.setPreferredSize(new Dimension(1350, 730));
        liveScreenImage = new ImageIcon(liveScreenFile);
        deathScreenImage = new ImageIcon(deathScreenFile);
        liveScreenLabel = new JLabel(liveScreenImage);
        deathScreenLabel = new JLabel(deathScreenImage);
        deathReturnBtnImage = new ImageIcon(deathReturnBtnFile);
        deathReturnBtn = new JButton(deathReturnBtnImage);
        deathReturnBtn.setPreferredSize(new Dimension(390, 100));
        deathReturnBtn.setBounds(500, 400, 390, 100);
        liveReturnBtnImage = new ImageIcon(liveReturnBtnFile);
        liveReturnBtn = new JButton(liveReturnBtnImage);
        liveReturnBtn.setPreferredSize(new Dimension(390, 100));
        liveReturnBtn.setBounds(500, 400, 390, 100);
        liveScreenLabel.setPreferredSize(new Dimension(1350, 730));
        liveScreenLabel.setBounds(0, 0, 1350, 730);
        deathScreenLabel.setPreferredSize(new Dimension(1350, 730));
        deathScreenLabel.setBounds(0, 0, 1350, 730);

        //level two
        levelTwoPan = new JPanel(null); //null layout for level two JPanel
        levelTwoPan.setPreferredSize(new Dimension(1350, 730)); //size of level two JPanel
        levelTwoBgLabel = new LevelTwoLabel(this); //redefine JLabel to be of level two label
        levelTwoBgLabel.setPreferredSize(new Dimension(1350, 730)); //size of level two background
        levelTwoBgLabel.setBounds(0, 0, 1350, 730); //position of level two background
        //level three
        levelThreePan = new JPanel(null); //null layout for level three JPanel
        levelThreePan.setPreferredSize(new Dimension(1350, 730)); //size of level three JPanel
        levelThreeBgLabel = new LevelThreeLabel(this); //redefine JLabel to be of level two label
        levelThreeBgLabel.setPreferredSize(new Dimension(1350, 730)); //size of level two background
        levelThreeBgLabel.setBounds(0, 0, 1350, 730); //position of level two background

        //tutorial screen
        tutorialPan = new JPanel(null); //initializes JPanel specifically for this screen, null layout
        tutorialPan.setPreferredSize(new Dimension(1350, 730)); //sets dimensions for this JPanel
        tutorialBgIcon = new ImageIcon(tutorialBgFile); //creates an image of the background file
        tutorialBgLabel = new JLabel(tutorialBgIcon); //puts the background file (gif) into a JLabel
        tutorialBgLabel.setBounds(0, 0, 1350, 730); //sets size of the JLabel
        tutorialCl = new CardLayout();
        tutorialCardLayout = new JPanel(); //creates a JPanel called allPanels
        tutorialCardLayout.setLayout(tutorialCl); //set allPanels to a cardlayout
        tutorialCardLayout.setBounds(85, 160, 1070, 428);
        //tutorial on jump and shoot
        tutorialLevelOnePan = new JPanel(null);
        tutorialLevelOnePan.setPreferredSize(new Dimension(1070, 428));
        tutorialLevelOnePan.setBounds(0, 0, 1070, 428);
        tutorialLevelOneBgIcon = new ImageIcon(tutorialLevelOneBgFile);
        tutorialLevelOneBgLabel = new JLabel(tutorialLevelOneBgIcon);
        tutorialLevelOneBgLabel.setBounds(0, 0, 1070, 428);
        tutorialLevelOneJumpIcon = new ImageIcon(tutorialLevelOneJumpFile);
        tutorialLevelOneJumpLabel = new JLabel(tutorialLevelOneJumpIcon);
        tutorialLevelOneJumpLabel.setBounds(164, 138, 245, 245);
        tutorialLevelOneShootIcon = new ImageIcon(tutorialLevelOneShootFile);
        tutorialLevelOneShootLabel = new JLabel(tutorialLevelOneShootIcon);
        tutorialLevelOneShootLabel.setBounds(638, 138, 245, 245);

        //tutorial on heal and attack power ups
        tutorialLevelTwoPan = new JPanel(null);
        tutorialLevelTwoPan.setPreferredSize(new Dimension(1070, 428));
        tutorialLevelTwoPan.setBounds(0, 0, 1070, 428);
        tutorialPowerUp1BgIcon = new ImageIcon(tutorialPowerUp1BgFile);
        tutorialPowerUp1BgLabel = new JLabel(tutorialPowerUp1BgIcon);
        tutorialPowerUp1BgLabel.setBounds(0, 0, 1070, 428);
        tutorialHealIcon = new ImageIcon(tutorialHealFile);
        tutorialHealLabel = new JLabel(tutorialHealIcon);
        tutorialHealLabel.setBounds(164, 138, 245, 245);
        tutorialAttackIcon = new ImageIcon(tutorialAttackFile);
        tutorialAttackLabel = new JLabel(tutorialAttackIcon);
        tutorialAttackLabel.setBounds(638, 138, 245, 245);
        
        //tutorial on shield and jump board
        tutorialLevelThreePan = new JPanel(null);
        tutorialLevelThreePan.setPreferredSize(new Dimension(1070, 428));
        tutorialLevelThreePan.setBounds(0, 0, 1070, 428);
        tutorialPowerUp2BgIcon = new ImageIcon(tutorialPowerUp2BgFile);
        tutorialPowerUp2BgLabel = new JLabel(tutorialPowerUp2BgIcon);
        tutorialPowerUp2BgLabel.setBounds(0, 0, 1070, 428);
        tutorialShieldIcon = new ImageIcon(tutorialShieldFile);
        tutorialShieldLabel = new JLabel(tutorialShieldIcon);
        tutorialShieldLabel.setBounds(164, 138, 245, 245);
        tutorialBoardIcon = new ImageIcon(tutorialBoardFile);
        tutorialBoardLabel = new JLabel(tutorialBoardIcon);
        tutorialBoardLabel.setBounds(638, 138, 245, 245);
        
        //tutorial change btn
        tutorialLevelChangeBtnIcon = new ImageIcon(tutorialLevelChangeBtnFile);
        tutorialLevelChangeBtn = new JButton(tutorialLevelChangeBtnIcon);
        tutorialLevelChangeBtn.setBounds(1164, 311, 148, 157);
        tutorialLevelChangeBtn.setBackground(null);
        tutorialLevelChangeBtn.setOpaque(false);
        tutorialLevelChangeBtn.setBorderPainted(false);
        tutorialLevelChangeBtn.setContentAreaFilled(false);
        tutorialLevelChangeBtn.setFocusPainted(false);

        //settings screen
        settingsPan = new JPanel(null); //initializes JPanel specifically for this screen, null layout
        settingsPan.setPreferredSize(new Dimension(1350, 730)); //sets dimensions for this JPanel
        //toggle buttons
        togBtnBorder = new LineBorder(new Color(114, 140, 140), 2); //border for all toggle buttons
        buttonSoundOnIcon = new ImageIcon(buttonSoundOnFile); //button sound on imgicon
        buttonSoundOffIcon = new ImageIcon(buttonSoundOffFile); //button sound off imgicon
        buttonSoundTogBtn = new JToggleButton(buttonSoundOnIcon, true); //set selected as true, display button sound on image
        buttonSoundTogBtn.setPreferredSize(new Dimension(600, 123)); //set size of button sound toggle button
        buttonSoundTogBtn.setBounds(390, 215, 600, 123); //set location of button sound toggle button
        buttonSoundTogBtn.setBorder(togBtnBorder); //set border of button sound toggle button
        trackSoundOnIcon = new ImageIcon(trackSoundOnFile); //track sound on image icon
        trackSoundOffIcon = new ImageIcon(trackSoundOffFile); //track sound off image icon
        trackSoundTogBtn = new JToggleButton(trackSoundOnIcon, true); //set selected as true, display track sound on image
        trackSoundTogBtn.setPreferredSize(new Dimension(600, 123)); //set size of track sound toggle button
        trackSoundTogBtn.setBounds(390, 415, 600, 123); //set location of track sound toggle button
        trackSoundTogBtn.setBorder(togBtnBorder); //set border of track sound toggle button

        //settings screen
        settingsBgIcon = new ImageIcon(settingsBgFile);//creates an image of the background file
        settingsBgLabel = new JLabel(settingsBgIcon);//puts the background file (gif) into a JLabel
        settingsBgLabel.setBounds(0, 0, 1350, 730); //sets size of the JLabel

        //instructions screen
        instructionsPan = new JPanel(null); //initializes JPanel specifically for this screen, null layout
        instructionsPan.setPreferredSize(new Dimension(1350, 730)); //sets dimensions for this JPanel
        instructionsBgIcon = new ImageIcon(instructionsBgFile);//creates an image of the background file
        instructionsBgLabel = new JLabel(instructionsBgIcon);//puts the background file (gif) into a JLabel
        instructionsBgLabel.setBounds(0, 0, 1350, 730); //sets size of the JLabel
        //instructions
        //sets border properties of instructions text panel
        instructionsTextIcon = new ImageIcon(instructionsTextFile); //image icon of instructions
        instructionsTextLabel = new JLabel(instructionsTextIcon);//makes instructions text the new designed JLabel
        instructionsTextPanel = new JPanel(null); //new null layout JPanel for instructions text
        instructionsTextLabel.setBounds(0, 0, 1245, 530); //sets position&size of instructions text
        //sets border properties of instructions text label
        instructionsTextLabel.setBorder(BorderFactory.createLineBorder(new Color(114, 128, 133), 1));
        //sets position of instructions text panel
        instructionsTextPanel.setBounds(52, 160, 1245, 445);

        //adding components to layouts

        //adding components to play page
        playPan.add(levelOneBtn);
        playPan.add(levelTwoBtn);
        playPan.add(levelThreeBtn);
        playPan.add(levelsBgLabel);
        //adding components to level one page
        levelOnePan.add(levelOneBgLabel);

        deathScreenPan.add(deathReturnBtn);
        deathScreenPan.add(deathScreenLabel);
        liveScreenPan.add(liveReturnBtn);
        liveScreenPan.add(liveScreenLabel);
        //adding components to level two page;
        levelTwoPan.add(levelTwoBgLabel);
        //adding components to level three page
        levelThreePan.add(levelThreeBgLabel);

        //adding components to tutorial page
        tutorialPan.add(tutorialBackBtn);
        //tutorial level one
        tutorialLevelOnePan.add(tutorialLevelOneJumpLabel);
        tutorialLevelOnePan.add(tutorialLevelOneShootLabel);
        tutorialLevelOnePan.add(tutorialLevelOneBgLabel);
        //tutorial power up 1
        tutorialLevelTwoPan.add(tutorialHealLabel);
        tutorialLevelTwoPan.add(tutorialAttackLabel);
        tutorialLevelTwoPan.add(tutorialPowerUp1BgLabel);
        //tutorial power up 2
        tutorialLevelThreePan.add(tutorialShieldLabel);
        tutorialLevelThreePan.add(tutorialBoardLabel);
        tutorialLevelThreePan.add(tutorialPowerUp2BgLabel);
        //adding main tutorial level screens
        tutorialCardLayout.add(tutorialLevelOnePan, "one");
        tutorialCardLayout.add(tutorialLevelTwoPan, "two");
        tutorialCardLayout.add(tutorialLevelThreePan, "three");
        //adding card layout to tutorial
        tutorialPan.add(tutorialCardLayout);
        tutorialPan.add(tutorialLevelChangeBtn);
        tutorialPan.add(tutorialBgLabel);

        //adding components to settings page
        settingsPan.add(settingsBackBtn);
        settingsPan.add(buttonSoundTogBtn);
        settingsPan.add(trackSoundTogBtn);
        settingsPan.add(settingsBgLabel);

        //adding components to instructions page
        instructionsPan.add(instructionsBackBtn);
        //adding instructions
        instructionsTextPanel.add(instructionsTextLabel);
        instructionsPan.add(instructionsTextPanel);
        //adding instructions background
        instructionsPan.add(instructionsBgLabel);

        //adding components to main screen
        mainBgPan.add(playBtn);
        mainBgPan.add(tutorialBtn);
        mainBgPan.add(settingsBtn);
        mainBgPan.add(instructionsBtn);
        mainBgPan.add(mainBgLabel); //adding main background button to main screen

        //adding all panels to the card layout, naming each panel
        allPanels.add(mainBgPan, "home");
        allPanels.add(playPan, "play");
        allPanels.add(tutorialPan, "tutorial");
        allPanels.add(settingsPan, "settings");
        allPanels.add(instructionsPan, "instructions");
        allPanels.add(levelOnePan, "level one");
        allPanels.add(levelTwoPan, "level two");
        allPanels.add(levelThreePan, "level three");
        allPanels.add(deathScreenPan, "death");
        allPanels.add(liveScreenPan, "live");

        //adding all the panels to the JFrame
        add(allPanels);

        //creates new audio bgm clips so that only one is created each time, it will not overlap
        try {
            bgmClip = AudioSystem.getClip();
            buttonClip = AudioSystem.getClip();
            levelOneClip = AudioSystem.getClip();
            levelTwoClip = AudioSystem.getClip();
            levelThreeClip = AudioSystem.getClip();
        } catch (Exception e) {

        }

        playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //audio of main screen

        //sound toggle buttons
        buttonSoundTogBtn.addActionListener(e -> { //gives button sound toggle button a listener
            if (buttonSoundTogBtn.isSelected()) { //if button sound toggle button is clicked
                buttonSoundTogBtn.setIcon(buttonSoundOnIcon); //set the sound icon as on
                buttonSoundOn = true;
            } else {
                buttonSoundTogBtn.setIcon(buttonSoundOffIcon); //set the sound icon as off
                buttonSoundOn = false;
            }
        });
        trackSoundTogBtn.addActionListener(e -> { //gives track sound toggle button a listener
            if (trackSoundTogBtn.isSelected()) { //if track sound toggle button is clicked
                trackSoundTogBtn.setIcon(trackSoundOnIcon); //set the track icon as on
                trackSoundOn = true;
            } else {
                trackSoundTogBtn.setIcon(trackSoundOffIcon); //set the track icon as off
                trackSoundOn = false;
            }
            playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //audio of main screen
        });


        //giving each button a listener
        tutorialLevelChangeBtn.addActionListener(e -> {
            tutorialCl.next(tutorialCardLayout);
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true);
        });
        playBtn.addActionListener(e -> {
            cl.show(allPanels, "play"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            trackSoundOn = false;
            playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //calls method to stop bgm
        });
        tutorialBtn.addActionListener(e -> {
            cl.show(allPanels, "tutorial"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound

        });
        settingsBtn.addActionListener(e -> {
            cl.show(allPanels, "settings"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound

        });
        instructionsBtn.addActionListener(e -> {
            cl.show(allPanels, "instructions"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound

        });
        tutorialBackBtn.addActionListener(e -> {
            cl.show(allPanels, "home"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            trackSoundOn = true;
            playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //calls method to play bgm

        });
        settingsBackBtn.addActionListener(e -> {
            cl.show(allPanels, "home"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            trackSoundOn = true;
            playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //calls method to play bgm
        });
        instructionsBackBtn.addActionListener(e -> {
            cl.show(allPanels, "home"); //shows the specific panel from the cardlayout of allPanels
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            trackSoundOn = true;
            playMusic(mainMusicFile, bgmClip, trackSoundOn, true, false); //calls method to play bgm
        });
        levelOneBtn.addActionListener(e -> {
            cl.show(allPanels, "level one"); //shows the specific panel from the cardlayout of allPanels
            levelOneBgLabel.startTimer(); //starts the timer for all level one component timers
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            levelOneSoundOn = true;
            playMusic(levelOneMusicFile, levelOneClip, levelOneSoundOn, false, false);

        });
        levelTwoBtn.addActionListener(e -> {
            cl.show(allPanels, "level two"); //shows the specific panel from the cardlayout of allPanels
            levelTwoBgLabel.startTimer();
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            levelTwoSoundOn = true;
            playMusic(levelTwoMusicFile, levelTwoClip, levelTwoSoundOn, false, false);
        });
        levelThreeBtn.addActionListener(e -> {
            cl.show(allPanels, "level three"); //shows the specific panel from the cardlayout of allPanels
            levelThreeBgLabel.startTimer();
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true); //calls method to play button sound
            levelThreeSoundOn = true;
            playMusic(levelThreeMusicFile, levelThreeClip, levelThreeSoundOn, false, false);

        });
        //return buttons from "death" or "live" page, goes back to the main screen and stops level music
        deathReturnBtn.addActionListener(e -> {
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true);
            levelOneSoundOn = false;
            levelTwoSoundOn = false;
            levelThreeSoundOn = false;
            playMusic(levelOneMusicFile, levelOneClip, levelOneSoundOn, false, false);
            playMusic(levelTwoMusicFile, levelTwoClip, levelTwoSoundOn, false, false);
            playMusic(levelThreeMusicFile, levelThreeClip, levelThreeSoundOn, false, false);
            listenForDeathSignal("die");
        });
        liveReturnBtn.addActionListener(e -> {
            playMusic(buttonClickFile, buttonClip, buttonSoundOn, false, true);
            levelOneSoundOn = false;
            levelTwoSoundOn = false;
            levelThreeSoundOn = false;
            playMusic(levelOneMusicFile, levelOneClip, levelOneSoundOn, false, false);
            playMusic(levelTwoMusicFile, levelTwoClip, levelTwoSoundOn, false, false);
            playMusic(levelThreeMusicFile, levelThreeClip, levelThreeSoundOn, false, false);
            listenForDeathSignal("die");
        });

    }

    //when there is a "signal" from the other class saying that the game is over, reset the program
    public void listenForDeathSignal(String message) {
        score = 0;
        died = false;
        ghostHunter.reset();
    }

    public static void playSoundEffect(String filePath){ //plays sound effects
        try {
            File file = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            //if (!clip.isOpen()) { //if the clip is not open yet
                clip.open(audioInputStream); //open the clip to play the music
            //}
            clip.start(); //tells the audio to start
            //System.out.print("playSoundEffect");
            //clip.stop(); //stop the clip
        } catch (Exception e)
        {

        }
    }

    public static void playMusic(String filePath, Clip clip, boolean on, boolean isBgm, boolean isButton) { //plays the music file indicated
        try {
            musicPath = new File(filePath); //creates a file of the audio
            if (musicPath.exists()) { //if the audio file actually exists
                if (on) { //if the selected state is on
                    if (!isBgm) { //it is not a bgm clip
                        if (isButton) {
                            buttonClip = AudioSystem.getClip(); //create a new clip every time
                        }

                    }
                    AudioInputStream musicInput = AudioSystem.getAudioInputStream(musicPath); //create input stream with audio in it

                    if (!clip.isOpen()) { //if the clip is not open yet
                        clip.open(musicInput); //open the clip to play the music
                    }
                    clip.start(); //tells the audio to start
                    if (isBgm) { //if it is the bgm track
                        clip.loop(Clip.LOOP_CONTINUOUSLY); //loops the audio
                    }
                }
                if (!on) { //if the selected state is off
                    if (clip != null && clip.isOpen()) { //if the clip is not null and it is already open
                        clip.stop(); //stop the clip
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e); //print out the error if there is one
        }
    }

    public void backBtnProperties(JButton button) { //sets all customized graphic features of "back" buttons
        button.setFont(new Font("Serif", Font.BOLD, 20)); //font of button text
        button.setForeground(new Color(114, 128, 133)); //color of button text
        button.setBorder(backBtnBorder); //sets no colored borderline for button
        button.setOpaque(false); //sets button background visible
        button.setBounds(50, 100, 140, 42); //sets position and width height for button
    }

}

class SplashDemo extends Frame implements ActionListener { //splash screen class, displays a loading screen
    static void renderSplashFrame(Graphics2D g, int frame) { //sets up rendering process for the splash screen
        g.setComposite(AlphaComposite.Clear); //specifies how new pixels and combined when rendering an image
        g.setPaintMode(); //sets paint mode for what will be painted to overwrite its current color
    }

    public SplashDemo() { //constructor of splash screen class
        super("SplashScreen demo"); //name of splash screen
        setSize(300, 200); //sets size of splash screen
        setLayout(new BorderLayout()); //sets layout of splash screen
        Menu m1 = new Menu("File"); //sets menu of label File
        MenuItem mi1 = new MenuItem("Exit"); //a menu item in File
        m1.add(mi1); //adding menu item to menu
        mi1.addActionListener(this); //adding actionlistener to menu item
        this.addWindowListener(closeWindow); //if pressed, exit

        MenuBar mb = new MenuBar(); //creating menu bar
        setMenuBar(mb); //setting menu bar as the defined menu bar
        mb.add(m1); //adding File menu to the menu bar
        final SplashScreen splash = SplashScreen.getSplashScreen(); //creating object splash screen
        if (splash == null) { //if it is null
            return; //do not return anything
        }
        Graphics2D gd = splash.createGraphics(); //creating 2d graphics methods for splash screen
        if (gd == null) { //if graphics is empty
            return; //do not return anything
        }
        for (int i = 0; i < 100; i++) { //for 100 loops
            renderSplashFrame(gd, i); //render the splash frame with the graphics g, and frame i
            splash.update(); //update the splash screen
            try {
                Thread.sleep(90); //stops the threat for 90 milliseconds
            } catch (InterruptedException e) {
            }
        }
        splash.close(); //close the splash screen
    }

    public void actionPerformed(ActionEvent ae) {
        System.exit(0);
    } //when exit pressed, exit system, status 0

    private static WindowListener closeWindow = new WindowAdapter() {
        public void windowClosing(WindowEvent e) { //listens for a closed window
            e.getWindow().dispose(); //if window is closed, dispose the window
        }
    };
}

class LevelOneLabel extends JLabel { //definition of level one label that overrides JLabel default definition


    //all

    String delete = "";
    
    private Timer bulletEngine;
    private Timer ghostEngine;
    boolean timerIsEnabled = false;
    boolean isStart = true;
    String scoreBgFile = delete + "scoreBg.gif";
    Rectangle scoreRect = new Rectangle(1085, 70, 60, 60);
    Image scoreBgImage = new ImageIcon(scoreBgFile).getImage();
    String levelOneGroundBgFile = delete + "levelOneGroundBg.gif";
    Image levelOneGroundBgIcon = new ImageIcon(levelOneGroundBgFile).getImage();
    String levelOneFloorBgFile = delete + "levelOneFloorBg.gif";
    Image levelOneFloorBgIcon = new ImageIcon(levelOneFloorBgFile).getImage();


    //sprite
    protected static final int SPRITE_HEIGHT = 150;
    private float vDelta; //vertical delta
    private float gDelta; //Gravity, how much the vDelta will be reduced by over time
    private Timer engine;
    private int yPos; //vertical position
    int xPos;
    boolean floating = false;
    int spriteMaxHeight = 1;

    String spriteGroundFile = delete + "adventurerGround.gif";
    Image spriteGroundImage;
    String spriteSkyFile = delete + "adventurerSky.gif";
    Image spriteSkyImage;
    String spriteIdleFile = delete + "adventurerIdle.gif";
    Image spriteIdleImage;

    //bullet
    public boolean fire = false;

    int globalBulletID = 0;
    HashMap<Integer, Bullet> allBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets

    String spriteBulletFile = delete + "spriteBullet.gif";
    Image spriteBulletImage = new ImageIcon(spriteBulletFile).getImage(); //image of bullet

    //ghost
    int elapsedTime = 0;
    boolean ghostStart = false;
    int globalGhostID = 0;
    HashMap<Integer, Ghost> allGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
    String ghostFile = delete + "ghost.gif";
    Image ghostImage = new ImageIcon(ghostFile).getImage(); //image of ghost

    String hitGhostByBulletFile = delete + "hitGhostByBullet.gif";
    Image hitGhostByBulletImage = new ImageIcon(hitGhostByBulletFile).getImage(); //image of ghost

    String hitGhostBySpriteFile = delete + "hitGhostBySprite.gif";
    Image hitGhostBySpriteImage = new ImageIcon(hitGhostBySpriteFile).getImage(); //image of ghost
    String hitGroundSpriteFile = delete + "adventurerGroundHit.gif";
    Image hitGroundSpriteImage = new ImageIcon(hitGroundSpriteFile).getImage();
    String hitSkySpriteFile = delete + "adventurerSkyHit.gif";
    Image hitSkySpriteImage = new ImageIcon(hitSkySpriteFile).getImage();

    //ghost is hit by bullet
    HashMap<Ghost, Integer> hitGhostByBulletToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of bullets
    int maxDrawCountOfHitGhostByBullet = 120;

    //ghost is hit by sprite
    HashMap<Ghost, Integer> hitGhostBySpriteToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of dying ghosts
    int maxDrawCountOfHitGhostBySprite = 600;

    boolean isGhostHitByBullet = false;
    boolean isGhostHitBySprite = false;

    //health

    String healthThree = delete + "healthThree.gif";
    String healthTwo = delete + "healthTwo.gif";
    String healthOne = delete + "healthOne.gif";
    String healthZero = delete + "healthZero.gif";

    //images of health bar at different stages
    Image healthThreeImage = new ImageIcon(healthThree).getImage();
    Image healthTwoImage = new ImageIcon(healthTwo).getImage();
    Image healthOneImage = new ImageIcon(healthOne).getImage();
    Image healthZeroImage = new ImageIcon(healthZero).getImage();
    int health = 3;

    //power ups
    String levelOnePowerUpBgFile = delete + "levelOnePowerUpBg.gif";
    Image levelOnePowerUpBgIcon = new ImageIcon(levelOnePowerUpBgFile).getImage();

    Panel_ghost panel_ghost;
    boolean isGameOver = false;
    //sound effects
    String shootBulletSoundString = delete + "shootBullet.wav";
    String jumpSoundString = delete + "jump.wav";
    String bulletHitGhostString = delete + "bulletHitGhost.wav";
    String spriteHitByGhostString = delete + "spriteHitByGhost.wav";

    public LevelOneLabel(Panel_ghost panel_g) { //constructor of level one label
        setLayout(null); //set layout of the Jlabel as null

        //creates panel_ghost instance
        panel_ghost = panel_g;

        yPos = getPreferredSize().height - SPRITE_HEIGHT; //setting the y position of the sprite as background - sprite height
        vDelta = 0; //vertical change
        gDelta = 0.15f; //gravity change float
        //this is how much the re-bound will degrade on each cycle

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW); //binding between keystroke and object, when object is focused on
        ActionMap am = getActionMap(); //used to determine what action to fire for specific keystroke event

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "fire"); //when space pressed, jump
        am.put("fire", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                fire = true;
                globalBulletID++;
                Bullet bullet = new Bullet(globalBulletID, xPos + 20, yPos - 100); //creates a new bullet
                Panel_ghost.playSoundEffect(shootBulletSoundString); //play sound effects
                allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "jump"); //when space pressed, jump
        am.put("jump", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                if (yPos + SPRITE_HEIGHT == getHeight()) { //if the sprite is on the ground
                    vDelta = -8; //the vertical change is 8 up
                }
                Panel_ghost.playSoundEffect(jumpSoundString); //play sound effects
            }
        });

    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) { //draws scoreboard centered
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int xLength = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int yLength = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, xLength, yLength);
    }

    public void startTimer() { //starts timer of level one
        timerIsEnabled = true;

        engine = new Timer(2, new ActionListener() { //creates a new timer of the player sprite
            public void actionPerformed(ActionEvent e) {
                int height = getHeight(); //gets height
                //no point if not yet sized
                if (height > 0) {
                    //add the vDelta to the yPos
                    //vDelta may be positive or negative, allowing for both up and down movement
                    yPos += vDelta;
                    //add the gravity to the vDelta, this will slow down upward movement and speed up downward movement
                    vDelta += gDelta;
                    //if the sprite is not on the ground
                    if (yPos + SPRITE_HEIGHT >= height) {
                        //seat the sprite on the ground
                        yPos = height - SPRITE_HEIGHT;
                        //if the re-bound delta is 0 or more, then we have stopped
                        floating = false; //floating is false
                    } else {
                        floating = true; //floating is true
                    }
                }
                if (yPos > spriteMaxHeight) {
                    spriteMaxHeight = yPos; //set new sprite max height
                }
                repaint(); //repaint the sprite
            }
        });
        engine.start(); //start the player timer

        bulletEngine = new Timer(1, new ActionListener() { //bullet engine timer
            public void actionPerformed(ActionEvent e) { //when time hits 1 milliseconds

                fire = true;
            }
        });
        bulletEngine.start(); //start the timer

        ghostEngine = new Timer(1, new ActionListener() { //new timer for ghosts
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 8; //add 2 milliseconds to the amount of elapsed time
                //no point if not yet sized
                //System.out.println(elapsedTime);
                if (elapsedTime < 4000) {
                    isStart = true;
                } else if (elapsedTime >= 4000 && elapsedTime < 55000) { //start running everything after 10 seconds have passed
                    isStart = false;
                    ghostStart = true;
                    if (elapsedTime % 1200 == 0) { //every 1.2 seconds, create a new ghost
                        globalGhostID++; //make a new ghost id object
                        int randomY = (int) (Math.random() * (230) + 250); //set a random number
                        Ghost ghost = new Ghost(globalGhostID, 1350 - 100, randomY); //new ghost's position
                        allGhosts.put(Integer.valueOf(ghost.id), ghost); //create new value and key of ghost
                    }

                } else if (elapsedTime >= 57000) { //if time is over, game is ended
                    ghostStart = false;
                    isGameOver = true;
                    endGame();

                }
            }
        });
        ghostEngine.start(); //start the timer
    }

    public Dimension getPreferredSize() { //gets size of this label
        return new Dimension(1350, 730); //returns its dimensions
    }

    public void endGame() { //when the game is ended
        //stop all timers
        engine.stop();
        bulletEngine.stop();
        ghostEngine.stop();
        //if player is dead, show death screen
        if (health == 0) {
            Panel_ghost.died = true;
            panel_ghost.cl.show(panel_ghost.allPanels, "death");
        }
        //if player lives, show live screen
        else {
            Panel_ghost.died = false;
            panel_ghost.cl.show(panel_ghost.allPanels, "live");
        }
    }

    protected void paintComponent(Graphics g) { //paints the sprite
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create(); //allow for 2d graphic methods
        xPos = 200; //set the x position as 200

        if (elapsedTime < 57000) { //draw components if not reached end of map
            //draw background components and scoreboard
            g2d.drawImage(levelOneGroundBgIcon, 0, 0, 1350, 630, null);
            g2d.drawImage(levelOneFloorBgIcon, 0, 630, 1350, 100, null);
            g2d.drawImage(scoreBgImage, 925, 40, 400, 120, null);
            g2d.drawImage(levelOnePowerUpBgIcon, 375, 20, 520, 150, null);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(5));
            Font sansSerif55 = new Font("Monospaced", Font.BOLD, 55);
            drawCenteredString(g2d, Panel_ghost.score + "", scoreRect, sansSerif55);

            //draw health bar
            if (health == 3) {
                g2d.drawImage(healthThreeImage, 20, 20, 330, 175, null);
            }
            if (health == 2) {
                g2d.drawImage(healthTwoImage, 20, 20, 330, 175, null);
            }
            if (health == 1) {
                g2d.drawImage(healthOneImage, 20, 20, 330, 175, null);
            }
            if (health == 0) {
                g2d.drawImage(healthZeroImage, 20, 20, 330, 175, null);
                elapsedTime = 25000;
            }

            spriteIdleImage = new ImageIcon(spriteIdleFile).getImage(); //image of sprite when it is idle
            spriteGroundImage = new ImageIcon(spriteGroundFile).getImage(); //image of sprite when it is on the ground
            spriteSkyImage = new ImageIcon(spriteSkyFile).getImage(); //image of sprite when it is in the sky

            if (isStart) {
                g2d.drawImage(spriteIdleImage, 200, 480, 130, 150, null); //draw grounded sprite
                g2d.dispose(); //dispose graphics after so it does not appear anymore
            }

            if (fire) {
                //paint bullet
                Iterator iter = allBullets.entrySet().iterator(); //create a new Iterator, allows removal of bullet
                HashMap<Integer, Bullet> displayableBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets
                while (iter.hasNext()) { //while there is a next bullet
                    Map.Entry<Integer, Bullet> entry = (Map.Entry) iter.next(); //iterate bullets on map
                    Bullet bul = entry.getValue(); //get values of current bullet
                    if (bul.bx <= 1350) {
                        //when bullet hits ghost
                        HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                        displayableGhosts.putAll(allGhosts); //set the map of displayable ghost initially as allGhosts
                        Iterator iterGhost = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                        boolean hit = false;
                        Ghost hitGhost = null;
                        while (iterGhost.hasNext()) { //while there is a next ghost
                            Map.Entry<Integer, Ghost> entryGhost = (Map.Entry) iterGhost.next(); //create another ghost on map
                            Ghost ghost = entryGhost.getValue(); //get the new ghost being hit

                            if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy < 0 && bul.by - ghost.gy > -10) { //top hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            } else if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy >= 0 && bul.by - ghost.gy < 150) { //bottom hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            }
                        }
                        if (hit) { //if it is determined to be hit
                            isGhostHitByBullet = true;
                            hitGhostByBulletToDrawCount.put(hitGhost, maxDrawCountOfHitGhostByBullet); //animation after hitting a ghost
                            Panel_ghost.score += 100;
                            Panel_ghost.playSoundEffect(bulletHitGhostString);
                        } else {
                            isGhostHitByBullet = false;
                            g2d.drawImage(spriteBulletImage, bul.bx, bul.by, 85, 72, null); //draw a new bullet
                             bul.bx += 8; //adding position to bullet for it to change
                            displayableBullets.put(entry.getKey(), bul); //continue showing the ghost next time if not hit
                        }
                        allGhosts = displayableGhosts;

                    }
                }
                allBullets = displayableBullets;
                fire = false;
            }

            //ghost hit by bullet
            Iterator iterHitGhostByBulletToDrawCount = hitGhostByBulletToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsByBullet = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostByBulletToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostByBulletToDrawCount = (Map.Entry) iterHitGhostByBulletToDrawCount.next(); //create another ghost on map
                Integer countBulletGhost = entryHitGhostByBulletToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostByBullet = entryHitGhostByBulletToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countBulletGhost > 0) { //hit
                    g2d.drawImage(hitGhostByBulletImage, hitGhostByBullet.gx, hitGhostByBullet.gy, 270, 270, null); //draw ghost effect when dies
                    countBulletGhost--; //minus count by 1 every time
                    displayableHitGhostsByBullet.put(hitGhostByBullet, countBulletGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostByBulletToDrawCount = displayableHitGhostsByBullet;

            if (ghostStart) { //if ghost timer starts
                if (!floating) { //if the sprite is not floating
                    g2d.drawImage(spriteGroundImage, xPos, yPos - 100, 130, 150, null); //draw grounded sprite
                } else if (floating) { //if the sprite is floating
                    g2d.drawImage(spriteSkyImage, xPos, yPos - 100, 130, 150, null); //draw flying sprite
                }
                //paint ghost that are not hit by bullet
                Iterator iter = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                while (iter.hasNext()) { //while there is a next ghost
                    Map.Entry<Integer, Ghost> entry = (Map.Entry) iter.next(); //create another ghost on map
                    Ghost ghost = entry.getValue(); //get a new ghost

                    if (ghost.gx >= 0) {
                        Ghost ghostHitSpriteAnimation = null;

                        boolean ghostHitSprite = false;
                        //check if ghost hits sprite
                        if (Math.abs(xPos - ghost.gx) <= 30 && yPos - 100 - ghost.gy < 0 && yPos - ghost.gy - 100 > -10) { //top ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        } else if (Math.abs(xPos - ghost.gx) <= 30 && yPos - 100 - ghost.gy >= 0 && yPos - 100 - ghost.gy < 150) { //bottom ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        }

                        //if ghost does hit sprite
                        if (ghostHitSprite) {
                            health--;
                            Panel_ghost.playSoundEffect(spriteHitByGhostString);
                            isGhostHitBySprite = true;
                            hitGhostBySpriteToDrawCount.put(ghostHitSpriteAnimation, maxDrawCountOfHitGhostBySprite); //animation after hitting a ghost
                            if (health == 0) {
                                endGame();
                            }
                        }
                        //not hit
                        else {
                            isGhostHitBySprite = false;
                            g2d.drawImage(ghostImage, ghost.gx, ghost.gy, 250, 250, null); //draw a new ghost
                            ghost.gx -= 4; //subtracting position for ghost to change across the screen
                            displayableGhosts.put(entry.getKey(), ghost); //add current ghost to displayable ghosts if in the screen
                        }

                    }
                }
                allGhosts = displayableGhosts;
            }

            //ghost hit by sprite
            Iterator iterHitGhostBySpriteToDrawCount = hitGhostBySpriteToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsBySprite = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostBySpriteToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostBySpriteToDrawCount = (Map.Entry) iterHitGhostBySpriteToDrawCount.next(); //create another ghost on map
                Integer countSpriteGhost = entryHitGhostBySpriteToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostBySprite = entryHitGhostBySpriteToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countSpriteGhost > 0) { //hit
                    g2d.drawImage(hitGhostBySpriteImage, hitGhostBySprite.gx-80, hitGhostBySprite.gy, 420, 260, null); //draw ghost effect when dies
                    if (yPos == 630) {
                        g2d.drawImage(hitGroundSpriteImage, xPos, yPos - 100, 130, 150, null);
                    } else if (yPos < 630) {
                        g2d.drawImage(hitSkySpriteImage, xPos, yPos - 100, 130, 150, null);
                    }
                    countSpriteGhost--; //minus count by 1 every time
                    displayableHitGhostsBySprite.put(hitGhostBySprite, countSpriteGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostBySpriteToDrawCount = displayableHitGhostsBySprite;


            g2d.dispose(); //dispose this current graphics context every time it finishes painting, allowing for repaint
        }
    }

}

class LevelTwoLabel extends JLabel { //definition of level one label that overrides JLabel default definition


    //all

    String delete = "";
    private Timer bulletEngine;
    private Timer ghostEngine;
    boolean timerIsEnabled = false;
    boolean isStart = true;

    String scoreBgFile = delete + "scoreBg.gif";
    Rectangle scoreRect = new Rectangle(1085, 70, 60, 60);
    Image scoreBgImage = new ImageIcon(scoreBgFile).getImage();
    String levelTwoGroundBgFile = delete + "levelTwoGroundBg.gif";
    Image levelTwoGroundBgIcon = new ImageIcon(levelTwoGroundBgFile).getImage();
    String levelTwoFloorBgFile = delete + "levelTwoFloorBg.gif";
    Image levelTwoFloorBgIcon = new ImageIcon(levelTwoFloorBgFile).getImage();


    //sprite
    protected static final int SPRITE_HEIGHT = 150;
    private float vDelta; //vertical delta
    private float gDelta; //Gravity, how much the vDelta will be reduced by over time
    private Timer engine;
    private int yPos; //vertical position
    int xPos;
    boolean floating = false;
    int spriteMaxHeight = 1;

    String spriteGroundFile = delete + "adventurerGround.gif";
    Image spriteGroundImage;
    String spriteSkyFile = delete + "adventurerSky.gif";
    Image spriteSkyImage;
    String spriteIdleFile = delete + "adventurerIdle.gif";
    Image spriteIdleImage;

    //bullet
    public boolean fire = false;

    int globalBulletID = 0;
    HashMap<Integer, Bullet> allBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets

    String spriteBulletFile = delete + "spriteBullet.gif";
    Image spriteBulletImage = new ImageIcon(spriteBulletFile).getImage(); //image of bullet

    //ghost
    int elapsedTime = 0;
    boolean ghostStart = false;
    int globalGhostID = 0;
    HashMap<Integer, Ghost> allGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
    String ghostFile = delete + "ghost.gif";
    Image ghostImage = new ImageIcon(ghostFile).getImage(); //image of ghost

    String hitGhostByBulletFile = delete + "hitGhostByBullet.gif";
    Image hitGhostByBulletImage = new ImageIcon(hitGhostByBulletFile).getImage(); //image of ghost

    String hitGhostBySpriteFile = delete + "hitGhostBySprite.gif";
    Image hitGhostBySpriteImage = new ImageIcon(hitGhostBySpriteFile).getImage(); //image of ghost
    String hitGroundSpriteFile = delete + "adventurerGroundHit.gif";
    Image hitGroundSpriteImage = new ImageIcon(hitGroundSpriteFile).getImage();
    String hitSkySpriteFile = delete + "adventurerSkyHit.gif";
    Image hitSkySpriteImage = new ImageIcon(hitSkySpriteFile).getImage();

    //ghost is hit by bullet
    HashMap<Ghost, Integer> hitGhostByBulletToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of bullets
    int maxDrawCountOfHitGhostByBullet = 120;

    //ghost is hit by sprite
    HashMap<Ghost, Integer> hitGhostBySpriteToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of dying ghosts
    int maxDrawCountOfHitGhostBySprite = 600;

    boolean isGhostHitByBullet = false;
    boolean isGhostHitBySprite = false;

    //health

    String healthThree = delete + "healthThree.gif";
    String healthTwo = delete + "healthTwo.gif";
    String healthOne = delete + "healthOne.gif";
    String healthZero = delete + "healthZero.gif";

    //images of health bar at different stages
    Image healthThreeImage = new ImageIcon(healthThree).getImage();
    Image healthTwoImage = new ImageIcon(healthTwo).getImage();
    Image healthOneImage = new ImageIcon(healthOne).getImage();
    Image healthZeroImage = new ImageIcon(healthZero).getImage();
    int health = 3;

    //power ups
    String levelTwoPowerUpBgFile = delete + "levelTwoPowerUpBg.gif";
    Image levelTwoPowerUpBgIcon = new ImageIcon(levelTwoPowerUpBgFile).getImage();
    //attack power up

    String attackPowerUpFile = delete + "attackPowerUp.gif";
    Image attackPowerUpImage = new ImageIcon(attackPowerUpFile).getImage();
    AttackPowerUp attackPowerUp = null;
    boolean isAttackPowerUp = false;
    boolean attackPowerUpCreated = false;


    String spriteGroundWithAttackPowerUpFile = delete + "adventurerGroundWithAttackPowerUp.gif";
    Image spriteGroundWithAttackPowerUpIcon = new ImageIcon(spriteGroundWithAttackPowerUpFile).getImage();
    String spriteSkyWithAttackPowerUpFile = delete + "adventurerSkyWithAttackPowerUp.gif";
    Image spriteSkyWithAttackPowerUpIcon = new ImageIcon(spriteSkyWithAttackPowerUpFile).getImage();
    boolean isAttackPowerUpGrabbedBySprite = false;
    int elapsedTimeAfterAttackPowerUpGrabbedBySprite = 0;

    //shield power up

    String shieldPowerUpFile = delete + "shieldPowerUp.gif";
    Image shieldPowerUpImage = new ImageIcon(shieldPowerUpFile).getImage();
    ShieldPowerUp shieldPowerUp = null;
    boolean isShieldPowerUp = false;
    boolean shieldPowerUpCreated = false;

    String spriteGroundWithShieldPowerUpFile = delete + "adventurerGroundWithShieldPowerUp.gif";
    Image spriteGroundWithShieldPowerUpIcon = new ImageIcon(spriteGroundWithShieldPowerUpFile).getImage();
    String spriteSkyWithShieldPowerUpFile = delete + "adventurerSkyWithShieldPowerUp.gif";
    Image spriteSkyWithShieldPowerUpIcon = new ImageIcon(spriteSkyWithShieldPowerUpFile).getImage();
    boolean isShieldPowerUpGrabbedBySprite = false;
    int elapsedTimeAfterShieldPowerUpGrabbedBySprite = 0;

    //heal power up

    String healPowerUpFile = delete + "healPowerUp.gif";
    Image healPowerUpImage = new ImageIcon(healPowerUpFile).getImage();
    HealPowerUp healPowerUp = null;
    boolean isHealPowerUp = false;
    boolean healPowerUpCreated = false;

    boolean isHealPowerUpGrabbedBySprite = false;
    boolean healPowerUpUsed = false;

    Panel_ghost panel_ghost;
    boolean isGameOver = false;

    //sound effects
    String shootBulletSoundString = delete + "shootBullet.wav";
    String jumpSoundString = delete + "jump.wav";
    String bulletHitGhostString = delete + "bulletHitGhost.wav";
    String spriteHitByGhostString = delete + "spriteHitByGhost.wav";
    String acquirePowerUpString = delete + "acquirePowerUp.wav";
    String shieldBlockString = delete + "shieldBlock.wav";
    String healString = delete + "heal.wav";

    public LevelTwoLabel(Panel_ghost panel_g) { //constructor of level one label
        setLayout(null); //set layout of the Jlabel as null

        //creates panel_ghost instance
        panel_ghost = panel_g;

        yPos = getPreferredSize().height - SPRITE_HEIGHT; //setting the y position of the sprite as background - sprite height
        vDelta = 0; //vertical change
        gDelta = 0.15f; //gravity change float
        //this is how much the re-bound will degrade on each cycle

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW); //binding between keystroke and object, when object is focused on
        ActionMap am = getActionMap(); //used to determine what action to fire for specific keystroke event

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "fire"); //when space pressed, jump
        am.put("fire", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                fire = true;
                globalBulletID++;
                Bullet bullet = new Bullet(globalBulletID, xPos + 20, yPos - 100); //creates a new bullet
                Panel_ghost.playSoundEffect(shootBulletSoundString);
                allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                if (isAttackPowerUpGrabbedBySprite) {
                    globalBulletID++;
                    bullet = new Bullet(globalBulletID, xPos + 20, yPos - 100 + 50); //creates a new bullet
                    Panel_ghost.playSoundEffect(shootBulletSoundString);
                    allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                    globalBulletID++;
                    bullet = new Bullet(globalBulletID, xPos + 20, yPos - 100 - 50); //creates a new bullet
                    Panel_ghost.playSoundEffect(shootBulletSoundString);
                    allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                }

            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "jump"); //when space pressed, jump
        am.put("jump", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                if (yPos + SPRITE_HEIGHT == getHeight()) { //if the sprite is on the ground
                    vDelta = -8; //the vertical change is 8 up
                }
                Panel_ghost.playSoundEffect(jumpSoundString);
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "heal"); //when 1 pressed, heal
        am.put("heal", new AbstractAction() { //what action will happen when key is "heal"
            public void actionPerformed(ActionEvent e) { //what action is performed
                if (health < 3) {
                    if (isHealPowerUpGrabbedBySprite) {
                        if (!healPowerUpUsed) {
                            Panel_ghost.playSoundEffect(healString);
                            health++;
                            healPowerUpUsed = true;
                        }
                    }
                }
            }
        });

    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) { //draws scoreboard centered
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int xLength = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int yLength = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, xLength, yLength);
    }

    public void startTimer() { //starts timer of level one
        timerIsEnabled = true;

        engine = new Timer(2, new ActionListener() { //creates a new timer of the player sprite
            public void actionPerformed(ActionEvent e) {
                int height = getHeight(); //gets height
                //no point if not yet sized
                if (height > 0) {
                    //add the vDelta to the yPos
                    //vDelta may be positive or negative, allowing for both up and down movement
                    yPos += vDelta;
                    //add the gravity to the vDelta, this will slow down upward movement and speed up downward movement
                    vDelta += gDelta;
                    //if the sprite is not on the ground
                    if (yPos + SPRITE_HEIGHT >= height) {
                        //seat the sprite on the ground
                        yPos = height - SPRITE_HEIGHT;
                        //if the re-bound delta is 0 or more, then we have stopped
                        floating = false; //floating is false
                    } else {
                        floating = true; //floating is true
                    }
                }
                if (yPos > spriteMaxHeight) {
                    spriteMaxHeight = yPos; //set new sprite max height
                }
                repaint(); //repaint the sprite
            }
        });
        engine.start(); //start the player timer

        bulletEngine = new Timer(1, new ActionListener() { //bullet engine timer
            public void actionPerformed(ActionEvent e) { //when time hits 1 milliseconds

                fire = true;
            }
        });
        bulletEngine.start(); //start the timer

        ghostEngine = new Timer(1, new ActionListener() { //new timer for ghosts
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 8; //add 2 milliseconds to the amount of elapsed time
                //no point if not yet sized
                //System.out.println(elapsedTime);
                if (elapsedTime < 4000) {
                    isStart = true;
                } else if (elapsedTime >= 4000 && elapsedTime < 55000) { //start running everything after 10 seconds have passed
                    //System.out.println(elapsedTimeAfterAttackPowerUpGrabbedBySprite);
                    isStart = false;
                    ghostStart = true;
                    if (elapsedTime % 800 == 0) { //every 0.8 seconds, create a new ghost
                        globalGhostID++; //make a new ghost id object
                        int randomY = (int) (Math.random() * (230) + 250); //set a random number
                        Ghost ghost = new Ghost(globalGhostID, 1350 - 100, randomY); //new ghost's position
                        allGhosts.put(Integer.valueOf(ghost.id), ghost); //create new value and key of ghost
                    }
                    if (elapsedTime >= 5000 && elapsedTime < 10000) { //create attack power up
                        if (!attackPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            attackPowerUp = new AttackPowerUp(1350 - 100, randomY);
                            attackPowerUpCreated = true;
                            isAttackPowerUp = true;
                        }

                    }
                    if (elapsedTime >= 15000 && elapsedTime < 20000) { //create shield power up
                        if (!shieldPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            shieldPowerUp = new ShieldPowerUp(1350 - 100, randomY);
                            shieldPowerUpCreated = true;
                            isShieldPowerUp = true;
                        }
                    }

                    if (elapsedTime >= 27000 && elapsedTime < 32000) { //create heal power up
                        if (!healPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            healPowerUp = new HealPowerUp(1350 - 100, randomY);
                            healPowerUpCreated = true;
                            isHealPowerUp = true;
                        }
                    }

                } else if (elapsedTime >= 57000) { //if time is over, game is ended
                    ghostStart = false;
                    isGameOver = true;
                    endGame();

                }
            }
        });
        ghostEngine.start(); //start the timer


    }

    public Dimension getPreferredSize() { //gets size of this label
        return new Dimension(1350, 730); //returns its dimensions
    }

    public void endGame() { //when the game is ended
        //stop all timers
        engine.stop();
        bulletEngine.stop();
        ghostEngine.stop();
        //if player is dead, show death screen
        if (health == 0) {
            Panel_ghost.died = true;
            panel_ghost.cl.show(panel_ghost.allPanels, "death");
        }
        //if player lives, show live screen
        else {
            Panel_ghost.died = false;
            panel_ghost.cl.show(panel_ghost.allPanels, "live");
        }
    }

    protected void paintComponent(Graphics g) { //paints the sprite
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create(); //allow for 2d graphic methods
        xPos = 200; //set the x position as 200

        if (!isGameOver) { //draw components if not reached end of map
            //draw background components and scoreboard

            g2d.drawImage(levelTwoGroundBgIcon, 0, 0, 1350, 630, null);
            g2d.drawImage(levelTwoFloorBgIcon, 0, 630, 1350, 100, null);
            g2d.drawImage(scoreBgImage, 925, 40, 400, 120, null);
            g2d.drawImage(levelTwoPowerUpBgIcon, 375, 20, 520, 175, null);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(5));
            Font sansSerif55 = new Font("Monospaced", Font.BOLD, 55);
            drawCenteredString(g2d, Panel_ghost.score + "", scoreRect, sansSerif55);

            //draw health bar
            if (health == 3) {
                g2d.drawImage(healthThreeImage, 20, 20, 330, 175, null);
            }
            if (health == 2) {
                g2d.drawImage(healthTwoImage, 20, 20, 330, 175, null);
            }
            if (health == 1) {
                g2d.drawImage(healthOneImage, 20, 20, 330, 175, null);
            }
            if (health == 0) {
                g2d.drawImage(healthZeroImage, 20, 20, 330, 175, null);
                elapsedTime = 57000;
            }

            spriteIdleImage = new ImageIcon(spriteIdleFile).getImage(); //image of sprite when it is idle
            spriteGroundImage = new ImageIcon(spriteGroundFile).getImage(); //image of sprite when it is on the ground
            spriteSkyImage = new ImageIcon(spriteSkyFile).getImage(); //image of sprite when it is in the sky

            if (isStart) {
                g2d.drawImage(spriteIdleImage, 200, 480, 130, 150, null); //draw grounded sprite
                g2d.dispose(); //dispose graphics after so it does not appear anymore
            }

            if (fire) {
                //paint bullet
                Iterator iter = allBullets.entrySet().iterator(); //create a new Iterator, allows removal of bullet
                HashMap<Integer, Bullet> displayableBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets
                while (iter.hasNext()) { //while there is a next bullet
                    Map.Entry<Integer, Bullet> entry = (Map.Entry) iter.next(); //create another bullet on map
                    Bullet bul = entry.getValue(); //get values of current bullet
                    if (bul.bx <= 1350) {
                        //when bullet hits ghost
                        HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                        displayableGhosts.putAll(allGhosts); //set the map of displayable ghost initially as allGhosts
                        Iterator iterGhost = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                        boolean hit = false;
                        Ghost hitGhost = null;
                        while (iterGhost.hasNext()) { //while there is a next ghost
                            Map.Entry<Integer, Ghost> entryGhost = (Map.Entry) iterGhost.next(); //create another ghost on map
                            Ghost ghost = entryGhost.getValue(); //get the new ghost being hit

                            if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy < 0 && bul.by - ghost.gy > -10) { //top hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            } else if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy >= 0 && bul.by - ghost.gy < 150) { //bottom hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            }
                        }
                        if (hit) { //if it is determined to be hit
                            isGhostHitByBullet = true;
                            hitGhostByBulletToDrawCount.put(hitGhost, maxDrawCountOfHitGhostByBullet); //animation after hitting a ghost
                            Panel_ghost.score += 100;
                            Panel_ghost.playSoundEffect(bulletHitGhostString);
                        } else {
                            isGhostHitByBullet = false;
                            g2d.drawImage(spriteBulletImage, bul.bx, bul.by, 85, 72, null); //draw a new bullet
                            bul.bx += 8; //adding position to bullet for it to change
                            displayableBullets.put(entry.getKey(), bul); //continue showing the ghost next time if not hit
                        }
                        allGhosts = displayableGhosts;

                    }
                }
                allBullets = displayableBullets;
                fire = false;
            }

            //ghost hit by bullet
            Iterator iterHitGhostByBulletToDrawCount = hitGhostByBulletToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsByBullet = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostByBulletToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostByBulletToDrawCount = (Map.Entry) iterHitGhostByBulletToDrawCount.next(); //create another ghost on map
                Integer countBulletGhost = entryHitGhostByBulletToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostByBullet = entryHitGhostByBulletToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countBulletGhost > 0) { //hit
                    g2d.drawImage(hitGhostByBulletImage, hitGhostByBullet.gx, hitGhostByBullet.gy, 270, 270, null); //draw ghost effect when dies
                    countBulletGhost--; //minus count by 1 every time
                    displayableHitGhostsByBullet.put(hitGhostByBullet, countBulletGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostByBulletToDrawCount = displayableHitGhostsByBullet;

            if (ghostStart) { //if ghost timer starts
                //System.out.println(elapsedTimeAfterAttackPowerUpGrabbedBySprite);
                if (!floating) { //if the sprite is not floating
                    if (isAttackPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteGroundWithAttackPowerUpIcon, xPos, yPos - 100, 150, 150, null);
                    } else if (isShieldPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteGroundWithShieldPowerUpIcon, xPos, yPos - 100, 150, 150, null);
                    } else {
                        g2d.drawImage(spriteGroundImage, xPos, yPos - 100, 130, 150, null); //draw grounded sprite
                    }
                } else if (floating) { //if the sprite is floating
                    if (isAttackPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteSkyWithAttackPowerUpIcon, xPos, yPos - 100, 150, 150, null);
                    } else if (isShieldPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteSkyWithShieldPowerUpIcon, xPos, yPos - 100, 150, 150, null);
                    } else {
                        g2d.drawImage(spriteSkyImage, xPos, yPos - 100, 130, 150, null); //draw flying sprite
                    }
                }
                //paint ghost that are not hit by bullet
                Iterator iter = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                while (iter.hasNext()) { //while there is a next ghost
                    Map.Entry<Integer, Ghost> entry = (Map.Entry) iter.next(); //create another ghost on map
                    Ghost ghost = entry.getValue(); //get a new ghost

                    if (ghost.gx >= 0) {
                        Ghost ghostHitSpriteAnimation = null;

                        boolean ghostHitSprite = false;
                        //check if ghost hits sprite
                        if (Math.abs(xPos - ghost.gx) <= 30 && yPos - 100 - ghost.gy < 0 && yPos - ghost.gy - 100 > -10) { //top ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        } else if (Math.abs(xPos - ghost.gx) <= 30 && yPos - 100 - ghost.gy >= 0 && yPos - 100 - ghost.gy < 150) { //bottom ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        }

                        //if ghost does hit sprite
                        if (ghostHitSprite) {
                            if (!isShieldPowerUpGrabbedBySprite) {
                                Panel_ghost.playSoundEffect(spriteHitByGhostString);
                                health--;
                                isGhostHitBySprite = true;
                                hitGhostBySpriteToDrawCount.put(ghostHitSpriteAnimation, maxDrawCountOfHitGhostBySprite); //animation after hitting a ghost
                                if (health == 0) {
                                    endGame();
                                }
                            }
                            else if (isShieldPowerUpGrabbedBySprite){
                                Panel_ghost.playSoundEffect(shieldBlockString);
                            }
                        }
                        //not hit
                        else {
                            isGhostHitBySprite = false;
                            g2d.drawImage(ghostImage, ghost.gx, ghost.gy, 250, 250, null); //draw a new ghost
                            ghost.gx -= 4; //subtracting position for ghost to change across the screen
                            displayableGhosts.put(entry.getKey(), ghost); //add current ghost to displayable ghosts if in the screen
                        }

                    }
                }
                allGhosts = displayableGhosts;
            }

            //ghost hit by sprite
            Iterator iterHitGhostBySpriteToDrawCount = hitGhostBySpriteToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsBySprite = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostBySpriteToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostBySpriteToDrawCount = (Map.Entry) iterHitGhostBySpriteToDrawCount.next(); //create another ghost on map
                Integer countSpriteGhost = entryHitGhostBySpriteToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostBySprite = entryHitGhostBySpriteToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countSpriteGhost > 0) { //hit
                    g2d.drawImage(hitGhostBySpriteImage, hitGhostBySprite.gx-80, hitGhostBySprite.gy, 420, 260, null); //draw ghost effect when dies //draw ghost effect when dies
                    if (yPos == 630) {
                        g2d.drawImage(hitGroundSpriteImage, xPos, yPos - 100, 130, 150, null);
                    } else if (yPos < 630) {
                        g2d.drawImage(hitSkySpriteImage, xPos, yPos - 100, 130, 150, null);
                    }
                    countSpriteGhost--; //minus count by 1 every time
                    displayableHitGhostsBySprite.put(hitGhostBySprite, countSpriteGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostBySpriteToDrawCount = displayableHitGhostsBySprite;

            //if attack power up is grabbed by sprite, shoot multiple bullets at once
            if (isAttackPowerUp) {
                if (!isAttackPowerUpGrabbedBySprite) {
                    g2d.drawImage(attackPowerUpImage, attackPowerUp.attackx, attackPowerUp.attacky, 150, 150, null);
                    attackPowerUp.attackx -= 2;
                    if (Math.abs(xPos - attackPowerUp.attackx) <= 60 && yPos - 100 - attackPowerUp.attacky < 0 && yPos - attackPowerUp.attacky - 100 > -20) { //top attackPowerUpHitSprite
                        isAttackPowerUpGrabbedBySprite = true;
                        isAttackPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(xPos - attackPowerUp.attackx) <= 60 && yPos - 100 - attackPowerUp.attacky >= 0 && yPos - 100 - attackPowerUp.attacky < 140) { //bottom attackPowerUpHitSprite
                        isAttackPowerUpGrabbedBySprite = true;
                        isAttackPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            } else if (isAttackPowerUpGrabbedBySprite) {
                elapsedTimeAfterAttackPowerUpGrabbedBySprite += 6;
                if (elapsedTimeAfterAttackPowerUpGrabbedBySprite >= 5000) {
                    isAttackPowerUpGrabbedBySprite = false;
                }
            }

            //if shield power up is grabbed by sprite, protect from all damage
            if (isShieldPowerUp) {
                if (!isShieldPowerUpGrabbedBySprite) {
                    g2d.drawImage(shieldPowerUpImage, shieldPowerUp.shieldx, shieldPowerUp.shieldy, 100, 100, null);
                    shieldPowerUp.shieldx -= 2;
                    if (Math.abs(xPos - shieldPowerUp.shieldx) <= 60 && yPos - 100 - shieldPowerUp.shieldy < 0 && yPos - shieldPowerUp.shieldy - 100 > -20) { //top shieldPowerUpHitSprite
                        isShieldPowerUpGrabbedBySprite = true;
                        isShieldPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(xPos - shieldPowerUp.shieldx) <= 60 && yPos - 100 - shieldPowerUp.shieldy >= 0 && yPos - 100 - shieldPowerUp.shieldy < 140) { //bottom shieldPowerUpHitSprite
                        isShieldPowerUpGrabbedBySprite = true;
                        isShieldPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            } else if (isShieldPowerUpGrabbedBySprite) {
                elapsedTimeAfterShieldPowerUpGrabbedBySprite += 6;
                if (elapsedTimeAfterShieldPowerUpGrabbedBySprite >= 5000) {
                    isShieldPowerUpGrabbedBySprite = false;
                }
            }

            //if heal is active, recover 1/3 blood
            if (isHealPowerUp) {
                if (!isHealPowerUpGrabbedBySprite) {
                    g2d.drawImage(healPowerUpImage, healPowerUp.healx, healPowerUp.healy, 100, 159, null);
                    healPowerUp.healx -= 2;
                    if (Math.abs(xPos - healPowerUp.healx) <= 60 && yPos - 100 - healPowerUp.healy < 0 && yPos - healPowerUp.healy - 100 > -20) { //top healPowerUpHitSprite
                        isHealPowerUpGrabbedBySprite = true;
                        isHealPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(xPos - healPowerUp.healx) <= 60 && yPos - 100 - healPowerUp.healy >= 0 && yPos - 100 - healPowerUp.healy < 140) { //bottom healPowerUpHitSprite
                        isHealPowerUpGrabbedBySprite = true;
                        isHealPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            }
            g2d.dispose(); //dispose this current graphics context every time it finishes painting, allowing for repaint
        }
    }

}

class LevelThreeLabel extends JLabel { //definition of level one label that overrides JLabel default definition

    //all

    String delete = "";
    private Timer engine;
    private Timer bulletEngine;
    private Timer ghostEngine;
    private Timer blockEngine;
    boolean timerIsEnabled = false;
    boolean isStart = true;

    String scoreBgFile = delete + "scoreBg.gif";
    Rectangle scoreRect = new Rectangle(1085, 70, 60, 60);
    Image scoreBgImage = new ImageIcon(scoreBgFile).getImage();
    String levelThreeGroundBgFile = delete + "levelThreeGroundBg.gif";
    Image levelThreeGroundBgIcon = new ImageIcon(levelThreeGroundBgFile).getImage();
    String levelThreeFloorBgFile = delete + "levelThreeFloorBg.gif";
    Image levelThreeFloorBgIcon = new ImageIcon(levelThreeFloorBgFile).getImage();


    //sprite
    private float vDelta; //vertical delta
    private float gDelta; //Gravity, how much the vDelta will be reduced by over time

    String spriteGroundFile = delete + "adventurerGround.gif";
    Image spriteGroundImage;
    String spriteSkyFile = delete + "adventurerSky.gif";
    Image spriteSkyImage;
    String spriteIdleFile = delete + "adventurerIdle.gif";
    Image spriteIdleImage;
    Sprite sprite = new Sprite(200, getPreferredSize().height - 100-150, 150);

    //bullet
    public boolean fire = false;

    int globalBulletID = 0;
    HashMap<Integer, Bullet> allBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets

    String spriteBulletFile = delete + "spriteBullet.gif";
    Image spriteBulletImage = new ImageIcon(spriteBulletFile).getImage(); //image of bullet

    //ghost
    int elapsedTime = 0;
    boolean ghostStart = false;
    int globalGhostID = 0;
    HashMap<Integer, Ghost> allGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
    String ghostFile = delete + "ghost.gif";
    Image ghostImage = new ImageIcon(ghostFile).getImage(); //image of ghost

    String hitGhostByBulletFile = delete + "hitGhostByBullet.gif";
    Image hitGhostByBulletImage = new ImageIcon(hitGhostByBulletFile).getImage(); //image of ghost

    String hitGhostBySpriteFile = delete + "hitGhostBySprite.gif";
    Image hitGhostBySpriteImage = new ImageIcon(hitGhostBySpriteFile).getImage(); //image of ghost
    String hitGroundSpriteFile = delete + "adventurerGroundHit.gif";
    Image hitGroundSpriteImage = new ImageIcon(hitGroundSpriteFile).getImage();
    String hitSkySpriteFile = delete + "adventurerSkyHit.gif";
    Image hitSkySpriteImage = new ImageIcon(hitSkySpriteFile).getImage();

    //ghost is hit by bullet
    HashMap<Ghost, Integer> hitGhostByBulletToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of bullets
    int maxDrawCountOfHitGhostByBullet = 120;

    //ghost is hit by sprite
    HashMap<Ghost, Integer> hitGhostBySpriteToDrawCount = new HashMap<Ghost, Integer>(); //creates a map of dying ghosts
    int maxDrawCountOfHitGhostBySprite = 600;

    boolean isGhostHitByBullet = false;
    boolean isGhostHitBySprite = false;

    //health

    String healthThree = delete + "healthThree.gif";
    String healthTwo = delete + "healthTwo.gif";
    String healthOne = delete + "healthOne.gif";
    String healthZero = delete + "healthZero.gif";

    //images of health bar at different stages
    Image healthThreeImage = new ImageIcon(healthThree).getImage();
    Image healthTwoImage = new ImageIcon(healthTwo).getImage();
    Image healthOneImage = new ImageIcon(healthOne).getImage();
    Image healthZeroImage = new ImageIcon(healthZero).getImage();
    int health = 3;

    //power ups
    String levelThreePowerUpBgFile = delete + "levelThreePowerUpBg.gif";
    Image levelThreePowerUpBgIcon = new ImageIcon(levelThreePowerUpBgFile).getImage();
    //attack power up

    String attackPowerUpFile = delete + "attackPowerUp.gif";
    Image attackPowerUpImage = new ImageIcon(attackPowerUpFile).getImage();
    AttackPowerUp attackPowerUp = null;
    boolean isAttackPowerUp = false;
    boolean attackPowerUpCreated = false;


    String spriteGroundWithAttackPowerUpFile = delete + "adventurerGroundWithAttackPowerUp.gif";
    Image spriteGroundWithAttackPowerUpIcon = new ImageIcon(spriteGroundWithAttackPowerUpFile).getImage();
    String spriteSkyWithAttackPowerUpFile = delete + "adventurerSkyWithAttackPowerUp.gif";
    Image spriteSkyWithAttackPowerUpIcon = new ImageIcon(spriteSkyWithAttackPowerUpFile).getImage();
    boolean isAttackPowerUpGrabbedBySprite = false;
    int elapsedTimeAfterAttackPowerUpGrabbedBySprite = 0;

    //shield power up

    String shieldPowerUpFile = delete + "shieldPowerUp.gif";
    Image shieldPowerUpImage = new ImageIcon(shieldPowerUpFile).getImage();
    ShieldPowerUp shieldPowerUp = null;
    boolean isShieldPowerUp = false;
    boolean shieldPowerUpCreated = false;

    String spriteGroundWithShieldPowerUpFile = delete + "adventurerGroundWithShieldPowerUp.gif";
    Image spriteGroundWithShieldPowerUpIcon = new ImageIcon(spriteGroundWithShieldPowerUpFile).getImage();
    String spriteSkyWithShieldPowerUpFile = delete + "adventurerSkyWithShieldPowerUp.gif";
    Image spriteSkyWithShieldPowerUpIcon = new ImageIcon(spriteSkyWithShieldPowerUpFile).getImage();
    boolean isShieldPowerUpGrabbedBySprite = false;
    int elapsedTimeAfterShieldPowerUpGrabbedBySprite = 0;

    //heal power up

    String healPowerUpFile = delete + "healPowerUp.gif";
    Image healPowerUpImage = new ImageIcon(healPowerUpFile).getImage();
    HealPowerUp healPowerUp = null;
    boolean isHealPowerUp = false;
    boolean healPowerUpCreated = false;

    boolean isHealPowerUpGrabbedBySprite = false;
    boolean healPowerUpUsed = false;
    //wood blocks
    String blockFile = delete + "woodBlock.png";
    Image blockImage = new ImageIcon(blockFile).getImage();
    HashMap<Integer, Block> allBlocks = new HashMap<Integer, Block>(); //creates a map of blocks
    boolean blockStart = false;
    int globalBlockID = 0;

    Panel_ghost panel_ghost;
    boolean isGameOver = false;
    //sound effects
    String shootBulletSoundString = delete + "shootBullet.wav";
    String jumpSoundString = delete + "jump.wav";
    String bulletHitGhostString = delete + "bulletHitGhost.wav";
    String spriteHitByGhostString = delete + "spriteHitByGhost.wav";
    String acquirePowerUpString = delete + "acquirePowerUp.wav";
    String shieldBlockString = delete + "shieldBlock.wav";
    String healString = delete + "heal.wav";

    public LevelThreeLabel(Panel_ghost panel_g) { //constructor of level one label
        setLayout(null); //set layout of the Jlabel as null

        //creates panel_ghost instance
        panel_ghost = panel_g;
        
        vDelta = 0; //vertical change
        gDelta = 0.15f; //gravity change float
        //this is how much the re-bound will degrade on each cycle

        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW); //binding between keystroke and object, when object is focused on
        ActionMap am = getActionMap(); //used to determine what action to fire for specific keystroke event

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "fire"); //when space pressed, jump
        am.put("fire", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                fire = true;
                globalBulletID++;
                int bulletY = sprite.spriteYHead+(sprite.spriteHeight/3);
                Bullet bullet = new Bullet(globalBulletID, sprite.spriteX + 20, bulletY); //creates a new bullet
                Panel_ghost.playSoundEffect(shootBulletSoundString);
                allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                if (isAttackPowerUpGrabbedBySprite) {
                    globalBulletID++;
                    bullet = new Bullet(globalBulletID, sprite.spriteX + 20, bulletY + 50); //creates a new bullet
                    Panel_ghost.playSoundEffect(shootBulletSoundString);
                    allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                    globalBulletID++;
                    bullet = new Bullet(globalBulletID, sprite.spriteX + 20, bulletY - 50); //creates a new bullet
                    Panel_ghost.playSoundEffect(shootBulletSoundString);
                    allBullets.put(Integer.valueOf(bullet.id), bullet); //adds bullet to map of allBullets
                }

            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "jump"); //when space pressed, jump
        am.put("jump", new AbstractAction() { //what action will happen when key is "jump"
            public void actionPerformed(ActionEvent e) { //what action is performed
                //can only jump when we're actually on the ground
                //if (sprite.spriteYHead+100 + sprite.spriteHeight >= getHeight()) { //if the sprite is on the ground
                    vDelta = -8; //the vertical change is 8 up
                if (sprite.spriteYHead < sprite.spriteHeight){
                    vDelta = 0;
                }
                Panel_ghost.playSoundEffect(jumpSoundString);
            }
        });

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "heal"); //when 1 pressed, heal
        am.put("heal", new AbstractAction() { //what action will happen when key is "heal"
            public void actionPerformed(ActionEvent e) { //what action is performed
                if (health < 3) {
                    if (isHealPowerUpGrabbedBySprite) {
                        if (!healPowerUpUsed) {
                            Panel_ghost.playSoundEffect(healString);
                            health++;
                            healPowerUpUsed = true;
                        }
                    }
                }
            }
        });

    }

    public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) { //draws scoreboard centered
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int xLength = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int yLength = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, xLength, yLength);
    }

    public boolean isFloating(Sprite sprite){ //if the sprite is floating
        if (sprite.spriteYHead + sprite.spriteHeight + 100 < getHeight()){
            return true;
        }
        return false;
    }

    public void moveSpriteDownward(Sprite sprite){ //moves sprite downward
        //vDelta may be positive or negative, allowing for both up and down movement
        int height = getHeight();
        sprite.spriteYHead += vDelta;
        //add the gravity to the vDelta, this will slow down upward movement and speed up downward movement
        vDelta += gDelta;
        //if the sprite is not on the ground
        if (sprite.spriteYHead + sprite.spriteHeight + 100 >= height) {
            //seat the sprite on the ground
            sprite.spriteYHead = height - 100 - sprite.spriteHeight;
            //if the re-bound delta is 0 or more, then we have stopped
        }
    }

    public void startTimer() { //starts timer of level one
        timerIsEnabled = true;

        engine = new Timer(2, new ActionListener() { //creates a new timer of the player sprite
            public void actionPerformed(ActionEvent e) {
                //System.out.println(sprite.spriteYHead);
                int height = getHeight(); //gets height
                //no point if not yet sized
                if (height > 0) {
                    if (sprite.block == null) {
                        moveSpriteDownward(sprite);
                    }
                }
                repaint(); //repaint the sprite
            }
        });
        engine.start(); //start the player timer

        bulletEngine = new Timer(1, new ActionListener() { //bullet engine timer
            public void actionPerformed(ActionEvent e) { //when time hits 1 milliseconds

                fire = true;
            }
        });
        bulletEngine.start(); //start the timer

        ghostEngine = new Timer(1, new ActionListener() { //new timer for ghosts
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 8; //add 2 milliseconds to the amount of elapsed time
                //no point if not yet sized
                //System.out.println(elapsedTime);
                if (elapsedTime < 4000) {
                    isStart = true;
                } else if (elapsedTime >= 4000 && elapsedTime < 55000) { //start running everything after 10 seconds have passed
                    //System.out.println(elapsedTimeAfterAttackPowerUpGrabbedBySprite);
                    isStart = false;
                    ghostStart = true;
                    if (elapsedTime % 680 == 0) { //every 0.68 seconds, create a new ghost
                        globalGhostID++; //make a new ghost id object
                        int randomY = (int) (Math.random() * 430) + 50; //set a random number
                        Ghost ghost = new Ghost(globalGhostID, 1350 - 100, randomY); //new ghost's position
                        allGhosts.put(Integer.valueOf(ghost.id), ghost); //create new value and key of ghost
                    }
                    if (elapsedTime >= 5000 && elapsedTime < 10000) {
                        if (!attackPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            attackPowerUp = new AttackPowerUp(1350 - 100, randomY);
                            attackPowerUpCreated = true;
                            isAttackPowerUp = true;
                        }

                    }
                    if (elapsedTime >= 15000 && elapsedTime < 20000) {
                        if (!shieldPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            shieldPowerUp = new ShieldPowerUp(1350 - 100, randomY);
                            shieldPowerUpCreated = true;
                            isShieldPowerUp = true;
                        }
                    }

                    if (elapsedTime >= 27000 && elapsedTime < 32000) {
                        if (!healPowerUpCreated) {
                            int randomY = (int) (Math.random() * (230) + 250); //set a random number
                            healPowerUp = new HealPowerUp(1350 - 100, randomY);
                            healPowerUpCreated = true;
                            isHealPowerUp = true;
                        }
                    }

                } else if (elapsedTime >= 57000) { //if time is over, game is ended
                    ghostStart = false;
                    isGameOver = true;
                    endGame();

                }
            }
        });
        ghostEngine.start(); //start the timer

        blockEngine = new Timer(1, new ActionListener() { //new timer for blocks
            public void actionPerformed(ActionEvent e) {
                if (elapsedTime >= 4000 && elapsedTime < 55000) { //start running everything after 10 seconds have passed
                    blockStart = true;
                    if (elapsedTime % 10000 == 0) { //every 3 seconds, create a new block
                        globalBlockID++; //make a new block id object
                        int randomY = (int) (Math.random() * 150) + 300; //set a random number
                        Block block = new Block(globalBlockID, 1350 - 342, 342, randomY); //new block's position
                        allBlocks.put(Integer.valueOf(block.id), block); //create new value and key of block
                    }

                } else if (elapsedTime >= 57000) { //if time is over, game is ended
                    blockStart = false;

                }
            }
        });
        blockEngine.start(); //start the timer


    }

    public Dimension getPreferredSize() { //gets size of this label
        return new Dimension(1350, 730); //returns its dimensions
    }

    public void endGame() { //when the game is ended
        //stop all timers
        engine.stop();
        bulletEngine.stop();
        ghostEngine.stop();
        blockEngine.stop();
        //if player is dead, show death screen
        if (health == 0) {
            Panel_ghost.died = true;
            panel_ghost.cl.show(panel_ghost.allPanels, "death");
        }
        //if player lives, show live screen
        else {
            Panel_ghost.died = false;
            panel_ghost.cl.show(panel_ghost.allPanels, "live");
        }
    }

    protected void paintComponent(Graphics g) { //paints the sprite
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create(); //allow for 2d graphic methods

        if (!isGameOver) { //draw components if not reached end of map
            //draw background components and scoreboard

            g2d.drawImage(levelThreeGroundBgIcon, 0, 0, 1350, 630, null);
            g2d.drawImage(levelThreeFloorBgIcon, 0, 630, 1350, 100, null);
            g2d.drawImage(scoreBgImage, 925, 40, 400, 120, null);
            g2d.drawImage(levelThreePowerUpBgIcon, 375, 20, 520, 175, null);
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(5));
            Font sansSerif55 = new Font("Monospaced", Font.BOLD, 55);
            drawCenteredString(g2d, Panel_ghost.score + "", scoreRect, sansSerif55);

            //draw health bar
            if (health == 3) {
                g2d.drawImage(healthThreeImage, 20, 20, 330, 175, null);
            }
            if (health == 2) {
                g2d.drawImage(healthTwoImage, 20, 20, 330, 175, null);
            }
            if (health == 1) {
                g2d.drawImage(healthOneImage, 20, 20, 330, 175, null);
            }
            if (health == 0) {
                g2d.drawImage(healthZeroImage, 20, 20, 330, 175, null);
                elapsedTime = 57000;
            }

            spriteIdleImage = new ImageIcon(spriteIdleFile).getImage(); //image of sprite when it is idle
            spriteGroundImage = new ImageIcon(spriteGroundFile).getImage(); //image of sprite when it is on the ground
            spriteSkyImage = new ImageIcon(spriteSkyFile).getImage(); //image of sprite when it is in the sky

            if (isStart) {
                g2d.drawImage(spriteIdleImage, 200, 480, 130, 150, null); //draw grounded sprite
                g2d.dispose(); //dispose graphics after so it does not appear anymore
            }

            if (fire) {
                //paint bullet
                Iterator iter = allBullets.entrySet().iterator(); //create a new Iterator, allows removal of bullet
                HashMap<Integer, Bullet> displayableBullets = new HashMap<Integer, Bullet>(); //creates a map of bullets
                while (iter.hasNext()) { //while there is a next bullet
                    Map.Entry<Integer, Bullet> entry = (Map.Entry) iter.next(); //create another bullet on map
                    Bullet bul = entry.getValue(); //get values of current bullet
                    if (bul.bx <= 1350) {
                        //when bullet hits ghost
                        HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                        displayableGhosts.putAll(allGhosts); //set the map of displayable ghost initially as allGhosts
                        Iterator iterGhost = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                        boolean hit = false;
                        Ghost hitGhost = null;
                        while (iterGhost.hasNext()) { //while there is a next ghost
                            Map.Entry<Integer, Ghost> entryGhost = (Map.Entry) iterGhost.next(); //create another ghost on map
                            Ghost ghost = entryGhost.getValue(); //get the new ghost being hit

                            if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy < 0 && bul.by - ghost.gy > -10) { //top hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            } else if (Math.abs(bul.bx - ghost.gx) <= 30 && bul.by - ghost.gy >= 0 && bul.by - ghost.gy < 150) { //bottom hit
                                displayableGhosts.remove(entryGhost.getKey()); //remove the hit ghost  from displayable ghosts
                                hit = true;
                                hitGhost = ghost;
                                break; //after hitting a ghost, exit out of while loop so one bullet can only hit one ghost
                            }
                        }
                        if (hit) { //if it is determined to be hit
                            isGhostHitByBullet = true;
                            hitGhostByBulletToDrawCount.put(hitGhost, maxDrawCountOfHitGhostByBullet); //animation after hitting a ghost
                            Panel_ghost.score += 100;
                            Panel_ghost.playSoundEffect(bulletHitGhostString);
                        } else {
                            isGhostHitByBullet = false;
                            g2d.drawImage(spriteBulletImage, bul.bx, bul.by-20, 85, 72, null); //draw a new bullet
                            bul.bx += 8; //adding position to bullet for it to change
                            displayableBullets.put(entry.getKey(), bul); //continue showing the ghost next time if not hit
                        }
                        allGhosts = displayableGhosts;

                    }
                }
                allBullets = displayableBullets;
                fire = false;
            }

            //ghost hit by bullet
            Iterator iterHitGhostByBulletToDrawCount = hitGhostByBulletToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsByBullet = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostByBulletToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostByBulletToDrawCount = (Map.Entry) iterHitGhostByBulletToDrawCount.next(); //create another ghost on map
                Integer countBulletGhost = entryHitGhostByBulletToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostByBullet = entryHitGhostByBulletToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countBulletGhost > 0) { //hit
                    g2d.drawImage(hitGhostByBulletImage, hitGhostByBullet.gx, hitGhostByBullet.gy, 270, 270, null); //draw ghost effect when dies
                    countBulletGhost--; //minus count by 1 every time
                    displayableHitGhostsByBullet.put(hitGhostByBullet, countBulletGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostByBulletToDrawCount = displayableHitGhostsByBullet;

            if (ghostStart) { //if ghost timer starts
                //System.out.println(elapsedTimeAfterAttackPowerUpGrabbedBySprite);
                if (!isFloating(sprite) && sprite.block == null) { //if the sprite is not floating
                    if (isAttackPowerUpGrabbedBySprite) { //on ground, with attack power up
                        g2d.drawImage(spriteGroundWithAttackPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else if (isShieldPowerUpGrabbedBySprite) { //on ground, with shield power up
                        g2d.drawImage(spriteGroundWithShieldPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else { //on ground, no power up
                        g2d.drawImage(spriteGroundImage, sprite.spriteX, sprite.spriteYHead, 130, sprite.spriteHeight, null); //draw grounded sprite
                    }
                }
                else if (isFloating(sprite) && sprite.block == null){ //if the sprite is floating
                    if (isAttackPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteSkyWithAttackPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else if (isShieldPowerUpGrabbedBySprite) {
                        g2d.drawImage(spriteSkyWithShieldPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else {
                        g2d.drawImage(spriteSkyImage, sprite.spriteX, sprite.spriteYHead, 130, sprite.spriteHeight, null); //draw flying sprite
                    }
                }
                else{
                    if (isAttackPowerUpGrabbedBySprite) { //on ground, with attack power up
                        g2d.drawImage(spriteGroundWithAttackPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else if (isShieldPowerUpGrabbedBySprite) { //on ground, with shield power up
                        g2d.drawImage(spriteGroundWithShieldPowerUpIcon, sprite.spriteX, sprite.spriteYHead, 150, sprite.spriteHeight, null);
                    } else { //on ground, no power up
                        g2d.drawImage(spriteGroundImage, sprite.spriteX, sprite.spriteYHead, 130, sprite.spriteHeight, null); //draw grounded sprite
                    }
                }
                //paint ghost that are not hit by bullet
                Iterator iter = allGhosts.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
                HashMap<Integer, Ghost> displayableGhosts = new HashMap<Integer, Ghost>(); //creates a map of ghosts
                while (iter.hasNext()) { //while there is a next ghost
                    Map.Entry<Integer, Ghost> entry = (Map.Entry) iter.next(); //create another ghost on map
                    Ghost ghost = entry.getValue(); //get a new ghost

                    if (ghost.gx >= 0) {
                        Ghost ghostHitSpriteAnimation = null;

                        boolean ghostHitSprite = false;
                        //check if ghost hits sprite
                        if (Math.abs(sprite.spriteX - ghost.gx) <= 30 && sprite.spriteYHead - ghost.gy < 0 && sprite.spriteYHead- ghost.gy > -10) { //top ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        } else if (Math.abs(sprite.spriteX - ghost.gx) <= 30 && sprite.spriteYHead - ghost.gy >= 0 && sprite.spriteYHead - ghost.gy < 150) { //bottom ghostHitSprite
                            ghostHitSprite = true;
                            ghostHitSpriteAnimation = ghost;
                        }

                        //if ghost does hit sprite
                        if (ghostHitSprite) {
                            if (!isShieldPowerUpGrabbedBySprite) {
                                Panel_ghost.playSoundEffect(spriteHitByGhostString);
                                health--;
                                isGhostHitBySprite = true;
                                hitGhostBySpriteToDrawCount.put(ghostHitSpriteAnimation, maxDrawCountOfHitGhostBySprite); //animation after hitting a ghost
                                if (health == 0) {
                                    endGame();
                                }
                            }
                            else if (isShieldPowerUpGrabbedBySprite){
                                Panel_ghost.playSoundEffect(shieldBlockString);
                            }
                        }
                        //not hit
                        else {
                            isGhostHitBySprite = false;
                            g2d.drawImage(ghostImage, ghost.gx, ghost.gy, 250, 250, null); //draw a new ghost
                            ghost.gx -= 4; //subtracting position for ghost to change across the screen
                            displayableGhosts.put(entry.getKey(), ghost); //add current ghost to displayable ghosts if in the screen
                        }

                    }
                }
                allGhosts = displayableGhosts;
            }

            //ghost hit by sprite
            Iterator iterHitGhostBySpriteToDrawCount = hitGhostBySpriteToDrawCount.entrySet().iterator(); //create a new Iterator, allows removal of ghosts
            HashMap<Ghost, Integer> displayableHitGhostsBySprite = new HashMap<Ghost, Integer>(); //creates a map of ghosts
            while (iterHitGhostBySpriteToDrawCount.hasNext()) { //while there is a next ghost
                Map.Entry<Ghost, Integer> entryHitGhostBySpriteToDrawCount = (Map.Entry) iterHitGhostBySpriteToDrawCount.next(); //create another ghost on map
                Integer countSpriteGhost = entryHitGhostBySpriteToDrawCount.getValue(); //get the new ghost being hit
                Ghost hitGhostBySprite = entryHitGhostBySpriteToDrawCount.getKey(); //get the id of the ghost that is hit

                if (countSpriteGhost > 0) { //hit
                    g2d.drawImage(hitGhostBySpriteImage, hitGhostBySprite.gx-80, hitGhostBySprite.gy, 420, 260, null); //draw ghost effect when dies
                    if (sprite.spriteYHead == 630) {
                        g2d.drawImage(hitGroundSpriteImage, sprite.spriteX, sprite.spriteYHead, 130, 150, null);
                    } else if (sprite.spriteYHead < 630) {
                        g2d.drawImage(hitSkySpriteImage, sprite.spriteX, sprite.spriteYHead, 130, 150, null);
                    }
                    countSpriteGhost--; //minus count by 1 every time
                    displayableHitGhostsBySprite.put(hitGhostBySprite, countSpriteGhost); //add ghost to this map, shows ghost death animation
                }
            }
            hitGhostBySpriteToDrawCount = displayableHitGhostsBySprite;

            if (isAttackPowerUp) {
                if (!isAttackPowerUpGrabbedBySprite) {
                    g2d.drawImage(attackPowerUpImage, attackPowerUp.attackx, attackPowerUp.attacky, 150, 150, null);
                    attackPowerUp.attackx -= 2;
                    if (Math.abs(sprite.spriteX - attackPowerUp.attackx) <= 60 && sprite.spriteYHead - attackPowerUp.attacky < 0 && sprite.spriteYHead - attackPowerUp.attacky > -20) { //top attackPowerUpHitSprite
                        isAttackPowerUpGrabbedBySprite = true;
                        isAttackPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(sprite.spriteX - attackPowerUp.attackx) <= 60 && sprite.spriteYHead - attackPowerUp.attacky >= 0 && sprite.spriteYHead - attackPowerUp.attacky < 40) { //bottom attackPowerUpHitSprite
                        isAttackPowerUpGrabbedBySprite = true;
                        isAttackPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            } else if (isAttackPowerUpGrabbedBySprite) {
                elapsedTimeAfterAttackPowerUpGrabbedBySprite += 6;
                if (elapsedTimeAfterAttackPowerUpGrabbedBySprite >= 5000) {
                    isAttackPowerUpGrabbedBySprite = false;
                }
            }

            if (isShieldPowerUp) {
                if (!isShieldPowerUpGrabbedBySprite) {
                    g2d.drawImage(shieldPowerUpImage, shieldPowerUp.shieldx, shieldPowerUp.shieldy, 100, 100, null);
                    shieldPowerUp.shieldx -= 2;
                    if (Math.abs(sprite.spriteX - shieldPowerUp.shieldx) <= 60 && sprite.spriteYHead - shieldPowerUp.shieldy < 0 && sprite.spriteYHead - shieldPowerUp.shieldy > -20) { //top shieldPowerUpHitSprite
                        isShieldPowerUpGrabbedBySprite = true;
                        isShieldPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(sprite.spriteX - shieldPowerUp.shieldx) <= 60 && sprite.spriteYHead - shieldPowerUp.shieldy >= 0 && sprite.spriteYHead - shieldPowerUp.shieldy < 40) { //bottom shieldPowerUpHitSprite
                        isShieldPowerUpGrabbedBySprite = true;
                        isShieldPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            } else if (isShieldPowerUpGrabbedBySprite) {
                elapsedTimeAfterShieldPowerUpGrabbedBySprite += 6;
                if (elapsedTimeAfterShieldPowerUpGrabbedBySprite >= 5000) {
                    isShieldPowerUpGrabbedBySprite = false;
                }
            }

            if (isHealPowerUp) {
                if (!isHealPowerUpGrabbedBySprite) {
                    g2d.drawImage(healPowerUpImage, healPowerUp.healx, healPowerUp.healy, 100, 159, null);
                    healPowerUp.healx -= 2;
                    if (Math.abs(sprite.spriteX - healPowerUp.healx) <= 60 && sprite.spriteYHead - healPowerUp.healy < 0 && sprite.spriteYHead - healPowerUp.healy  > -20) { //top healPowerUpHitSprite
                        isHealPowerUpGrabbedBySprite = true;
                        isHealPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    } else if (Math.abs(sprite.spriteX - healPowerUp.healx) <= 60 && sprite.spriteYHead - healPowerUp.healy >= 0 && sprite.spriteYHead - healPowerUp.healy < 40) { //bottom healPowerUpHitSprite
                        isHealPowerUpGrabbedBySprite = true;
                        isHealPowerUp = false;
                        Panel_ghost.playSoundEffect(acquirePowerUpString);
                    }
                }
            }

            if (blockStart) { //if block timer starts
                //paint blocks
                Iterator iter = allBlocks.entrySet().iterator(); //create a new Iterator, allows removal of blocks
                HashMap<Integer, Block> displayableBlocks = new HashMap<Integer, Block>(); //creates a map of blocks
                boolean isSpriteOnABlock = false;
                while (iter.hasNext()) { //while there is a next block
                    Map.Entry<Integer, Block> entry = (Map.Entry) iter.next(); //create another ghost on map
                    Block block = entry.getValue(); //get a new ghost

                    if (block.blockxLeft + block.blockLength >= 0) {
                        //still in screen


                        //aabb
                        //is sprite on board
                        if (isSpriteOnBoard(sprite, block)) {
                            isSpriteOnABlock =  true;
                            sprite.spriteYHead = block.blocky - sprite.spriteHeight;
                            sprite.block = block;
                            //System.out.println(block.blockxLeft + " " + block.blockLength +" " + block.blocky);
                        }
                        g2d.drawImage(blockImage, block.blockxLeft, block.blocky, block.blockLength, 102, null); //draw a new block
                        block.blockxLeft -= 2; //subtracting position for block to change across the screen
                        displayableBlocks.put(entry.getKey(), block); //add current block to displayable blocks if in the screen

                    }
                }
                if (!isSpriteOnABlock){
                    sprite.block = null;
                    //System.out.println(sprite.spriteX + " " + sprite.spriteYHead);
                }

                allBlocks = displayableBlocks;
            }

            g2d.dispose(); //dispose this current graphics context every time it finishes painting, allowing for repaint
        }
    }

    public boolean isSpriteOnBoard(Sprite sprite, Block block) { //if the sprite is on a board
        //check if block hits sprite
        int spriteFoot = sprite.spriteYHead + sprite.spriteHeight;
        if (sprite.spriteX >= block.blockxLeft && sprite.spriteX <= (block.blockxLeft + block.blockLength) && Math.abs( spriteFoot- block.blocky)<=10){
            return true;
        }

        return false;

    }
}

class Sprite{ //defines the sprite and its position
    int spriteX;
    int spriteYHead;
    int spriteHeight;
    Block block;

    public Sprite(int xPosition, int yHead, int height) { //resets bullet coordinates
        spriteX = xPosition;
        spriteYHead = yHead;
        spriteHeight = height;
        block = null;
    }
}

class Bullet { //redefine the current position of a bullet
    int id;
    int bx;
    int by;

    public Bullet(int bulletID, int coordX, int coordY) { //resets bullet coordinates
        id = bulletID;
        bx = coordX;
        by = coordY;
    }
}

class Ghost { //redefine the position of a ghost
    int id;
    int gx;
    int gy;

    public Ghost(int ghostID, int coordX, int coordY) { //resets ghost coordinates
        id = ghostID;
        gx = coordX;
        gy = coordY;
    }
}

class Block { //redefine the position of a block
    int id;
    int blockxLeft;
    int blocky;
    int blockLength;

    public Block(int blockID, int coordXLeft, int length, int coordY) { //resets block coordinates
        id = blockID;
        blockxLeft = coordXLeft;
        blockLength = length;
        blocky = coordY;
    }
}

class AttackPowerUp {
    int attackx;
    int attacky;

    public AttackPowerUp(int coordX, int coordY) { //resets attackhost coordinates
        attackx = coordX;
        attacky = coordY;
    }
}

class ShieldPowerUp {

    int shieldx;
    int shieldy;

    public ShieldPowerUp(int coordX, int coordY) { //resets shield coordinates
        shieldx = coordX;
        shieldy = coordY;
    }
}

class HealPowerUp {

    int healx;
    int healy;

    public HealPowerUp(int coordX, int coordY) { //resets Heal coordinates
        healx = coordX;
        healy = coordY;
    }
}
