package kr.study.spring6.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor {

    @Override
    public String execute(URI uri) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        uri.toURL().openConnection().getInputStream()
                )
        )) {
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw e;
        }
    }
}
