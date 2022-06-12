class Goal extends Tools {

    String goal;
    Hallway ending;
    boolean complete=false;

    public Goal(String name,Hallway hallway)
    {
        goal=name;
        ending=hallway;
    }
    public Goal(String name)
    {
        goal=name;
    }
    public  String toString()
    {
        if(ending!=null)
        {
           return "Current Goal: "+goal+" go to "+ending.hallwayName; 
        }
        return "Current Goal: "+goal;
    }
    public boolean check(Hallway hallway)
    {
        if(ending!=null)
        {
        if(hallway.hallwayName.equals(ending.hallwayName) && !complete)
        {
            sPrintln(toString()+" COMPLETE");
            complete=true;
            return true;
        }
        }
        return false;
    }
}