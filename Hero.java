public class Hero extends FileRead {
    boolean    isHealing;
    boolean    isUnlocked;
    String     heroName;
    int        levelUnlocked;
    HeroAttack attack;
    static Hero[] allHeros = {
            new Hero( "2077" , false , 0 , ( ) -> {
                long startTime = System.currentTimeMillis( );
                int  i         = 0;
                while ( startTime + 10000 > System.currentTimeMillis( ) ) {
                    if ( nbes.quickTime( "Kick" , 5000 ) ) {
                        i++;
                    }
                    if ( startTime + 10000 > System.currentTimeMillis( ) ) {
                        if ( nbes.quickTime( "Punch" , 5000 ) ) {
                            i++;
                        }
                    }
                }
                return i;
            } ) ,

            new Hero( "2069" , false , 0 , ( ) -> Hero.allHeros[ Nbes.random( 0 , Hero.allHeros.length ) ].attack.attack( ) ) ,
            new Hero( "2048" , false , 0 , ( ) -> {
                if ( nbes.inputBool( "Go for a rush attack?" ) ) {
                    if ( Nbes.random( 0 , 1 ) == 1 ) {
                        Hero.allHeros[ 2 ].isHealing = false;
                        nbes.sPrintln( "2048 deals 10 damage" );
                        return 10;
                    } else {
                        Hero.allHeros[ 2 ].isHealing = true;
                        nbes.sPrintln( "2048 hits 2051 for 10 damage" );
                        nbes.sPrintln( "2051: WHAT THE HECK 2048" );
                        return - 10;
                    }
                }
                return 0;
            } ) ,
            new Hero( "2007" , false , 3 , ( ) -> {
                while ( Attack.combo.size( ) > 3 ) {
                    Attack.combo.add( new Attack( "2007" , "Combo boost" , 0 , 0 , 0 ) );
                }
                nbes.sPrintln( "COMBO BOOST" );
                nbes.sPrintln( "2007: that should help" );
                return 0;
            } ) ,
            new Hero( "2020",true,2,()->{
                if ( nbes.inputBool( "Need Healing?" ) ) {
                    if ( Nbes.random( 0 , 1 ) == 1 ) {
                        Hero.allHeros[ 2 ].isHealing = true;
                        nbes.sPrintln( "2051 deals 10 damage" );
                        return 10;
                    } else {
                        Hero.allHeros[ 2 ].isHealing = false;
                        nbes.sPrintln( "2020 Heals the enemy" );
                        nbes.sPrintln( "2020: my bad" );
                        return - 10;
                    }
                }
                return 0;
            } ),
    };

    private Hero( String name , boolean healing , int unlocked , HeroAttack heroAttack ) {
        isHealing     = healing;
        heroName      = name;
        levelUnlocked = unlocked;
        isUnlocked    = false;
        attack        = heroAttack;
    }


    public boolean isUnlocked( int Timeline ) {
        if(!this.isUnlocked && levelUnlocked <= Timeline) {
            Game.unlocked.add(this);
        }
        return isUnlocked = levelUnlocked <= Timeline;
    }

}

interface HeroAttack {
    int attack( );
}
