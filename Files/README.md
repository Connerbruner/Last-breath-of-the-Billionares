# Last breath

HOW TO PLAY:

### Battling enemies

Get gud scrub L * ratio \

When chosing a move chose a number from 1 to 4 \
Lasershot: has low damage but high stun kind of fast\
Aqua: no stun but good damage ok speed\
Cure: heals you super fast \
Ember: high everything but is slow \
Stun: if high then the enemy might not attack \
Speed: you always need to be faster than the enemy \
Tip: take into account what the enemy is attacking with

### Star system:

The star setter and getters are for changing the difficulty of missions \
The star setter method will only work if stars are unlocked \
Stars are unlocked after beating the mission once \
Each star adds 1 enemy level above the current player level \
If you wish to add another dungeon, you must check if the stars are unlocked upon each calling of the object

# DOCS:

## GENERAL methods:

Void - sPrint(String) - Prints using a wait in miliseconds in between characters, set at the start of the game. Modified
by cheat codes in tools.java\
Void - wait(milliseconds int) - Waits by a time in miliseconds, the program will not do anything during this time

boolean - choice(String str) takes a string and returns true on [y,Y,yes,Yes]

boolean - strIsInt(String str) - checks if the string thats been inputted is possible to parse to an integer, if so
returns true

## GOTCHA methods:

## GAME:

### Contstcurtor

        savePath = Where to save data (null if no where)
        Timeline = Where
        resetArr = When reseting save what should the data be;
        name     = Name of game;

### METHODS

      Void - pull()  Rolls gotcha, lower odds for higher rolls, no duplicates, the more exp that is put in the more likely you are to get a higher tier attack
      Void - levelup() Checks for level up
      Void - Game() Starts That Game
      Void - bossfight(Boss boss) Fights boss
      Void - miniBossFight() fight mini boss
      Void - missionComplete( mission ) gives you end of mission rewards
      Void - attack() 2069 attacks
      Void - cure( bonus ) Cures 2069 and bonus will heal more based on bonus
      Void - battle() creates random emmi for you to fight
      Void - restart() brings you back to game
      Void - save() saves game
      Void - grabSave() Gets save
      Int - useItem() uses backpack item
      Int - choseAttack()
      Int - FusionMenu() Fusion attacks
      Int - laserShot( bonus ) Lasershot attack more damage based on bonus
      Int - attackSupport() Surrporting members attack
      Int - chainAttack( HP ) starts a chain of attacks

## NBES:

### VARS:

    static final int        SYSTEM_WIDTH  = Width of system
    static final int        SYSTEM_HEIGHT = Height of system
    static final int        SYSTEM_BORDER = Border of system
    static final int        MAX_CHAR      = Max number of char's per line
    static final JLabel     LABEL         = Label with TEXT1,INPUT
    static final JTextPane  TEXT1         = Text area
    static final JTextField INPUT         = gets user input
    static final JFrame     SYSTEM        = Full frame

    static final Color PLATE_COLOR  = uses showDialog to get the Color of plate
    static final Color SCREEN_COLOR = uses showDialog to get the Color of screen
    MouseListener mouseListener = gets mouse input

### METHOD

    Void - sPrintln( str ) prints and wait for click
    Void - sPrint( str ) prints no wait
    Void - wait(int time) waits in millis
    Void - setText1( str ) sets text then waits
    
    Input Methods = gets user input
    Boolean - strIsInt( str ) is inputed string a int
    Int - random(l,h) random number from l to h
    Int - quickTime(Word time) for time try to enter the word
    String - textFormat( str ) formats text to fit in MAX_CHAR
    BufferedImage - createPlate( ) creates system plate

## FILEREAD:

    Void - grabSave() -  The save accessing sequence 
    Void - save() saves data in ram to current accessed file
    Void - Write(String filepath, Object[] r) - Writes to the save at the filepath using the object array
    Void - writeTeam( b2051 ,  b2048 , Timeline) writes to Files/Team.txt
    Object[] Read(String filepath) - Spits out an organized array of save elements
    Int[] - readTeam() Reads Files/Team.txt
## CHEAT:
    String cheatCode = The needed string to be entered in Files/Team.txt
    boolean isOn = is the cheat code in effect
    
    String filePath = USED TO PUT CHEAT CODES INTO EFFECT
    String txtOverride = USED TO PUT CHEAT CODES INTO EFFECT
    Emmi    emmiOverride = USED TO PUT CHEAT CODES INTO EFFECT
    Color[] systemColors = USED TO PUT CHEAT CODES INTO EFFECT
    
    static Cheat[] cheats = all current cheat codes
    
### METHODS:
    Boolean - checkCode( Cheat code ) checks if cheat code is being used
    String - getCodeString( ) throws IOException Reads Files/Cheats.txt
    Void - useCode( ) uses cheat code


## BOSSES:

### HOW TO MAKE YOUR OWN BOSS:

- Create an attack array, for example: Attack[] elon = new Attack[]{Laser, blast, Shield};
- NOTE, you must create each attack individually before adding to the array: Attack blast = new Attack("");

## Dungeons:

Dungeons take 4 variables, and each dungeon that is added must be put in the dungeon list as well
The dungeon list determines which dungeon is being used, and is used to access the methods in each dungeon object \
To mod, create a new dungeon object, add it to the list, and determine which mission it should be used in \
Then make absolutely sure you've set the currentDungeon int correctly in the mission, and it should play properly

## VARIABLES:

    int HP1 = 2069 hp 
    int missionnum = missions unlocked 
    int HP1m = 2069 max HP 
    int attack_tier = tier of the move 
    int level1 = 2069 level 
    int exp1 = 2069 exp 
    int levelr1 = level up Requirement 2069 
    int maxHit = The power of supporting members
    int tSpeed = text speed in miliseconds between characters printed
    boolean is2051joined =  boolean for if a support character is joined
    boolean is2048joined =  boolean for if a support character is joined

## Save Editing Order

    missionnum
    HP1m
    level1
    levelr1
    exp1
    attack1_tier
    attack2_tier
    attack3_tier
    attack4_tier
    max_hit
    is2051joined
    is2048joined
    