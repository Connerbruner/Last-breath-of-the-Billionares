public class Race extends Tools{
    Emmi vsEmmi;
    Boss vsBoss;
    Dungeon vsDungeon;
    Gordy vsGordy;
    String mission;
    boolean isEmmi=false;
    boolean isBoss=false;
    boolean isDungeon=false;

    public Race(String name,Emmi emmi)
    {
        vsEmmi=emmi;
        mission=name;
        isEmmi=true;
    }
    public Race(String name,Boss boss)
    {
        vsBoss=boss;
        mission=name;
        isBoss=true;
    }
    public Race(String name,Dungeon dungeon)
    {
        vsDungeon=dungeon;
        mission=name;
        isDungeon=true;
    }


}
