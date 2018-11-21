package org.nico.ct.domain.live;

import org.nico.db.annotation.Param;
import org.nico.db.annotation.Primary;
import org.nico.db.annotation.Table;

@Table("live_room")
public class LiveRoom {
	
	@Primary
	private String id;
	
	@Param("room_name")
	private String roomName;
	
	@Param("account_id")
	private String accountId;
	
	@Param("live_url")
	private String liveUrl;
	
	@Param("live_flash_photo")
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
