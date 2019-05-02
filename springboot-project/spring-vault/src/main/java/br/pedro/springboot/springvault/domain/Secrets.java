package br.pedro.springboot.springvault.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Secrets {

    @Value("${myrepo.username}")
    private String gitUser;

    @Value("${myrepo.password}")
    private String gitPass;

    public String getGitUser() {
        return this.gitUser;
    }

    public String getGitPass() { return this.gitPass; }
}
