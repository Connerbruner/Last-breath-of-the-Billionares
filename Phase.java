class Phase extends Tools {
    int HP;
    Attack[] attacks;
    int HPM;
    String name;
    int curAttack;


    public Phase ( Attack[] arr , int health , String n ) {
        attacks = arr;
        HP = health;
        name = n;

    }

    public int getHP ( ) {
        return HP;
    }


    public void loseHP ( int HP ) {
        this.HP -= HP;
    }

    public void pickAttack ( ) {
        curAttack = random( 0 , attacks.length - 1 );
        sPrint( attacks[ curAttack ].attackName );
    }

}