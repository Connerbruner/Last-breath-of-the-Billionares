class Emmi extends Tools {
    String emmi_type;
    int emmi_HP;
    int emmi_level;
    int emmi_attackNum;
  int emmi_num;
    Attack emmi_attack;
    int emmi_HPM;

    /**
     *
     */
    public Emmi ( int type1 , int level ) {
        emmi_level = level;
        emmi_num=type1;
        if ( type1 == 1 ) {
            emmi_type = "Roomba";

            emmi_HP = 35 + ( emmi_level * 2 );
            sPrintln( "A Roomba appears" );
        }
        if ( type1 == 2 ) {
            emmi_type = "Dog bot";

            emmi_HP = 35 + ( emmi_level * 3 );
            sPrintln( "A Dog bot appears" );
        }
        if ( type1 == 3 ) {
            emmi_type = "Robot with a spear";

            emmi_HP = 25 + ( emmi_level * 2 );
            sPrintln( "A Robot with a spear appears" );
        }
        if ( type1 == 4 ) {
            emmi_type = "Robot with a sword";

            emmi_HP = 30 + ( emmi_level * 3 );
            sPrintln( "A Robot with a sword appears" );
        }

        if ( type1 == 5 ) {
            emmi_type = "Robot with a gun";

            emmi_HP = 40 + ( emmi_level * 4 );
            sPrintln( "A Robot with a gun appears" );
        }
        if ( type1 ==6 ) {
            emmi_type = "Mini Mech";

            emmi_HP = 150 + ( emmi_level * 4 );
            sPrintln( "A Mini Mech appears" );
        }
        if ( type1 == 7 ) {
            emmi_type = "Cyborg";

            emmi_HP = 125 + ( emmi_level * 4 );
            sPrintln( "A Cyborg appears" );
        }
        if ( type1 == 8 ) {
            emmi_type = "Mech";

            emmi_HP = 200 + ( emmi_level * 5 );
            sPrintln( "A Mech appears" );
        }
        emmi_HPM=emmi_HP;
    }

    /**
     *
     */
    public Emmi ( int level ) {
        emmi_type = "Giga Mech";
        emmi_num=10;
        emmi_level = level;
        emmi_HP = 200 + ( emmi_level * 20 );
        emmi_HPM=emmi_HP;
        sPrintln( "Giga Mech crashes down" );
    }

    /**
     *
     */
    public int attack_emmi () {
        return emmi_attack.attack( );
    }

    public void emmi_prep ( ) {
      int num = 3;
      if(emmi_num < 3){
        num = 2;
      }
        emmi_attackNum = random( 1 , num );
        if ( emmi_type.equals( "Roomba" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "ROOMBA RUSH" );
                emmi_attack = new Attack( emmi_type , "ROOMBA RUSH" , emmi_HP - 10 , emmi_HP , 20 );
            }
            if ( emmi_attackNum == 2) {
                sPrintln( "DARK RUSH" );
                emmi_attack = new Attack( emmi_type , "DARK RUSH" , 1 , 10+emmi_level , 15 );
            }
        }
        if ( emmi_type.equals( "Dog bot" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "SPEED TACKLE" );
                emmi_attack = new Attack( emmi_type , "SPEED TACKLE" , 1 , 3+emmi_level , 5 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "BITE FURY" );
                emmi_attack = new Attack( emmi_type , "BITE FURY" , 1 , 10+emmi_level , 15 );
            }
        }
        if ( emmi_type.equals( "Robot with a sword" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "TRIPLE SLASH" );
                emmi_attack = new Attack( emmi_type , "TRIPLE SLASH" , 5 , 15+emmi_level , 17 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "BLADE OF DESTRUCTION" );
                emmi_attack = new Attack( emmi_type , "BLADE OF DESTRUCTION" , 1 , 20+emmi_level , 30 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "ZERO RUSH" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 7 , 10+emmi_level , 10 );
            }
        }
        if ( emmi_type.equals( "Robot with a gun" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "BULLET STORM" );
                emmi_attack = new Attack( emmi_type , "BULLET STORM" , 10 , 15+emmi_level , 30 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "ZERO RUSH" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 5 , 11+emmi_level , 10 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "POWER SHOT" );
                emmi_attack = new Attack( emmi_type , "POWER SHOT" , 5 , 10+emmi_level , 30 );
            }
        }
        if ( emmi_type.equals( "Robot with a spear" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "SPEAR RUSH" );
                emmi_attack = new Attack( emmi_type , "SPEAR RUSH" , 5 , 10+emmi_level , 7 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "ZERO RUSH" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 5 , 11+emmi_level , 10 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "ULTIMATE SPEAR" );
                emmi_attack = new Attack( emmi_type , "ULTIMATE SPEAR" , 5 , 20+emmi_level , 20 );
            }
        }
        if ( emmi_type.equals( "Giga Mech" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "LASER RAIN" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 1 , 40+emmi_level*2 , 10 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "DUAL BLADE" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 10 , 20+emmi_level*2 , 10 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "ZERO RUSH" );
                emmi_attack = new Attack( emmi_type , "ZERO RUSH" , 5 , 30+emmi_level*2 , 10 );
            }
        }
        if ( emmi_type.equals( "Mech" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "LASER RAIN" );
                emmi_attack = new Attack( emmi_type , "LASER RAIN" , 5 , 25+emmi_level , 20 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "POWER BURST" );
                emmi_attack = new Attack( emmi_type , "LASER RAIN" , 10 , 30+emmi_level , 20 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "ULTIMATE RUSH" );
                emmi_attack = new Attack( emmi_type , "ULTIMATE RUSH" , 1 , 50 , 30 );
            }
        }
        if ( emmi_type.equals( "Mini Mech" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "LASER SHOT" );
                emmi_attack = new Attack( emmi_type , "LASER SHOT" , 1 , 30+emmi_level , 30 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "POWER BURST" );
                emmi_attack = new Attack( emmi_type , "POWER BURST" , 10 , 20+emmi_level , 30 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "TRIPLE SLASH" );
                emmi_attack = new Attack( emmi_type , "TRIPLE SLASH" , 15 , 17+emmi_level , 10 );
            }
        }
        if ( emmi_type.equals( "Cyborg" ) ) {
            if ( emmi_attackNum == 1 ) {
                sPrintln( "LASER SHOT" );
                emmi_attack = new Attack( emmi_type , "POWER BURST" , 10 , 30+emmi_level , 20 );
            }
            if ( emmi_attackNum == 2 ) {
                sPrintln( "LASER SLASH" );
                emmi_attack = new Attack( emmi_type , "LASER SLASH" , 15 , 25+emmi_level , 10 );
            }
            if ( emmi_attackNum == 3 ) {
                sPrintln( "CHARGE BEAM" );
                emmi_attack = new Attack( emmi_type , "CHARGE BEAM" , 5 , 40+emmi_level , 30 );

            }
        }
    }
}