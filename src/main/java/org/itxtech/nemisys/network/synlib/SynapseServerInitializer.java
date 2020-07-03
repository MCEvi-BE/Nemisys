
package org.itxtech.nemisys.network.synlib;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class SynapseServerInitializer extends ChannelInitializer<SocketChannel> {

    private SessionManager sessionManager;

    public SynapseServerInitializer(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new SynapsePacketDecoder());
        pipeline.addLast(new SynapsePacketEncoder());
        pipeline.addLast(new SynapseServerHandler(this.sessionManager));
    }
}
