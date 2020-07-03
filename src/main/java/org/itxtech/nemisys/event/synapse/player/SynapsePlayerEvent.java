package org.itxtech.nemisys.event.synapse.player;

import org.itxtech.nemisys.event.synapse.SynapseEvent;
import org.itxtech.nemisys.synapse.SynapsePlayer;


public class SynapsePlayerEvent extends SynapseEvent {

    protected SynapsePlayer player;

    public SynapsePlayerEvent(SynapsePlayer player) {
        this.player = player;
    }

    public SynapsePlayer getPlayer() {
        return player;
    }
}
