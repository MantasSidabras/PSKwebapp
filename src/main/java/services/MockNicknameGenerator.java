package services;

import servicesInterfaces.NicknameGeneratorInterface;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import java.util.concurrent.Future;

@Alternative
@Specializes
@ApplicationScoped
public class MockNicknameGenerator extends NicknameGenerator{
    @Override
    public Future<String> generateNickname() {
        return new AsyncResult<>("test");
    }
}