package group.shawarma.presentation.service;

import group.shawarma.domain.interactor.UserInteractor;
import group.shawarma.domain.repo.UserRepo;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class UserService extends UserInteractor implements ApplicationContextAware {
    private ApplicationContext ctx;
    private UserRepo userRepo;

    public UserService() {
        super(null);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
        this.userRepo = this.ctx.getBean(UserRepo.class);

        super.setUserRepo(this.userRepo);
    }
}
