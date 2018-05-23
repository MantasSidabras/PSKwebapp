package services;

import org.apache.deltaspike.core.api.future.Futureable;
import servicesInterfaces.NicknameGeneratorInterface;
import servicesInterfaces.SimpleGenerator;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.util.concurrent.Future;

@Default
@SimpleGenerator
@ApplicationScoped
public class NicknameGenerator implements Serializable, NicknameGeneratorInterface {
    private static int count = 0;

    @Futureable
    public Future<String> generateNickname() {
        try {
            Thread.sleep(8000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        final String generatedNickname = "nickname" + count;
        count++;
        return new AsyncResult<>(generatedNickname);
    }
}