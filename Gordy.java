class Gordy extends FileRead {
    Hallway hallway;
    int HP;
    boolean charge=false;
    int level;
    public Gordy(Hallway start,int health,int power)
    {
        hallway=start;
        HP=health;
        level=power;
    }
    
    public int Attack()
    {
        int r=4;
        if(!charge)
        {
            r = nbes.random(1,(level/3)+1);
        }
        if(r==1)
        {
            nbes.sPrintln("DARK RUSH");
            int damage = 3*nbes.random(0,5)+(level/2);
            nbes.sPrintln("Gordy deals "+damage+" damage");
            return damage;
        }
        else if(r==2)
        {
            nbes.sPrintln("DOUBLE BLADE");
            int damage = 8*nbes.random(0,2)+(level/2);
            nbes.sPrintln("Gordy deals "+damage+" damage");
            return damage;
        }
        else if(r==3){
            nbes.sPrintln("FIRE BLADE OF DARKNESS");
            nbes.sPrintln("Gordy is charging");
            charge=true;
        }
        else {
            nbes.sPrintln("FIRE BLADE OF DARKNESS");
            int damage = 2*nbes.random(7,10)+(level/2);
            nbes.sPrintln("Gordy deals "+damage+" damage");
            charge=false;
            return damage;
            }
        return 0;
    }
    public void move() {
            if(hallway.hallUnlocked)
            {
            Hallway temp=hallway.neighbors[nbes.random(0,hallway.neighbors.length-1)];
            while(!temp.hallUnlocked) {
                temp=hallway.neighbors[nbes.random(0,hallway.neighbors.length-1)];
            }
            hallway=temp;
            }
            else {
            hallway=hallway.neighbors[nbes.random(0,hallway.neighbors.length-1)];
            }
            
            
        }
    
    

        
    }
