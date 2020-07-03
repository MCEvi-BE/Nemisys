package org.itxtech.nemisys.command;

import java.util.List;


public interface CommandMap {

    void registerAll(String fallbackPrefix, List<? extends Command> commands);

    boolean register(String fallbackPrefix, Command command);

    boolean register(String fallbackPrefix, Command command, String label);

    boolean dispatch(CommandSender sender, String cmdLine);

    void clearCommands();

    Command getCommand(String name);

}
