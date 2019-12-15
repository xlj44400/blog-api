package am.ik.blog.service.github;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.constraint.base.ContainerConstraintBase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "blog.github")
@Component
@Validated
public class GitHubProps implements org.springframework.validation.Validator {

    private String accessToken;

    private String webhookSecret;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getWebhookSecret() {
        return webhookSecret;
    }

    public void setWebhookSecret(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == GitHubProps.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidatorBuilder.of(GitHubProps.class)
            .constraint(GitHubProps::getAccessToken, "accessToken", ContainerConstraintBase::notEmpty)
            .constraint(GitHubProps::getWebhookSecret, "webhookSecret", ContainerConstraintBase::notEmpty).build() //
            .validateToEither((GitHubProps) target) //
            .left() //
            .ifPresent(violations -> violations.apply(errors::rejectValue));
    }
}
