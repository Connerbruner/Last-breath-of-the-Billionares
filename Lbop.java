
class Lbop extends Tools
{
    String user
    Item currentItem;
    int timeleft=7000;
    int lastAttack=0;
    long startTime;
    
    ItemClass[] items = {
        new ItemClass("Trash lid",5,10,3,5);
        new ItemClass("Trash Bag",10,5,1,2);
        new ItemClass("Stick",1,5,8,20);
        new ItemClass("Metal bar",1,5,8,20);
        new ItemClass("Labtop",1,20,1,3);
        new ItemClass("tennisBall",1,5,8,20);
        new ItemClass("basketBall",10,20,1,5);
        new ItemClass("FootBall",5,10,3,7);
        new ItemClass("tennisBall",1,5,8,20);
        new ItemClass("Rock",5,15,8,20);
    }
    
    public Lbop(String name)
    {
        user=name;
    }
    public void battle()
    {
        currentItem=
    }
    public Item randomItem()
    {
        
    }
}
    public void save() {
        String time = System.currentTimeMillis() / 3600000 + "";
        Object[] arrList = {};
        if (savePath.equals("Save1.txt")) {
            Edit("Save1.txt", encrypt(arrList, "Save1.txt", pin));
        } else {
            Edit(savePath, arrList);
        }
        Object[] templateTxt = {};
        Edit("SaveTemplate.txt", templateTxt);
        System.gc();
    }
    public void restart() {

                sPrintln("The world around you begins to fade to black");
                sPrintln("???: Welcome back to this world of nothingness ");
                sPrintln("2069: no...");
                sPrintln("???: I will help you get out of this");
                sPrintln("2077:I see nothing wrong with that");
                sPrintln("2069: Please help us");
                String choice = "How are you doing";
                while (!choice.equals("START")) {
                    sPrint("Type ¨START¨ to continue     ");
                    choice = scanner.nextLine();
                }
                System.out.println();
                save();
                sendToBot(user+" has fallen. Good thing ??? is here");
            }

        }
}