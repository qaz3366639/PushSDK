package com.push.cmd.manager;

import io.netty.channel.Channel;

import android.util.Log;

import com.push.cmd.Command;
import com.push.cmd.ErrorCommand;
import com.push.cmd.config.ConfigFactory;
import com.push.cmd.config.ConfigManager;
import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;

/**
 * 命令管理器 --单例
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class CommandManager {

	/** logger **/
	private static String tag = "log";

	/** 单例 **/
	private static CommandManager manager = null;

	/** 命令池 **/
	private CommandPool<Command> pool = null;

	/**
	 * 构造方法
	 */
	private CommandManager() {
		super();
		initCommand();
	}

	/**
	 * 获取管理者
	 * 
	 * @return
	 */
	public static synchronized CommandManager getInstance() {
		if (null == manager) {
			manager = new CommandManager();
		}
		return manager;
	}

	/**
	 * 初始化命令
	 * 
	 * @throws Exception
	 */
	private void initCommand() {

		ConfigFactory factory = ConfigFactory.getInstance();
		ConfigManager manager = factory.getConfigManager();
		// 获取命令池
		pool = manager.getPool();

		Log.i(tag, "初始化命令池....");
	}

	/**
	 * 执行命令(请求|响应)
	 * 
	 * @param ctx
	 * @param message
	 *            报文实体
	 * @throws Exception
	 */
	public void runCommand(Channel channel, DataPackage msg) throws Exception {

		if (null == msg) {
			Log.e(tag, "数据报文为空....");
			throw new MyException(ExceptionCode.NULL_POINT);
		}

		CmdTask cmdThread = new CmdTask(msg, channel);
		Thread thread = new Thread(cmdThread, "cmd");
		thread.start();
	}

	/**
	 * 命令线程
	 * 
	 * @author DRAGON
	 * 
	 */
	private class CmdTask implements Runnable {
		private DataPackage msg;
		private Channel channel;

		/**
		 * 构造方法
		 * 
		 * @param msg
		 * @param channel
		 */
		public CmdTask(DataPackage msg, Channel channel) {
			super();
			this.msg = msg;
			this.channel = channel;
		}

		/**
		 * 线程执行方法
		 */
		@Override
		public void run() {
			Command command = pool.getCommandByCode(msg.getCode());

			if (null == command) {
				new ErrorCommand().execute(channel, msg);
				return;
			}

			command.execute(channel, msg);
			//Log.i(tag, "执行[" + command.getClass() + "]命令....");
		}

	}

}
