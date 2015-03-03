package com.push.cmd.config;


/**
 * 配置工厂---保存全局静态
 * @author DRAGON
 * @date 2015年1月16日
 */
public class ConfigFactory {

	/** 静态 **/
	private static ConfigFactory factory = null;

	private ConfigFactory() {
		super();
	}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static synchronized ConfigFactory getInstance(){
		if (null == factory) {
			factory = new ConfigFactory();
		}
		return factory;
	}
	
	/** 持有配置管理器 **/
	private ConfigManager configManager;

	public ConfigManager getConfigManager() {
		return configManager;
	}

	public void setConfigManager(ConfigManager configManager) {
		this.configManager = configManager;
	}

}
