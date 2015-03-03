package com.push.cmd.config;

import java.util.Map;

import com.push.cmd.Command;
import com.push.cmd.manager.CommandPool;

/**
 * 配置管理器--使用spring 注入
 * 
 * @author DRAGON
 * @date 2015年1月16日
 */
public class ConfigManager {

	/** 是否打開http請求 **/
	private int openHttp = 0;

	/** http主机 **/
	private String httpHost;

	/** http请求port **/
	private int httpPort;

	/** http路径 **/
	private Map<String, String> httpPathMap;

	/** abmq生产者 **/
	private Object producer;

	/** socket配置 **/
	private SocketConfig dataConfig;

	/** http连接超时时间 **/
	private int connectTime = 1000 * 30;

	/** 命令map **/
	private CommandPool<Command> pool;

	/** 私钥路径 **/
	private String prvateKeyPath;

	/** 私钥密码 **/
	private String privateKeyPwd;

	/** 公钥路径 **/
	private String publicKeyPath;

	/** 环境 **/
	private String env;
	
	/** 系统ID 也作为客户端Id **/
	private String systemId;
	
	/**客户端UID **/
	private String clientUid;
	
	/**redis **/
	private Object redisSpringProxy;
	
	/** redis id field **/
	private	String redisIdField;

	/** redis Msg field **/
	private	String redisMsgField;
	
	/** fd保存模式 single表示一个UID只能被一个客户端登陆 ;more 表示一个UID能被多个客户端同时登陆**/
	private String fdMode;

	public String getFdMode() {
		return fdMode;
	}

	public void setFdMode(String fdMode) {
		this.fdMode = fdMode;
	}

	public String getRedisIdField() {
		return redisIdField;
	}

	public void setRedisIdField(String redisIdField) {
		this.redisIdField = redisIdField;
	}

	public String getRedisMsgField() {
		return redisMsgField;
	}

	public void setRedisMsgField(String redisMsgField) {
		this.redisMsgField = redisMsgField;
	}

	public Object getRedisSpringProxy() {
		return redisSpringProxy;
	}
	
	public void setRedisSpringProxy(Object redisSpringProxy) {
		this.redisSpringProxy = redisSpringProxy;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getHttpHost() {
		return httpHost;
	}

	public void setHttpHost(String httpHost) {
		this.httpHost = httpHost;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public CommandPool<Command> getPool() {
		return pool;
	}

	public void setPool(CommandPool<Command> pool) {
		this.pool = pool;
	}

	public void setDataConfig(SocketConfig dataConfig) {
		this.dataConfig = dataConfig;
	}

	public SocketConfig getDataConfig() {
		return dataConfig;
	}

	public void setOpenHttp(int openHttp) {
		this.openHttp = openHttp;
	}

	public int getOpenHttp() {
		return openHttp;
	}

	public Object getProducer() {
		return producer;
	}

	public void setProducer(Object producer) {
		this.producer = producer;
	}

	public int getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(int connectTime) {
		this.connectTime = connectTime;
	}

	public Map<String, String> getHttpPathMap() {
		return httpPathMap;
	}

	public void setHttpPathMap(Map<String, String> httpPathMap) {
		this.httpPathMap = httpPathMap;
	}

	public String getPrvateKeyPath() {
		return prvateKeyPath;
	}

	public void setPrvateKeyPath(String prvateKeyPath) {
		this.prvateKeyPath = prvateKeyPath;
	}

	public String getPublicKeyPath() {
		return publicKeyPath;
	}

	public void setPublicKeyPath(String publicKeyPath) {
		this.publicKeyPath = publicKeyPath;
	}

	public String getPrivateKeyPwd() {
		return privateKeyPwd;
	}

	public void setPrivateKeyPwd(String privateKeyPwd) {
		this.privateKeyPwd = privateKeyPwd;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getClientUid() {
		return clientUid;
	}

	public void setClientUid(String clientUid) {
		this.clientUid = clientUid;
	}
	
	

}
