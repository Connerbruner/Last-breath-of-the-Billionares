public class Race extends Tools{
    Emmi vsEmmi;
    Boss vsBoss;
    Dungeon vsDungeon;
    Gordy vsGordy;
    String mission;

    public Race(String name,Emmi emmi)
    {
        vsEmmi=emmi;
        mission=name;
    }
    public Race(String name,Boss boss)
    {
        vsBoss=boss;
        mission=name;
    }
    public Race(String name,Dungeon dungeon)
    {
        vsDungeon=dungeon;
        mission=name;
    }
    public Race(String name,Gordy gordy)
    {
        vsGordy=gordy;
        mission=name;
    }

}
