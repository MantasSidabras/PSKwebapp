package servicesInterfaces;

import java.util.concurrent.Future;

public interface NicknameGeneratorInterface {
    Future<String> generateNickname();
}