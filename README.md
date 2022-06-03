# MISSION 2051
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
  



# DOCS:

## GENERAL methods:
Void - sPrint(String) - Prints using a wait in miliseconds in between characters, set at the start of the game. Modified by cheat codes in tools.java\
Void - wait(milliseconds int) - Waits by a time in miliseconds, the program will not do anything during this time

Object[]- Read(String FileName) - Reads the file of the String you put in based on Save editing order (see Save editing order) 

boolean - choice(String str) takes a string and returns true on [y,Y,yes,Yes] 

boolean - strIsInt(String str) - checks if the string thats been inputted is possible to parse to an integer, if so returns true


## GOTCHA methods:
Void - pull()  Rolls gotcha, lower odds for higher rolls, no duplicates, the more exp that is put in the more likely you are to get a higher tier attack


## BOSSES:

### HOW TO MAKE YOUR OWN BOSS:

- Create an attack array, for example: Attack[] elon = new Attack[]{Laser, blast, Shield};
- NOTE, you must create each attack individually before adding to the array: Attack blast = new Attack("");

void - bossfight(Boss boss) (in game.java)  fights a boss that you inputed

## EXP method:
Void - levelup() Checks for level up

## ATTACK  methods:

Void - attack1() 2069s attack method


## Star methods:
The star setter and getters are for changing the difficulty of missions \
The star setter method will only work if stars are unlocked \
Stars are unlocked after beating the mission once \
Each star adds 1 enemy level above the current player level \
If you wish to add another dungeon, you must check if the stars are unlocked upon each calling of the object 

## Dungeons:
Dungeons take 4 variables, and each dungeon that is added must be put in the dungeon list as well
The dungeon list determines which dungeon is being used, and is used to access the methods in each dungeon object \
To mod, create a new dungeon object, add it to the list, and determine which mission it should be used in \
Then make absolutely sure you've set the currentDungeon int correctly in the mission, and it should play properly 

## TEXT/HOW DOES GAME() WORK
Void - Game() Starts Game and Lets you chose a Mission 


## VARIABLES:
    public static final String SCREEN_CLEAR = "\u001b[H\u001b[2J" 
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
    int attack_tier = tier of the move 
    int level1 = 2069 level 
    int exp1 = 2069 exp 
    int levelr1 = level up Requirement 2069 
    int maxHit = The power of supporting members
    int tSpeed = text speed in miliseconds between characters printed
    boolean is2051joined =  boolean for if a support character is joined
    boolean is2048joined =  boolean for if a support character is joined
    
# Save methods:
  Void grabSave() -  The save accessing sequence \
  Void save() saves data in ram to current accessed file\
  Object[] Read(String filepath) - Spits out an organized array of save elements\
  Void Write(String filepath, Object[] r) - Writes to the save at the filepath using the object array\
  Encrypt and Decrypt - Encrypts or decrypts a save using a pin (Excuslively used for the dev file) 
  
  
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
    