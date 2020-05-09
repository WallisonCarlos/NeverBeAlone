package br.org.neverbealone.core.model.enumeration;

public enum ChannelUserEnum {

	TELEGRAM("TELEGRAM"),
	FACEBOOK("FACEBOOK"),
	TWITTER("TWITTER"),
	MESSENGER("MESSENGER"),
	NEVER_BE_ALONE("NEVER_BE_ALONE");
	
	private String channel;
	
	private ChannelUserEnum(String channel) {
		this.channel = channel;
	}
	
	public String getChannel() {
		return channel;
	}
	
}
