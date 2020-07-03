
package org.itxtech.nemisys.synapse.network.synlib;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.itxtech.nemisys.network.synlib.SynapsePacketDecoder;
import org.itxtech.nemisys.network.synlib.SynapsePacketEncoder;


public class SynapseClientInitializer extends ChannelInitializer<SocketChannel> {

    private SynapseClient synapseClient;

    public SynapseClientInitializer(SynapseClient synapseClient) {
        this.synapseClient = synapseClient;
    }

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        pipeline.addLast(new SynapsePacketDecoder());
        pipeline.addLast(new SynapsePacketEncoder());
        pipeline.addLast(new SynapseClientHandler(this.synapseClient));
    }
}
