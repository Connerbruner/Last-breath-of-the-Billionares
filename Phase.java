class Phase extends FileRead {
    int      HP;
    Attack[] attacks;
    String   name;
    int      curAttack;


    public Phase( Attack[] arr , int health , String n ) {
        attacks = arr;
        HP      = health;
        name    = n;

    }

    public int getHP( ) {
        return HP;
    }


    public void loseHP( int HP ) {
        this.HP -= HP;
    }

    public void pickAttack( ) {
        curAttack = nbes.random( 0 , attacks.length - 1 );
        nbes.sPrint( attacks[ curAttack ].attackName );
    }

}