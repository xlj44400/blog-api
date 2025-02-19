package am.ik.blog.github;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "/repos/{owner}/{repo}/{branch}")
public interface GitHubUserContentClient {
	@GetExchange(url = "/{path}")
	Mono<String> getContent(@PathVariable("owner") String owner, @PathVariable("repo") String repo, @PathVariable("branch") String branch, @PathVariable("path") String path);
}
