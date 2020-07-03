package org.itxtech.nemisys.command;

import org.itxtech.nemisys.Server;
import org.itxtech.nemisys.event.TextContainer;
import org.itxtech.nemisys.permission.Permissible;


public interface CommandSender extends Permissible {


    void sendMessage(String message);


    void sendMessage(TextContainer message);


    Server getServer();


    String getName();

    boolean isPlayer();

}
