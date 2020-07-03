package org.itxtech.nemisys.network.protocol.mcpe;


public class GameRulesChangedPacket extends DataPacket {
    public static final byte NETWORK_ID = ProtocolInfo.GAME_RULES_CHANGED_PACKET;

    @Override
    public byte pid() {
        return NETWORK_ID;
    }

    //public RuleData[] ruleDatas = new RuleData[0];

    @Override
    public void decode() {
    }

    @Override
    public void encode() {
        this.reset();

    }
}
