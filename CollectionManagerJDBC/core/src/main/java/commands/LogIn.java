package commands;

import interfaces.Command;

import java.io.IOException;

public class LogIn extends AuthorizationCommand implements Command {
    public LogIn(String username, String password) {
        super(username, password);
    }

    @Override
    public String execute() throws IOException {
        return null;
    }
}