package usecases;

import interceptors.LoggedInvocation;
import services.NicknameGenerator;
import servicesInterfaces.NicknameGeneratorInterface;
import servicesInterfaces.RandomGenerator;
import servicesInterfaces.SimpleGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateAuthorNickname implements Serializable {
    @Inject @SimpleGenerator
    NicknameGeneratorInterface nicknameGenerator;

    private Future<String> nicknameGenerationTask = null;

    @LoggedInvocation
    public String generateNewNickname() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        nicknameGenerationTask = nicknameGenerator.generateNickname();
        return  "/author.xhtml?faces-redirect=true&authorId=" + requestParameters.get("authorId");
    }

    public boolean isNicknameGenerationRunning() {
        return nicknameGenerationTask != null && !nicknameGenerationTask.isDone();
    }

    public String getNicknameGenerationStatus() throws ExecutionException, InterruptedException {
        if (nicknameGenerationTask == null) {
            return null;
        } else if (isNicknameGenerationRunning()) {
            return "Nickname generation in progress";
        }
        return "Suggested nickname: " + nicknameGenerationTask.get();
    }


}