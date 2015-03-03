package com.push.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import android.util.Log;

import com.push.cmd.code.DataUtil;
import com.push.cmd.manager.CommandManager;
import com.push.model.DataPackage;
import com.push.util.ClientUtil;

/**
 * TCP 处理线程 响应客户端
 * 
 * @author DRAGON
 * 
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {

	/** logger **/
	private static String tag = "log";

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {

		// 1.去到的非byteBuf缓冲区流
		if (!(msg instanceof ByteBuf)) {
			ctx.close();
			return;
		}

		// 2.读取流
		DataPackage message = DataUtil.getMessage((ByteBuf) msg);

		CommandManager manager = CommandManager.getInstance();
		manager.runCommand(ctx.channel(), message);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		Log.i(tag, "channelActive");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		super.channelUnregistered(ctx);
		Log.i(tag, "取消注册通道");

		if (ctx.channel().isActive()) {
			ctx.channel().close();
		}

	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
		Log.i(tag, "通道取消激活");
	}

//	@Override
//	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
//			throws Exception {
//		super.userEventTriggered(ctx, evt);
//
//		if (evt instanceof IdleStateEvent) {
//			IdleStateEvent event = (IdleStateEvent) evt;
//			if (event.state().equals(IdleState.READER_IDLE)) {
//				System.out.println("READER_IDLE");
//				// 超时关闭channel
//				ctx.close();
//			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
//				System.out.println("WRITER_IDLE");
//			} else if (event.state().equals(IdleState.ALL_IDLE)) {
//				System.out.println("ALL_IDLE");
//				// 发送心跳
//
//				CommandManager manager = CommandManager.getInstance();
//				manager.runCommand(ctx.channel(), ClientUtil.getInstance().getHeartBeatPackage());
//			}
//		}
//
//	}

	/**
	 * 异常处理
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		Log.i(tag, "Unexpected exception from downstream.", cause);
		ctx.close();
		
		//重连
		ReConnectHandler reconnetHandler = new ReConnectHandler();
		reconnetHandler.connect();
	}
}
