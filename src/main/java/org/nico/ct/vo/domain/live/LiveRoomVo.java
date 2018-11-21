package org.nico.ct.vo.domain.live;

import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.ct.vo.domain.BaseVo;

public class LiveRoomVo extends BaseVo{
	
	private String id;
	
	@NotNull(message = "名称不能为空")
	private String roomName;
	
	private String accountId;
	
	@NotNull(message = "直播间地址不能为空")
	private String liveUrl;
	
	private String liveFlashPhoto;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getLiveUrl() {
		return liveUrl;
	}

	public void setLiveUrl(String liveUrl) {
		this.liveUrl = liveUrl;
	}

	public String getLiveFlashPhoto() {
		return liveFlashPhoto;
	}

	public void setLiveFlashPhoto(String liveFlashPhoto) {
		this.liveFlashPhoto = liveFlashPhoto;
	}
	
}
