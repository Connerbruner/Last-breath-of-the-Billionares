# MISSION 2051
HOW TO PLAY:

### Battling enemies




# DOCS:
## GENERAL methods:
Void - sPrint(String) - Prints using a wait in miliseconds in between characters, set at the start of the game. Modified by cheat codes in tools.java
Void - wait(milliseconds int) - Waits by a time in miliseconds, the program will not do anything during this time

ArrayList<Object> - Read(String FileName) - Reads the file of the String you put in based on Save editing order (see Save editing order)
Void - GetSave() - Reads from save file, changes game based on values in Save.txt
Void - Edit(String FileName) - Edits the file of the String you put in based on Save editing order (see Save editing order)

boolean - skip() Asks for an input, will skip a cutscene on "Y", "y", "Yes", or "yes". By adding to the if statement you can add extra skip conditions, for example: skip.equals("yea");

boolean - strIsInt(String str) - checks if the string thats been inputted is possible to parse to an integer, if so returns true


## GOTCHA methods:
Void - pull()  Rolls gotcha, lower odds for higher rolls, no duplicates, the more exp that is put in the more likely you are to get a higher tier attack


## BOSS methods:

### HOW TO MAKE YOUR OWN BOSS:

- Create an attack array, for example: Attack[] elon = new Attack[]{Laser, blast, Shield};
- NOTE, you must create each attack individually before adding to the array: Attack blast = new Attack("");

## EXP method:
Void - levelup() Checks for level up

## ATTACK  methods:

Void - attack1() 2069s attack method


## Star methods:
The star setter and getters are for changing the difficulty of missions
They're a little complicated but basically, the star setter method will only work if stars are unlocked
Stars are unlocked after beating the mission once
Each star adds 1 enemy level above the current player level
If you wish to add another dungeon, you must check if the stars are unlocked upon each calling of the object

## Dungeons:
Dungeons take 4 variables, and each dungeon that is added must be put in the dungeon list as well
The dungeon list determines which dungeon is being used, and is used to access the methods in each dungeon object
To mod, create a new dungeon object, add it to the list, and determine which mission it should be used in
Then make absolutely sure you've set the currentDungeon int correctly in the mission, and it should play properly

## TEXT/HOW DOES GAME() WORK
Void - Mission(mission_num)_(cutscene number) Plays a cutscene (non are used)
Void - Game() Starts Game and Lets you chose a Mission 

##DUNGEONS


## VARIABLES:
 public static final String SCREEN_CLEAR = "\u001b[H\u001b[2J";
    public static final String RESET = reset Screen
    public static final String BLACK = makes text black
    public static final String RED = makes text red
    public static final String GREEN = makes text green
    public static final String YELLOW = makes text yellow
    public static final String BLUE = makes text blue
    public static final String PURPLE = makes text purple
    public static final String CYAN = makes text cyan
    public static final String WHITE = makes text white
    int HP1 = 2069 hp
    int missionnum = missions unlocked
    int HP1m = 2069 max HP
    int attack1_tier = tier of the move
    int level1 = 2069 level
    int exp1 = 2069 exp
    int levelr1 = level up Requirement 2069
    int max_hit = number of damage per hit
    int num = placeholder
    int type_num = placeholder
    int hit = placeholder
    int damage = damage placeholder
    int tSpeed = text speed
    int move_tier = power of move

# Save editing order:
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