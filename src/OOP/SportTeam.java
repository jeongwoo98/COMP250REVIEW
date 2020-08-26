package OOP;

public class SportTeam {
    public String homeTown;
    private int victories, losses, points;
    static public double exchangeRate;          //Static keyword! Shared member

    public SportTeam(){
        victories=losses=points=0;
        homeTown = "Unknown";
    }

    public SportTeam(String town) {
        victories = losses = points = 0;
        homeTown = town;
    }

    public void addWin(){
        this.victories++;
        this.points += 2;
    }

    public static void main(String[] pArgs){
        SportTeam expos, alouettes;
        SportTeam.exchangeRate= 1.57;

        expos = new SportTeam();
        alouettes = new SportTeam("Montreal");

        expos.exchangeRate = 1.58;
        //Should both print out 1.58, since exchangeRate is a static member.
        System.out.println("Rate from expos: "+ expos.exchangeRate);
        System.out.println("Rate from alouettes: "+ alouettes.exchangeRate);
    }
}

