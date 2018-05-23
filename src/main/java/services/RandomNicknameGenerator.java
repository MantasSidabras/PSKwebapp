package services;

import org.apache.deltaspike.core.api.future.Futureable;
import servicesInterfaces.NicknameGeneratorInterface;
import servicesInterfaces.RandomGenerator;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@Alternative
@RandomGenerator
@ApplicationScoped
public class RandomNicknameGenerator implements Serializable, NicknameGeneratorInterface{

    private static String[] Beginning = { "Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk" };
    private static String[] Middle = { "air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak" };
    private static String[] End = { "d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur" };

    private static Random rand = new Random();

    @Futureable
    public Future<String> generateNickname() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        return new AsyncResult<>(Beginning[rand.nextInt(Beginning.length)] +
                Middle[rand.nextInt(Middle.length)]+
                End[rand.nextInt(End.length)]);

    }

}