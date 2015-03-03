package com.push.cmd.manager;

import java.util.HashMap;
import java.util.Map;

/**
 * 命令池
 * @author DRAGON
 * @date 2014年12月29日
 */
public final class CommandPool<T> {

	/** 命令map **/
	private Map<Short, T> commandMap = null;
	
	
	public void setCommandMap(Map<Short, T> commandMap) {
		this.commandMap = commandMap;
	}
	
	
	/**
	 * 构造方法
	 */
	public CommandPool() {
		super();
		this.commandMap = new HashMap<Short, T>();
	}

	/**
	 * 添加 命令
	 * @param code
	 * @param command
	 */
	public CommandPool<T> addCommand(Short code,T command){
		this.commandMap.put(code, command);
		return this;
	}
	
	/**
	 * 根据代码获取命令
	 * @param code
	 * @return
	 */
	public T getCommandByCode(Short code){
		return this.commandMap.get(code);
	}

	/**
	 * 根据代码移除命令
	 * @param code
	 */
	public void removeCommand(Short code){
		this.commandMap.remove(code);
	}
	
	/**
	 * 移除所有的命令
	 */
	public void removeAllCommand(){
		this.commandMap.clear();
	}
	
	/**
	 * 获取池的大小
	 * @return
	 */
	public int getCommandPoolSize(){
		return this.commandMap.size();
	}
	
	/**
	 * 是否有此命令
	 * @param code
	 * @return
	 */
	public boolean isHasCommand(Short code){
		return this.commandMap.containsKey(code);
	}
	
	/**
	 * 检测是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return this.commandMap.isEmpty();
	}
	
}
