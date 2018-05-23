package decorators;

import servicesInterfaces.NicknameGeneratorInterface;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.ejb.AsyncResult;
import javax.inject.Inject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Decorator
public abstract class NicknameDecorator implements NicknameGeneratorInterface{
    @Inject @Delegate
    NicknameGeneratorInterface nicknameGenerator;

    public Future<String> generateNickname(){
        String name = "";

        try {
            name = nicknameGenerator.generateNickname().get();
        } catch (InterruptedException e) {}
        catch (ExecutionException e) {}
        return new AsyncResult<>("crazy " + name + " name");
    }

}
