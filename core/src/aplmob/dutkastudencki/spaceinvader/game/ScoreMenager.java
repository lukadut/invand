package aplmob.dutkastudencki.spaceinvader.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import aplmob.dutkastudencki.spaceinvader.game.Config;

/**
 * @since 2016-05-25
 * @author  Witold Studencki
 */
public class ScoreMenager {

    /**
     * dostęp do preferencji, gdzie zapisywane zostają wyniki
     */
    Preferences HScore = Gdx.app.getPreferences("HighScore");

    /**
     * lokalna baza punktów
     */
    int[] Points={0,0,0,0,0,0,0,0,0,0};
    /**
     * lokalna baza imion
     */
    String[] Names={"no one","no one","no one","no one","no one","no one","no one","no one","no one","no one",};

    /**
     * initializacja
     */
    public void initialize()
    {
        getScores();
    }
    /**
     * funkcja testów
     */
    public void testUpdate()
    {
        UpdateScore("test wysoki", 21);
        UpdateScore("test sredni",5);
        UpdateScore("test niski", 0);
        saveScores();
    }
    /**
     * update lokalnej listy wyników
     * @param name  imię
     * @param score zdobyty wynik
     */
    public void UpdateScore(String name,int score)
    {

        String tempName="";
        int tempScore=0;
        boolean changed=false;
        for(int j=0;j< Config.HSCORE_QUANTITY;j++)
        {
            if (changed==true)
            {
                String temp2=Names[j];
                int temp3=Points[j];
                Names[j]=tempName;
                Points[j]=tempScore;
                tempName=temp2;
                tempScore=temp3;
            }
            else if(score>Points[j])
            {
                changed=true;
                tempScore=Points[j];
                tempName=Names[j];
                Points[j]=score;
                Names[j]=name;
            }
        }
        if (changed==true)
            saveScores();


    }

    /**
     * zapis punktów do preferencji
     */
    public void saveScores()
    {
        for(int i=0;i<Config.HSCORE_QUANTITY;i++)
        {

            //testowe zapisywanie pkt,
            String temp = Integer.toString(i);
            HScore.putInteger("score" + temp, Points[i]);
            HScore.putString("name" + temp, Names[i]);


        }
        HScore.flush();
    }

    /**
     * wczytuje z preferencji zapisane punkty
     */
    public void getScores()
    {

        for(int i=0;i<Config.HSCORE_QUANTITY;i++)
        {
            String temp = Integer.toString(i);
            Points[i]=HScore.getInteger("score"+temp);
            Names[i]=HScore.getString("name"+temp);
        }
    }

    /**
     * resetuje listę wyników
     */
    public void resetScore() {

        for (int i = 0; i < Config.HSCORE_QUANTITY; i++) {

            Points[i] = 0;
            Names[i] = "no one";

        }
    }
    /**
     * @param i  pozycja
     * @return imię na i-tej pozycji
     */
    public String getName(int i)
    {
        return Names[i];
    }
    /**
     * @param i  pozycja
     * @return wynik na i-tej pozycji
     */
    public int getPoint(int i)
    {
        return Points[i];
    }
}
